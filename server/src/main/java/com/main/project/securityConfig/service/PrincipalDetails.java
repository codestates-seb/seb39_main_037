package com.main.project.securityConfig.service;


import com.main.project.user.entity.WebUser;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


@Data
@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {
    //PrincipalDetails(UserDetails)는 인증을 위해 AuthenticationFilter -> AuthenticationManager -> AuthenticationProvider로 부터 전달되는 사용자 인증정보(Authentiation 객체)를
    // 가지고 UserDetailsService가 만들어지는 사용자 정보가 담긴 객체의 클래스이다.
    private WebUser webUser;
    private Map<String, Object> attributes;

    public PrincipalDetails(WebUser webUser) {
        this.webUser = webUser;
    }//일반 로그인 사용시

    public PrincipalDetails(WebUser webUser, Map<String, Object> attributes) {//oath로그인 사용시
        this.webUser = webUser;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(webUser.getAuthority());
            return null;// 아직 권한 설정 구현 안 헀으니 user 클래스에 만들면 그때 하자
    }

    @Override
    public String getPassword() {
        return webUser.getPassword();
    }

    @Override
    public String getUsername() {
        return webUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return webUser.getIsUserActive().equals(WebUser.UserActive.Active);
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }




    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
