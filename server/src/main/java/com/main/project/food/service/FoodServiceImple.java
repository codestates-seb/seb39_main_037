package com.main.project.food.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.repository.FoodRepository;
import com.main.project.foodType.FoodType;
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


    @Override
    public Food addFood(FoodDto.PostDto postDto) {

        if(foodRepository.findByFoodName(postDto.getFoodName()).isPresent()) {
            new BusinessLogicException(ExceptionCode.DUPLICATE_FOOD);
        }
        else{
            Food newFood = new Food();
            newFood.setFoodName(postDto.getFoodName());
            newFood.setFoodType(foodTypeRepository.findByTypeName(postDto.getFoodTypeName()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST)));

           return foodRepository.save(newFood);
        }
        return null;
    }

    public Food posttest(){
       Food food = new Food();
       food.setFoodName("텐동");

        food.setFoodType(foodTypeRepository.findById(1L).get());
        return foodRepository.save(food);

    }


    @Override
    public List<Food> random3Foods(String foodType) {

      FoodType foundfoodType =  foodTypeRepository.findByTypeName(foodType).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST));


        List<Food> threeRandomFoods =  foodRepository.findFilteredFoods(foundfoodType.getFoodTypeId());

        return threeRandomFoods;
    }
}
