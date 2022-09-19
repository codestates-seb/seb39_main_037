package com.main.project.review.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.restaurant.service.RestaurantServiceImpl;
import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    UserService userService;
    RestaurantServiceImpl restaurantServiceImpl;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, RestaurantServiceImpl restaurantServiceImpl) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.restaurantServiceImpl = restaurantServiceImpl;
    }

    public Review createReview(long userId, long foodId, long restaurantId, Review review) {

        review.addWebUser(userService.findUser(userId));
        review.addRestaurant(restaurantServiceImpl.findRestaurant(restaurantId));
        //food 검증 로직
        verifyReview(review);

        review.setReviewTitle(review.getReviewTitle());
        review.setReviewBody(review.getReviewBody());
        review.setReviewImgs(review.getReviewImgs());
        review.setTasteStar(review.getTasteStar());
        review.setFacilityStar(review.getFacilityStar());
        review.setPriceStar(review.getPriceStar());

        return reviewRepository.save(review);
    }

    public Review updateReview(long reviewId, Review review) {

        //수정할 리뷰가 존재하는지 체크
        Review foundReview = findVerifiedReview(reviewId);
        //작성자와 회원이 동일한지 체크
        if(reviewId == foundReview.getWebUser().getUserId()){
            //수정할 사항(제목, 내용, 별점)이 존재하는지 체크

            Optional.ofNullable(review.getReviewTitle())
                    .ifPresent(foundReview::setReviewTitle);
            Optional.ofNullable(review.getReviewBody())
                    .ifPresent(foundReview::setReviewBody);
            Optional.ofNullable(review.getReviewImgs())
                    .ifPresent(foundReview::setReviewImgs);
            Optional.ofNullable(review.getTasteStar())
                    .ifPresent(foundReview::setTasteStar);
            Optional.ofNullable(review.getFacilityStar())
                    .ifPresent(foundReview::setFacilityStar);
            Optional.ofNullable(review.getPriceStar())
                    .ifPresent(foundReview::setPriceStar);

            return reviewRepository.save(foundReview);
        }else {
            new BusinessLogicException(ExceptionCode.WRITER_IS_NOT_MATCH);
        }
        return null;
    }

    public Review findReview(long reviewId) {

        return findVerifiedReview(reviewId);
    }

    public Page<Review> findAllReview(int page, int size) {

        return reviewRepository.findAll(PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    public void deleteReview(long reviewId) {

        Review review = findVerifiedReview(reviewId);
        reviewRepository.delete(review);

    }

    @Transactional
    public int updateView(long reviewId) {
        return reviewRepository.view(reviewId);
    }

    private void verifyReview(Review review) {
//         회원이 존재하는지 확인
        userService.findUser(review.getWebUser().getUserId());

//         식당이 존재하는지 확인
        restaurantServiceImpl.findRestaurant(review.getRestaurant().getRestaurantId());

//         음식이 존재하는지 확인 (비지니스 로직 구현)
    }

    public Review findVerifiedReview(long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);

        Review findReview = review.orElseThrow(() -> new BusinessLogicException(ExceptionCode.REVIEW_NOT_FOUND));

        return findReview;
    }

}