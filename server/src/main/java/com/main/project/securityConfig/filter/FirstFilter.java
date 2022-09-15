//package com.main.project.securityConfig.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class FirstFilter implements Filter {//토큰을 생성하기 위한 커스텀 필터
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("토큰 생성을 위한 FirstFilter를 실행합니다다");
//
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        //PrintWriter 한글 깨짐 방지 위한 설정
//        //출처: https://icandooit.tistory.com/106 [i can do "IT":티스토리]
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;//HttpServletRequest는 ServletRequest의 자손 인터페이스로 http 프로토콜의 request정보를 서블릿에 전달하기 위한 목적으로 사용한다.
//        //인터페이스이지만  header의 정보, parameter, cookie, uri, url등의 정보를 읽어올 수 있는 static 메서드를 가지고 있다.
//
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        //Servlet이 HttpServletResponse 객체에 Content Type, 응답코드, 응답 메세지 등을 담아서 전송한다.
//
//
//        httpServletRequest.setCharacterEncoding("UTF-8");
//        if(httpServletRequest.getMethod().equals("POST")){//POST 요청이 들어오면
//            String headerAuth = httpServletRequest.getHeader("Authorization");//일단 http 요청에서 "Authorization" 해더값을 가져온다.
//            if(headerAuth.equals("seb39_pro37")) {//그 값이 다음과 같다면
//                chain.doFilter(httpServletRequest, httpServletResponse);//다음 체인을 실시한다.(SecurityConfig에서 설정했듯이 다음엔 BasicAuthenticationFilter가 실행될 것)
//            }else{
//                PrintWriter writer = httpServletResponse.getWriter();//오류메서지 출력을 위한  PrintWriter 객체 생성, getWriter는 ServletResponse에 정의되어 있다.
//                writer.println("인증 실패, POST요청이 아니거나 Authorization헤더값이 없거나 다릅니다.");
//            }
//
//        }
//        System.out.println("토큰 생성을 위한 FirstFilter를 종료합니다");
//    }
//}
