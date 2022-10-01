package com.main.project.securityConfig;



import com.main.project.securityConfig.filter.CustomOauth2AutheticationFilter;
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
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.*;



@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    private final CorsFilter corsFilter;
    private final UserService userService;

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

//    @Autowired
//    private InMemoryOAuth2AuthorizedClientService inMemoryOAuth2AuthorizedClientService;
//
//    @Autowired
//    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.cors().disable();

        return http.authorizeRequests()
                .antMatchers("/user/search/{userid}").authenticated()
                .anyRequest().permitAll()
                .and()

                .formLogin().disable()//시큐리티 기본제공 form login html 활성화 -> UsernamePasswordAuthenticationFilter가 설정된다.
                // 우리가 작성한 JwtAuthenticationFilter가  UsernamePasswordAuthenticationFilter를 상속 받기에 활성화하던 안 하던 인증 절차는 진행이 된다.
                //.cors().disable()
                .httpBasic().disable()//http베이직 로그인 기능 비활성화
//                .formLogin() //로그인 폼은  https://tmdrl5779.tistory.com/72
//                .loginPage("/auth/loginForm") //로그인 페이지를 우리가 만든 페이지로 등록한다.
//                .loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해줌(서비스의 loadUserByName로 알아서)
//                .defaultSuccessUrl("/") //정상일떄
//                .and()
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
//                    .addFilter(new CustomOauth2AutheticationFilter(, inMemoryOAuth2AuthorizedClientService))
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userService)); // 발급한 토큰을 다시 클라이언트한테서 받아서 인가 요청 처리할 커스텀 필터 추가

        }


    }




}
