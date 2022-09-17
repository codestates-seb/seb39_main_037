package com.main.project.securityConfig;


import com.main.project.securityConfig.provider.FaceBookUserInfo;
import com.main.project.securityConfig.provider.GoogleUserInfo;
import com.main.project.securityConfig.provider.OAuth2UserInfo;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;



//    @Autowired
//    private OAuth2AuthorizedClientService authorizedClientService;
//
//    Authentication authentication = SecurityContextHolder
//            .getContext()
//            .getAuthentication();
//    OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : " + userRequest);

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User.getAttributes());
        System.out.println("클라이언트 아이디는?: "+ userRequest.getClientRegistration().getClientId());
        System.out.println("클라이언트 이름은?: "+ userRequest.getClientRegistration().getClientName());
        // code를 통해 구성한 정보
        System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
        // token을 통해 응답받은 회원정보
        System.out.println("oAuth2User : " + oAuth2User);


        return processOAuth2User(userRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User){

        OAuth2UserInfo oAuth2UserInfo = null;

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            System.out.println("구글 로그인 요청~~");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            System.out.println("페이스북 로그인 요청~~");
            oAuth2UserInfo = new FaceBookUserInfo(oAuth2User.getAttributes());
        } else {
            System.out.println("지원하지 않는 로그인 방식입니다.");
        }

        Optional<WebUser> userOptional =
                userRepository.findByProviderAndProviderId(oAuth2UserInfo.getProvider(), oAuth2UserInfo.getProviderId());


        WebUser webUser = new WebUser();
        if (userOptional.isPresent()) {
            webUser = userOptional.get();
            // user가 존재하면 update 해주기
            webUser.setEmail(oAuth2UserInfo.getEmail());
            userRepository.save(webUser);
        } else {
            // user의 패스워드가 null이기 때문에 OAuth 유저는 일반적인 로그인을 할 수 없음.
            webUser.setNickName(oAuth2UserInfo.getProvider() + "_" + oAuth2UserInfo.getProviderId()) ;
            webUser.setEmail(oAuth2UserInfo.getEmail());
            webUser.setProvider(oAuth2UserInfo.getProvider());
            webUser.setProviderId(oAuth2UserInfo.getProviderId());
           if(   oAuth2UserInfo.getProfileImg()!=null  ) webUser.setProfileImg(oAuth2UserInfo.getProfileImg().getBytes());
            userRepository.save(webUser);
        }

        return new PrincipalDetails(webUser, oAuth2User.getAttributes());
    }












}
