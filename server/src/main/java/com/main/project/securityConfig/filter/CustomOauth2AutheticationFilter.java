package com.main.project.securityConfig.filter;

import com.google.gson.Gson;
import com.main.project.securityConfig.service.PrincipalDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomOauth2AutheticationFilter extends OAuth2LoginAuthenticationFilter {
    public CustomOauth2AutheticationFilter(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientService authorizedClientService) {
        super(clientRegistrationRepository, authorizedClientService);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Authentication authenticatedUserInfo = super.attemptAuthentication(request, response);

        PrincipalDetails principalDetails = (PrincipalDetails)authenticatedUserInfo.getPrincipal();

        String email   = principalDetails.getWebUser().getEmail();
        String userName = principalDetails.getWebUser().getUserName();
        String nickName = principalDetails.getWebUser().getNickName();
        String profileImgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/download/photo/")
                .path(String.valueOf(principalDetails.getWebUser().getProfileImgName()))
                .toUriString();

        Map<String, String> loginResponse = new HashMap<>();
        loginResponse.put("email", email);
        loginResponse.put("userName", userName);
        loginResponse.put("nickName", nickName);
        loginResponse.put("profileImgUrl", profileImgUrl);

        response.setContentType("application/json");
//        String loginResponse = "{\"email\" : " + email +", \"userName\" : " + userName + ", \"nickName\" : " +nickName + ", \" profileImgUrl\" : " + profileImgUrl + " \n}";

        String json = new Gson().toJson(loginResponse);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.write(json);
        writer.close();
        return super.attemptAuthentication(request, response);
    }














    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String email   = principalDetails.getWebUser().getEmail();
        String userName = principalDetails.getWebUser().getUserName();
        String nickName = principalDetails.getWebUser().getNickName();
        String profileImgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/download/photo/")
                .path(String.valueOf(principalDetails.getWebUser().getProfileImgName()))
                .toUriString();

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
