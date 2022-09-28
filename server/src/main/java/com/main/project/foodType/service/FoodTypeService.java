package com.main.project.foodType.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.food.entity.Food;
import com.main.project.foodType.FoodType;
import com.main.project.foodType.repository.FoodTypeRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class FoodTypeService {

    FoodTypeRepository foodTypeRepository;


    public FoodTypeService(FoodTypeRepository foodTypeRepository) {
        this.foodTypeRepository = foodTypeRepository;
    }


    public FoodType makeFoodType(String foodTypeName) throws BusinessLogicException {
        if(foodTypeRepository.findByTypeName(foodTypeName).isPresent()) {
            throw new BusinessLogicException(ExceptionCode.FOODTYPE_DUPLICATE);}//기존 타입과 중복되는지 체크

        FoodType newfoodType = new FoodType();
        newfoodType.setTypeName(foodTypeName);

        return foodTypeRepository.save(newfoodType);
    }

    public FoodType editFoodType(String oldName, String newName){
        FoodType foundfoodType =  findFoodType(oldName);
        foundfoodType.setTypeName(newName);
        return foodTypeRepository.save(foundfoodType);
    }

    public FoodType findFoodType(String foodTypeName){
       return foodTypeRepository.findByTypeName(foodTypeName).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST));
    }


    public List<FoodType> findAllFoodType(){
       return foodTypeRepository.findAll();

    }

    public List<Food> findAllFoodByFoodType(FoodType foodType){
       FoodType foundfoodTyp = foodTypeRepository.findByTypeName(foodType.getTypeName()).orElseThrow(() -> new BusinessLogicException(ExceptionCode.FOODTYPE_NOT_EXIST));
       List<Food> foodList =  foundfoodTyp.getFoodList();

       return foodList;
    }

    public void removeFoodType(FoodType foodType) {
        foodTypeRepository.delete(foodType);

    }
}
