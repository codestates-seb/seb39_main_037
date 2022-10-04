package com.main.project.foodType.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.food.entity.Food;
import com.main.project.foodType.entity.FoodType;

import java.util.List;

public interface FoodTypeService {
    FoodType makeFoodType(String foodTypeName, byte[] foodTypeImg) throws BusinessLogicException;

    FoodType editFoodType(String oldName, String newName);

    FoodType findFoodType(String foodTypeName);

    List<FoodType> findAllFoodType();

    List<Food> findAllFoodByFoodType(FoodType foodType);

    void removeFoodType(FoodType foodType);
}
