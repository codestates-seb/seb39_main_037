package com.main.project.securityConfig;


import com.main.project.securityConfig.filter.JwtAuthenticationFilter;
import com.main.project.securityConfig.filter.JwtAuthorizationFilter;
import com.main.project.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    private final CorsFilter corsFilter;
    private final UserService userService;


    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .formLogin().disable()
//                .cors().disable()
                .httpBasic().disable()
                .apply(new CustomDsl())
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .and().build();


    }


    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {//우리가 새로운 필터를 추가할 때마다 addFilter()로 추가하다보면 securityFilterChain() 메서드의 기이가 너무 길어진다.
        // 또한 WebSecurityConfigureAdapter가 deprecated되면서 내부 클래스로 처리해야한다.


        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userService)); // 발급한 토큰을 다시 클라이언트한테서 받아서 인가 요청 처리할 커스텀 필터 추가
        }
    }



}
