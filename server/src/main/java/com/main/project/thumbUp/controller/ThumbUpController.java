package com.main.project.thumbUp.controller;

import com.main.project.thumbUp.dto.ThumbUpDto;
import com.main.project.thumbUp.service.ThumbUpService;
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
    UserServieImpl userService;


    public ThumbUpController(ThumbUpServiceImpl thumbUpService, UserServieImpl userService) {
        this.thumbUpService = thumbUpService;
        this.userService = userService;

    }
    @PostMapping("/post/{review-id}")
    public ResponseEntity postThumbUp (@PathVariable("review-id") long reviewId,
                                       @Valid @RequestBody ThumbUpDto thumbUpDto) {

        boolean result = false;

        WebUser user = userService.findUser(thumbUpDto.getUserId());

        if (Objects.nonNull(user))
            result = thumbUpService.createThumbUp(user, reviewId);

        return result ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteThumbUp (@PathVariable("review-id") long reviewId,
                                         ThumbUpDto thumbUpDto) {

        WebUser user = userService.findUser(thumbUpDto.getUserId());
        if(user != null) {
            thumbUpService.deleteThumbUp(user, reviewId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}