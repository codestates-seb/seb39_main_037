package com.main.project.user.service;

import com.main.project.badge.entity.Badge;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import  java.util.*;
public interface UserService {


    public WebUser registerUser(WebUser webUser);

    WebUser editUser(WebUser editUser);

    WebUser editUserPassWord(UserDto.patchUserpasswordDto patchUserpasswordDto);

    void deActiveUser(long userid, String password);

    Page<WebUser> findAllUser(int page);

    WebUser findUser(long user);






/* ------------------------------------------------------------ */
// 관리자 전용 메서드
    public WebUser findAllUserByMonth();//월별 총 가입자 검색

    public WebUser findAllUserByYear();//연 신규 가입자를 월별로 반환 Ex)1월:11명, ```` 12월 : 243명

    public WebUser findNewUsersByMonth();

    public WebUser findNewUsersByYear();

    WebUser findUserByEmailForAuth(String email);

    // 추가가능 메서드 : 가장 많이 리뷰가 쓰여진 메뉴

    /* ------------------------------------------------------------ */










}
