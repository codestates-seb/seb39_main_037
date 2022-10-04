package com.main.project.restaurant.repository;

import com.main.project.food.entity.Food;
import com.main.project.restaurant.entity.RestaurantFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface RestaurantFoodRepository extends JpaRepository<RestaurantFood, Long> {
    List<RestaurantFood> findAllByFood(Food food);
}
