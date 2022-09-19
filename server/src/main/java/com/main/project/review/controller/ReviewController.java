package com.main.project.review.controller;


import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.entity.Review;
import com.main.project.review.mapper.ReviewMapper;
import com.main.project.review.service.ReviewServiceImpl;
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

    private final ReviewServiceImpl reviewServiceImpl;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewServiceImpl reviewServiceImpl, ReviewMapper reviewMapper) {
        this.reviewServiceImpl = reviewServiceImpl;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping("/post")
    public ResponseEntity postReview (@Valid @RequestBody ReviewPostDto reviewPostDto) {

        long userId = reviewPostDto.getUserId();
        long foodId = reviewPostDto.getFoodId();
        long restaurantId = reviewPostDto.getRestaurantId();
        Review review = reviewServiceImpl.createReview(userId, foodId, restaurantId, reviewMapper.reviewPostDtoToReview(reviewPostDto));


        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchReview (@Valid @RequestBody ReviewPatchDto reviewPatchDto) {

        Review editReview = reviewMapper.reviewPatchDtoToReview(reviewPatchDto);
        Review review = reviewServiceImpl.updateReview(reviewPatchDto.getUserId(),editReview);
        System.out.println(reviewMapper.reviewToReviewResponseDto(review).toString());

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.OK);
    }

    @GetMapping("/{review-id}")
    public ResponseEntity getReview (@PathVariable("review-id") long reviewId) {

        Review review = reviewServiceImpl.findReview(reviewId);
        reviewServiceImpl.updateView(reviewId);

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity getAllReview (@PathVariable("page") int page) {

        int size =10;
        Page<Review> pageReview = reviewServiceImpl.findAllReview(page - 1, size);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(reviewMapper.reviewsToReviewResponseDtos(reviews),
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteReview (@PathVariable("review-id") long reviewId) {

        reviewServiceImpl.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}