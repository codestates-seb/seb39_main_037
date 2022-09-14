package com.main.project.review.service;

import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String input) {

        return null;
    }

    public Review updateReview(String input) {

        return null;
    }

    public Review findReview(String input) {

        return null;
    }

    public Page<Review> findAllReview(int page, int size) {

        return null;
    }

    public void deleteReview(long reviewId) {

    }

    @Transactional
    public int updateView(long reviewId) {
        return reviewRepository.view(reviewId);
    }

}
