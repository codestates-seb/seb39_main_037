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
    @PostMapping("/post")
    public ResponseEntity postThumbUp (@Valid @RequestBody ThumbUpDto.PostDto thumbUpDto) {

        boolean like = true;
        WebUser webUser = userService.findUser(thumbUpDto.getUserId());
        if(Objects.nonNull(webUser))
            like = thumbUpService.createThumbUp(thumbUpDto.getUserId(), thumbUpDto.getReviewId());

        return like ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/count")
    public ResponseEntity getCountThumbUp (ThumbUpDto.PatchDto thumbUpDto) {

        List<String> count = thumbUpService.count(thumbUpDto.getReviewId(),thumbUpDto.getUserId());

        log.info("likeCount : {} ", count);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{thumbUp-id}")
    public ResponseEntity deleteThumbUp (@PathVariable("thumbUp-id") long thumbUpId, ThumbUpDto.DeleteDto thumbUpDto) {

        thumbUpService.deleteThumbUp(thumbUpId, thumbUpDto.getUserId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}