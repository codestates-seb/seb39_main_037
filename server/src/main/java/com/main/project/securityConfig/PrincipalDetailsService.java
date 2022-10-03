package com.main.project.securityConfig;


import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor//final로 선언된 모든 필드를 인자값으로 하는 생성자를 생성해준다.
public class PrincipalDetailsService implements UserDetailsService {

    private final UserService userService;////final로 안 했더니 org.springframework.security.authentication.InternalAuthenticationServiceException: null 발생 -> RequiredArgsConstructor의 기능 참고고


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("이메일 아이디로 검색 시작");
        System.out.println("아이디 :" + email);


        try {
//            WebUser webUser =  new WebUser();
            WebUser TologinWebUser =  userService.findUserByEmail(email);
                    //아이디(username)이 들어오면(우리는 이메일로 로그인하므로)이메일을 받아 db에서 객체를 검색
            System.out.println("검색완료 ");
            return new PrincipalDetails(TologinWebUser);
        } catch (Exception e) {
            System.out.println("에러 발생");
            e.printStackTrace();
        }
            return  null;
    }
}
