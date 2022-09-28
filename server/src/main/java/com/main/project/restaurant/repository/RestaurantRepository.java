package com.main.project.restaurant.repository;

import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Modifying
    @Query("SELECT avg(tasteStar) FROM Review")
    double aveTasteStar (long restaurantId);
    @Modifying
    @Query("SELECT avg(facilityStar) FROM Review")
    double aveFacilityStar (long restaurantId);
    @Modifying
    @Query("SELECT avg(priceStar) FROM Review")
    double avePriceStar (long restaurantId);
    List<Restaurant> findByRestaurantNameContaining(String title); // 사용자가 검색했을때 나오는 식당 값
}
