package com.main.project.user.controller;


import com.main.project.user.dto.UserDto;
import com.main.project.user.service.UserServieImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserContorller {

    private UserServieImpl userService;



//    @PostMapping
//    public String postUser(UserDto.postUserDto){
//
////        userService.registerUser();
//        // 유저 등록시 필요한 내용(유저 본명, 닉네임, 이메일, 비밀번호, 사진)을 dto로 담기(파라미터)
//        //
//
//
//
//        return new ResponseBody(new Dto )
//    }

}
