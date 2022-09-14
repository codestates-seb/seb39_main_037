package com.main.project.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/comment")
@Validated
@CrossOrigin("*")
public class CommentController {

    @PostMapping("/post")
    public ResponseEntity postComment (@Valid @RequestBody String inputDto) {


        return new ResponseEntity<>(null,
                HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchComment (@Valid @RequestBody String inputDto) {


        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }

    @GetMapping("/{review-id}")
    public ResponseEntity getComment (@PathVariable("review-id") long reviewId) {

        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllComment (@PathVariable("page") int page,
                                        @PathVariable("size") int size) {


        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteComment (@PathVariable("review-id") long reviewId) {


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
