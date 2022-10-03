package com.main.project.user.controller;


import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import com.main.project.user.dto.UserDto;
import com.main.project.user.entity.WebUser;
import com.main.project.user.mapper.UserMapper;
import com.main.project.user.repository.service.UserServieImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserContorller {

    private UserServieImpl userService;

    private UserMapper mapper;

    public UserContorller(UserServieImpl userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/post")
    public ResponseEntity postUser(@RequestBody UserDto.postUserDto postUserDto/*, @RequestPart MultipartFile profileImg*/) throws IOException, URISyntaxException {


        LocalDateTime time = LocalDateTime.now();
        int year = time.getYear();
        int month = time.getMonthValue();//1~12
        int day = time.getDayOfMonth();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();


        WebUser webUser =  mapper.userPostDtoToWebUser(postUserDto);
        // 프로필을 등록하지 않을 경우
//        if(profileImg.isEmpty()){
//            ClassPathResource resource = new ClassPathResource("images/defaultImg.jpg");
//            byte[] defaultImg = FileCopyUtils.copyToByteArray(resource.getInputStream());
//            webUser.setProfileImg(defaultImg);
//        }
        //프로필 사진이 있을 경우
//        else {
//            webUser.setProfileImgName(profileImg.getOriginalFilename() + year + month + day + hour + minute + second);
//            webUser.setProfileImg(profileImg.getBytes());
//        }

         WebUser newUser = userService.registerUser(webUser);
        // 유저 등록시 필요한 내용(유저 본명, 닉네임, 이메일, 비밀번호, 사진)을 dto로 담기(파라미터)
        //
        UserDto.responseUserDto responseUserDto = new UserDto.responseUserDto();
        responseUserDto.setUserId(newUser.getUserId());
        responseUserDto.setUserName(newUser.getUserName());
        responseUserDto.setEmail(newUser.getEmail());
        responseUserDto.setNickName(newUser.getNickName());
//        responseUserDto.setImgUrl(uriMaker(newUser));
        return new ResponseEntity(mapper.webUserToresponseUserDto(newUser), HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchUser(@RequestBody UserDto.patchUserDto patchUserDto){
         WebUser webUser =  mapper.userPatchDtoToWebUser(patchUserDto);

         WebUser edittedUser = userService.editUser(webUser);
        UserDto.responseUserDto responseUserDto = new UserDto.responseUserDto();
        responseUserDto.setUserId(edittedUser.getUserId());
        responseUserDto.setUserName(edittedUser.getUserName());
        responseUserDto.setEmail(edittedUser.getEmail());
        responseUserDto.setNickName(edittedUser.getNickName());


        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }


    @PatchMapping("/edit/password")
    public ResponseEntity patchUser(@RequestBody UserDto.patchUserpasswordDto patchUserpasswordDto){

        WebUser edittedUser = userService.editUserPassWord(patchUserpasswordDto);

        UserDto.responseUserDto responseUserDto = new UserDto.responseUserDto();
        responseUserDto.setUserId(edittedUser.getUserId());
        responseUserDto.setUserName(edittedUser.getUserName());
        responseUserDto.setEmail(edittedUser.getEmail());
        responseUserDto.setNickName(edittedUser.getNickName());


        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }

    @GetMapping("/search/{userid}")
    public ResponseEntity getUser(@PathVariable("userid") long userid){

       WebUser webUser  = userService.checkUserByUserId(userid);

       return new ResponseEntity(mapper.webUserToresponseUserDto(webUser), HttpStatus.FOUND);
    }


    @GetMapping("/mypage")
    public ResponseEntity getUser(@RequestBody UserDto.getMyUserActivityDetailsDto getMyUserActivityDetailsDto){

        UserDto.responseUserActivityDto webUserActivity  = userService.findMyUserActivity(getMyUserActivityDetailsDto);

        return new ResponseEntity(webUserActivity, HttpStatus.FOUND);
    }


//    @GetMapping("/mypage/review")
//    public ResponseEntity getUserReview(@RequestBody UserDto.getMyUserActivityDetailsDto getMyUserActivityDetailsDto){
//
//        UserDto.responseUserActivityDto webUserActivity  = userService.findMyUserActivity(getMyUserActivityDetailsDto);
//
//        return new ResponseEntity(webUserActivity.getListOfReview(), HttpStatus.FOUND);
//    }
//
//
//    @GetMapping("/mypage/badge")
//    public ResponseEntity getUserBadge(@RequestBody UserDto.getMyUserActivityDetailsDto getMyUserActivityDetailsDto){
//
//        UserDto.responseUserActivityDto webUserActivity  = userService.findMyUserActivity(getMyUserActivityDetailsDto);
//
//        return new ResponseEntity(webUserActivity.getListOfBadge(), HttpStatus.FOUND);
//    }
//
//
//    @GetMapping("/mypage/comment")
//    public ResponseEntity getUserComment(@RequestBody UserDto.getMyUserActivityDetailsDto getMyUserActivityDetailsDto){
//
//        UserDto.responseUserActivityDto webUserActivity  = userService.findMyUserActivity(getMyUserActivityDetailsDto);
//
//        return new ResponseEntity(webUserActivity.getListOfComment(), HttpStatus.FOUND);
//    }
//
//
//    @GetMapping("/mypage/thumbup")
//    public ResponseEntity getUserThumnUps(@RequestBody UserDto.getMyUserActivityDetailsDto getMyUserActivityDetailsDto){
//
//        UserDto.responseUserActivityDto webUserActivity  = userService.findMyUserActivity(getMyUserActivityDetailsDto);
//
//        return new ResponseEntity(webUserActivity.getListOfThumbUp(), HttpStatus.FOUND);
//    }


    @GetMapping("/search/all/{page}")
    public ResponseEntity getAllUser(@PathVariable("page") int page){

         Page<WebUser> pageUsers = userService.findAllUser(page - 1);
        List<UserDto.responseWithPhotoUrlDTO> allUser =
                pageUsers.getContent().stream().map(WebUser -> new UserDto.responseWithPhotoUrlDTO(WebUser.getUserId(), WebUser.getUserName(), WebUser.getNickName(), WebUser.getEmail(), WebUser.getCreatedAt(), WebUser.getUpdatedAt(),uriMaker(WebUser) ))
                .collect(Collectors.toList());

        return  new ResponseEntity(new Multi_ResponseDTOwithPageInfo<>(allUser, pageUsers), HttpStatus.FOUND);
    }







    @GetMapping("/download/photo/{filename}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("filename") String filename) throws Exception {


        WebUser user = userService.findUserByFileName(filename);


//        HttpHeaders header = new HttpHeaders();
//        header.add("Content-Type", );

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "photo; filename=\"" + user.getProfileImgName()
                                + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(new ByteArrayResource(user.getProfileImg()));

    }




    @DeleteMapping("/withdrawal")
    public ResponseEntity deleteUser(@RequestBody UserDto.deleteUserDto deleteUserDto){
        long userId = deleteUserDto.getUserId();
        String password = deleteUserDto.getPassword();
        userService.deActiveUser(userId, password);

        return new ResponseEntity(HttpStatus.OK);
    }


    private String uriMaker(WebUser user){

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/download/photo/")
                .path(String.valueOf(user.getProfileImgName()))
                .toUriString();
    }

}
