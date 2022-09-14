package com.main.project.review.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/review")
@Validated
@CrossOrigin("*")
public class ReviewController {

    @PostMapping("/post")
    public ResponseEntity postReview (@Valid @RequestBody String inputDto) {


        return new ResponseEntity<>(null,
                HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchReview (@Valid @RequestBody String inputDto) {


        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }

    @GetMapping("/{review-id}")
    public ResponseEntity getReview (@PathVariable("review-id") long reviewId) {

        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllReview (@PathVariable("page") int page,
                                          @PathVariable("size") int size) {


        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteReview (@PathVariable("review-id") long reviewId) {


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
