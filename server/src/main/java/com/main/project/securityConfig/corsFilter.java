//package com.main.project.securityConfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//

//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class corsFilter {
//
//
//    @Bean
//    public CorsFilter corsFilter() {
//        System.out.println("Cors 필터 시작");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();//CORS를 설정하는 설정 객체
//        config.setAllowCredentials(true);//서버가 응답할 때 생성해서 주는 credential을 자바스크립트가 처리할 수 있도록 설정
//        config.addAllowedOrigin("*");//모든 api에 자원 교차공유허용
//        config.addAllowedHeader("*");//모든 종류의 헤더값 공유 허용
//        config.addAllowedMethod("*");//교차공유시 모든 http요청 허용
//        source.registerCorsConfiguration("/api/**", config);
//
//        System.out.println("Cors 필터 종료");
//        return new CorsFilter(source);
//    }
//}
