package com.main.project.user.controller;


import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import com.main.project.user.mapper.UserMapper;
import com.main.project.user.service.UserServieImpl;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserContorller {

    private UserServieImpl userService;
    private UserMapper mapper;

    public UserContorller(UserServieImpl userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping
    public String postUser(UserDto.postUserDto postUserDto, MultipartFile profileImg) throws IOException, URISyntaxException {


        LocalDateTime time = LocalDateTime.now();
        int year = time.getYear();
        int month = time.getMonthValue();//1~12
        int day = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();


        WebUser webUser =  mapper.userPostDtoToWebUser(postUserDto);
        // 프로필을 등록하지 않을 경우
        if(profileImg.isEmpty()){
            ClassPathResource resource = new ClassPathResource("images/defaultImg");
            byte[] defaultImg = FileCopyUtils.copyToByteArray(resource.getInputStream());
            webUser.setProfileImg(defaultImg);
        }
        //프로필 사진이 있을 경우
        else {
            webUser.setProfileImgName(profileImg.getOriginalFilename() + year + month + day + hour + minute + second);
            webUser.setProfileImg(profileImg.getBytes());
        }

//        userService.registerUser();
        // 유저 등록시 필요한 내용(유저 본명, 닉네임, 이메일, 비밀번호, 사진)을 dto로 담기(파라미터)
        //



        return null;
    }

}
