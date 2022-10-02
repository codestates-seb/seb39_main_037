package com.main.project.securityConfig.filter;


import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.main.project.securityConfig.PrincipalDetails;
import com.main.project.user.entity.WebUser;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {//jwt를 통한 사용자 인증을 처리할 커스텀 필터 클래스

    private final AuthenticationManager authenticationManager;




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        System.out.println("jwt를 통한 로그인 처리 시작");


        try{
//            BufferedReader br = request.getReader();//httprequest body의 내용을 그대로 읽어온다.
//            String input = null;
//            while((input = br.readLine()) != null) {//httprequest의 내용들이 존재한다면
//                System.out.println(input);//계속 서버 콘솔에 출력한다. Ex) {"email":"dd@naver.com","password":"12313"}
//            }


            ObjectMapper om = new ObjectMapper();
            //jackson 라이브러리를 통해(readValue) httpRequest에 담긴 json을 자바 객체로 변화해주는 기능을 수행 ,  httpRequset body에 담긴 json 형식의 데이터(아이디, 비밀번호)를 stream으로 꺼내고 이를 WebUser 타입으로 매핑
            WebUser webUser = om.readValue(request.getInputStream(), WebUser.class);




            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(webUser.getEmail(), webUser.getPassword());//사용자가 입력한 아이디, 주소를 가지고 토큰을 만든다.




            System.out.println("아이디와 비밀번호가 확인된 유저를 위한 JWT 생성, jwt를 통한 로그인 처리 완료");
                Authentication authentication = authenticationManager.authenticate(authenticationToken);//만들어진 토큰을 가져와 authenticationManager가 전달 받아 Authentication 객체 생성
                // Authentication은 UsernamePasswordAuthenticationToken의 조상 클래스로 UsernamePasswordAuthenticationToken내부에 Authenticationd을 구현해 놓았다.( principal, credentials;
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();//Authentication 객체에서 사용자 정보를 담은 principal을 가져오고 이를  PrincipalDetails로 변환 -> repository의 DB 데이터 호출을 통해 사용자를 검증하는데 쓰인다.

            return authentication;//PrincipalDetailsService의 loadUserByUsername()이 정상 실행되면(DB에서 유저가 있는지, 유저 정보와 일치하는지 체크) authentication을 반환한다.
            //반환된 authentication 객체는 session에 저장될 것이다.
            // attemptAuthentication()가 완료되면 아래의 successfulAuthentication가 실행되는데 여기서 JWT토큰을 만들어 클라이언트에게 응답으로 전달해준다.

        } catch (IOException e) {
            e.printStackTrace();
        }
            return  null;
//        return super.attemptAuthentication(request, response);// 조상클래스인 UsernamePasswordAuthenticationFilter의 매서드로 처리 -> attemptAuthentication()의 42~53의 토큰생성~authenticaiton 생성 ~사용자 검증 프로세스가 구현되면 필요가 없어진다.
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("Authentication을 세션으로 전달 후 클라이언트에서 토큰 전달 프로세스 시작");
        //AbstractAuthenticationProcessingFilter 추상 클래스에서 attemptAuthentication()와 successfulAuthentication()가 호출되는데 attemptAuthentication()에서 return 되는 Authentication가 바로 authResult이다
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()//com.auth0:java-jwt 디펜던시를 주입 받아야 사용 가능
                .withSubject("seb29_pro37 jwt token")//payload에 담길 payload의 요소중 하나인 subject를 정의하는 메서드(Ex. {"sub": "someInformation", "name": "phillip", "iat": 151623391}
                .withExpiresAt(new Date(System.currentTimeMillis() + (60 * 1000 * 100)))//토큰 만료시간 설정(60초(60*1000)*10: 즉 100분)
                .withClaim("id", principalDetails.getWebUser().getUserId())//토큰에 id에 해당하는 값 넣어주기
                .withClaim("username", principalDetails.getWebUser().getEmail())//토큰에 아이디(username)에 해당하는 값 넣어주기
                .sign(Algorithm.HMAC512("seb29_main37 jwt token"));//jwt의 credential에 넣을 암호화된 데이터
        response.addHeader("Authorization", "Bearer " + jwtToken);//httpResponse header에 넣을 헤더 값 정의 (Key:value)
//        super.successfulAuthentication(request, response, chain, authResult);//직접 구현하였기에 사용하지 않는다.

        response.setStatus(HttpStatus.ACCEPTED.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());


        String email   = principalDetails.getWebUser().getEmail();
        String userName = principalDetails.getWebUser().getUserName();
        String nickName = principalDetails.getWebUser().getNickName();
        String profileImgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/download/photo/")
                .path(String.valueOf(principalDetails.getWebUser().getProfileImgName()))
                .toUriString();

        // Map
        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("email", email);
        loginResponse.put("userName", userName);
        loginResponse.put("nickName", nickName);
        loginResponse.put("profileImgUrl", profileImgUrl);

        response.setContentType("application/json");
//        String loginResponse = "{\"email\" : " + email +", \"userName\" : " + userName + ", \"nickName\" : " +nickName + ", \" profileImgUrl\" : " + profileImgUrl + " \n}";

        String json = new Gson().toJson(loginResponse);
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
    }



}

