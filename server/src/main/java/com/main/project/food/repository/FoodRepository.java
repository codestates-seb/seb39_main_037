package com.main.project.food.repository;

import com.main.project.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    @Query(value = "select * from food order by rand() limit 2", nativeQuery = true )
     List<Food> findFilteredFoods();

}
