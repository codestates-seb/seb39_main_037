package com.main.project.review.repository;

import com.main.project.location.entity.Location;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Query("UPDATE Review r SET r.view = r.view + 1 WHERE r.reviewId = :reviewId")
    int view (long reviewId);
    @Modifying
    @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId")
    List<Review> findByRestaurant(long restaurantId);
    @Modifying
    @Query("SELECT r FROM Review r WHERE r.location.id = :locationId")
    List<Review> findByLocation(long locationId);
    List<Review> findByReviewTitleContaining(String keyword); // containing 추가, 검색기능 구현
}
