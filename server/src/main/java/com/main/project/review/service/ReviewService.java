package com.main.project.review.service;

import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {
    public Review createReview(long userId, long foodId, long restaurantId, Review review);
    public Review updateReview(long reviewId, Review review);
    public Review findReview(long reviewId);
    public Page<Review> findAllReview(int page, int size);
    public void deleteReview(long reviewId);
    public int updateView(long reviewId);
    public Review findVerifiedReview(long reviewId);
}
