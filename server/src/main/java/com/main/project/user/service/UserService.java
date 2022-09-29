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

    WebUser checkUserByUserId(long user);

    UserDto.responseUserActivityDto findMyUserActivity(UserDto.getMyUserActivityDetailsDto myUserDto);




    List<WebUser> findUserBydate(LocalDate start, LocalDate end);
    WebUser findUserByEmail(String email);

    WebUser findUserByFileName(String filename);

    boolean isPassWordMatch(String dtoPassWord, WebUser toCheckUser);

    void checkIsUserDeActive(WebUser deActiveUser);











}
