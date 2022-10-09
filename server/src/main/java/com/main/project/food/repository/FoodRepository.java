package com.main.project.food.repository;

import com.main.project.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "select * from food f where f.food_type_id = :foodName order by rand() limit 3", nativeQuery = true )
     List<Food> findFilteredFoods(@Param("foodName") long foodTypeId);

     Optional<Food> findByFoodName(String foodName);


}
