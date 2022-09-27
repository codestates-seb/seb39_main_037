package com.main.project.review.service;

import com.main.project.location.entity.Location;
import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    public Review createReview(long userId, long restaurantId, Review review);
    public Review updateReview(long reviewId, long userId, Review review);
    public Review findReview(long reviewId);
    public Page<Review> findAllReview(int page, int size);
    public List<Review> findRestaurantReview(long restaurantId); //식당별 리뷰 조회
    public List<Review> findLocationReview(long locationId); //지역별 리뷰 조회
    public void deleteReview(long reviewId);
    public int updateView(long reviewId);
    public Review findVerifiedReview(long reviewId);
    public List<Review> search(String keyword);
}
