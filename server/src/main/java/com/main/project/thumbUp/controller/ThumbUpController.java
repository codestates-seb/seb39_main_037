package com.main.project.thumbUp.controller;

import com.main.project.thumbUp.dto.ThumbUpDto;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.thumbUp.mapper.ThumbUpMapper;
import com.main.project.thumbUp.service.ThumbUpServiceImpl;
import com.main.project.user.entity.WebUser;
import com.main.project.user.service.UserServieImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/v1/thumbUp")
@Validated
@CrossOrigin("*")
public class ThumbUpController {

    private final ThumbUpServiceImpl thumbUpService;
    private final ThumbUpMapper thumbUpMapper;
    UserServieImpl userService;


    public ThumbUpController(ThumbUpServiceImpl thumbUpService, UserServieImpl userService, ThumbUpMapper thumbUpMapper) {
        this.thumbUpService = thumbUpService;
        this.userService = userService;
        this.thumbUpMapper = thumbUpMapper;
    }
    @PostMapping("/post/{review-id}")
    public ResponseEntity postThumbUp (@PathVariable("review-id") long reviewId,
                                       @Valid @RequestBody ThumbUpDto thumbUpDto) {

        boolean result = false;

        WebUser user = userService.checkUserByUserId(thumbUpDto.getUserId());

        if (Objects.nonNull(user))
            result = thumbUpService.createThumbUp(user, reviewId);

        return result ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/mypage/{user-id}")
    public ResponseEntity getAllUserLike (@PathVariable("user-id") long userId) {

        WebUser user = userService.checkUserByUserId(userId);

        List<ThumbUp> thumbUps = thumbUpService.findUserLike(user);

        return new ResponseEntity<>(thumbUpMapper.thumbUpsToThumbUpResponseDtos(thumbUps),
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteThumbUp (@PathVariable("review-id") long reviewId,
                                         ThumbUpDto thumbUpDto) {

        WebUser user = userService.checkUserByUserId(thumbUpDto.getUserId());
        if(user != null) {
            thumbUpService.deleteThumbUp(user, reviewId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}