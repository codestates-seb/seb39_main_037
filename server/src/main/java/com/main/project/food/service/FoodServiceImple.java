package com.main.project.food.service;

import com.main.project.food.entity.Food;
import com.main.project.food.repository.FoodRepository;
import com.main.project.foodType.repository.FoodTypeRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class FoodServiceImple implements FoodService{

    FoodRepository foodRepository;
    FoodTypeRepository foodTypeRepository;


    public FoodServiceImple(FoodRepository foodRepository, FoodTypeRepository foodTypeRepository) {
        this.foodRepository = foodRepository;
        this.foodTypeRepository = foodTypeRepository;
    }

    public Food posttest(){
       Food food = new Food();
       food.setFoodName("텐동");

        food.setFoodType(foodTypeRepository.findById(1L).get());
        return foodRepository.save(food);

    }


    @Override
    public List<Food> random3Foods() {

        List<Food> test =  foodRepository.findFilteredFoods();

        return test;
    }
}
