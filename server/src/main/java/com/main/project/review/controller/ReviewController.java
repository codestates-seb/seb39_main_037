package com.main.project.review.controller;


import com.main.project.location.entity.Location;
import com.main.project.location.service.LocationService;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.service.RestaurantServiceImpl;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.entity.Review;
import com.main.project.review.mapper.ReviewMapper;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.review.service.ReviewServiceImpl;
import com.main.project.thumbUp.service.ThumbUpService;
import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
@Validated
@CrossOrigin("*")
public class ReviewController {

    private final ReviewServiceImpl reviewServiceImpl;
    private final ReviewMapper reviewMapper;
    RestaurantServiceImpl restaurantService;
    private final ReviewRepository reviewRepository;
    LocationService locationService;
    ThumbUpService thumbUpService;
    UserService userService;

    public ReviewController(ReviewServiceImpl reviewServiceImpl, ReviewMapper reviewMapper, RestaurantServiceImpl restaurantService, ReviewRepository reviewRepository, LocationService locationService, ThumbUpService thumbUpService, UserService userService) {
        this.reviewServiceImpl = reviewServiceImpl;
        this.reviewMapper = reviewMapper;
        this.restaurantService = restaurantService;
        this.reviewRepository = reviewRepository;
        this.locationService = locationService;
        this.thumbUpService = thumbUpService;
        this.userService = userService;
    }

    @PostMapping("/post/{user-id}")
    public ResponseEntity postReview (@Valid @RequestBody ReviewPostDto reviewPostDto, @AuthenticationPrincipal Authentication webUser) {


        long userId = reviewPostDto.getUserId();
        long restaurantId = reviewPostDto.getRestaurantId();
        Review review = reviewServiceImpl.createReview(userId, restaurantId, reviewMapper.reviewPostDtoToReview(reviewPostDto));

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{review-id}")
    public ResponseEntity patchReview (@Valid @RequestBody ReviewPatchDto reviewPatchDto) {

        Review editReview = reviewMapper.reviewPatchDtoToReview(reviewPatchDto);
        Review review = reviewServiceImpl.updateReview(reviewPatchDto.getReviewId(),reviewPatchDto.getUserId(),editReview);
        System.out.println(reviewMapper.reviewToReviewResponseDto(review).toString());

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.OK);
    }

    @GetMapping("/{review-id}")
    public ResponseEntity getReview (@PathVariable("review-id") long reviewId) {

        Review review = reviewServiceImpl.findReview(reviewId);
        reviewServiceImpl.updateView(reviewId);
        thumbUpService.count(reviewId);

        return new ResponseEntity<>(reviewMapper.reviewToReviewResponseDto(review),
                HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity getAllReview (@PathVariable("page") int page) {

        int size =10;
        Page<Review> pageReview = reviewServiceImpl.findAllReview(page - 1, size);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(reviewMapper.reviewsToReviewResponseDtos(reviews),pageReview),
                HttpStatus.OK);
    }
    @GetMapping("/restaurant/{restaurant-id}/{page}")
    public ResponseEntity getAllRestaurantReview (@PathVariable("restaurant-id") long restaurantId, @PathVariable("page") int page) {

        Restaurant restaurant = restaurantService.findRestaurant(restaurantId);

        Page<Review> pageReview = reviewServiceImpl.findRestaurantReview(restaurantId, page - 1);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(reviewMapper.reviewsToReviewResponseDtos(reviews),pageReview),
                HttpStatus.OK);
    }
    @GetMapping("/location/{location-id}/{page}")
    public ResponseEntity getAllLocationReview (@PathVariable("location-id") long locationId, @PathVariable("page") int page) {

        Location location = locationService.findLocation(locationId);

        Page<Review> pageReview = reviewServiceImpl.findLocationReview(locationId, page - 1);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(reviewMapper.reviewsToReviewResponseDtos(reviews),pageReview),
                HttpStatus.OK);
    }

    @GetMapping("/mypage/{user-id}/{page}")
    public ResponseEntity getAllUserLike (@PathVariable("user-id") long userId, @PathVariable("page") int page) {

        WebUser user = userService.checkUserByUserId(userId);

        Page<Review> pageReview = reviewServiceImpl.findUserReview(user, page - 1);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(reviewMapper.reviewsToReviewResponseDtos(reviews),pageReview),
                HttpStatus.OK);
    }


    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteReview (@PathVariable("review-id") long reviewId) {

        reviewServiceImpl.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    @GetMapping("/search/{page}") // 리뷰 검색기능 구현
    public ResponseEntity search(@RequestParam String title, @PathVariable("page") int page) {

        Page<Review> pageReview = reviewServiceImpl.search(title, page - 1);
        List<Review> reviews = pageReview.getContent();

        return new ResponseEntity<>(new Multi_ResponseDTOwithPageInfo<>(reviewMapper.reviewsToReviewResponseDtos(reviews),pageReview),
                HttpStatus.OK);
    }

}