package com.main.project.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class postUserDto{
        String userName;//본명
        String nickName;//닉네임
        @Email
        String email;//이메일
        String password;
    }


    @Getter
    @NoArgsConstructor
    public static class patchUserDto{
        String userName;
        String nickName;
        @Email
        String email;
    }


    @Getter
    @NoArgsConstructor
    public static class deleteUserDto{
        String password;
// 유저 계정을 삭제할 경우 계정 해지 사유를 확인하는 설문 조사도 가능할 듯
    }


    @Getter
    @NoArgsConstructor
    public static class responseUserDto{
        String password;
// 유저 계정을 삭제할 경우 계정 해지 사유를 확인하는 설문 조사도 가능할 듯
    }

}
