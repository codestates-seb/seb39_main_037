package com.main.project.restaurant.repository;

import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r fROM Restaurant r WHERE r.restaurantName LIKE %:title%")
    Page<Restaurant> findByRestaurantNameContaining(@Param("title") String title, Pageable pageable); // 사용자가 검색했을때 나오는 식당 값
     @Query("SELECT r FROM Restaurant r WHERE r.location.id = :locationId")
     Page<Restaurant> findByLocation(long locationId, Pageable pageable);

    Optional<Object> findByCategoryContaining(String category);
}
