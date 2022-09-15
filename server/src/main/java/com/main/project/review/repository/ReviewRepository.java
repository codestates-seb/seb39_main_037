package com.main.project.review.repository;

import com.main.project.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("UPDATE Review r SET r.view = r.view + 1 WHERE r.reviewId = :reviewId")
    int view (long reviewId);
}
