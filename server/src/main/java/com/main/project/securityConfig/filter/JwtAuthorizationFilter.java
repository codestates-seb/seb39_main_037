package com.main.project.securityConfig.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.main.project.securityConfig.service.PrincipalDetails;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class    JwtAuthorizationFilter extends BasicAuthenticationFilter {//로그인을 통한 토큰 발급했으니 그 토큰을 가지고 인가 요청을 검증하는 필터 커스텀

    private UserService userService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청 됨.");

        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = jwtHeader.replace("Bearer ", "");

        String email = JWT.require(Algorithm.HMAC512("seb29_main37 jwt token")).build().verify(jwtToken).getClaim("username").asString();

        if (email != null) {
            WebUser memberEntity = userService.findUserByEmail(email);

            PrincipalDetails principalDetails = new PrincipalDetails(memberEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
            return;
        }
        super.doFilterInternal(request, response, chain);



    }



}
