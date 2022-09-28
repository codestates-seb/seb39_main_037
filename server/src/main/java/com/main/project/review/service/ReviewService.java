package com.main.project.review.service;

import com.main.project.location.entity.Location;
import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    public Review createReview(long userId, long restaurantId, Review review);
    public Review updateReview(long reviewId, long userId, Review review);
    public Review findReview(long reviewId);
    public Page<Review> findAllReview(int page, int size);
    public Page<Review> findRestaurantReview(long restaurantId, int page); //식당별 리뷰 조회
    public Page<Review> findLocationReview(long locationId, int page); //지역별 리뷰 조회
    public Page<Review> findUserReview(WebUser user, int page);
    public void deleteReview(long reviewId);
    public int updateView(long reviewId);
    public Review findVerifiedReview(long reviewId);
    public Page<Review> search(String keyword, int page);
}
