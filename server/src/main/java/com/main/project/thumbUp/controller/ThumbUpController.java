package com.main.project.thumbUp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/thumbUp")
@Validated
@CrossOrigin("*")
public class ThumbUpController {
    @PostMapping("/post")
    public ResponseEntity postThumbUp (@Valid @RequestBody String inputDto) {


        return new ResponseEntity<>(null,
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity getAllThumbUp (@PathVariable("page") int page,
                                        @PathVariable("size") int size) {


        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{thumbUp-id}")
    public ResponseEntity deleteThumbUp (@PathVariable("thumbUp-id") long thumbUpId) {


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
