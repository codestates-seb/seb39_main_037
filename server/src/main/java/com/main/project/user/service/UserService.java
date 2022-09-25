package com.main.project.user.service;

import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
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

    List<WebUser> findUserBydate(LocalDate start, LocalDate end);
    WebUser findUserByEmailForAuth(String email);

    // 추가가능 메서드 : 가장 많이 리뷰가 쓰여진 메뉴

    /* ------------------------------------------------------------ */










}
