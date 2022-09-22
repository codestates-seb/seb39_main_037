package com.main.project.foodType;


import com.main.project.food.entity.Food;
import com.main.project.foodType.repository.FoodTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/food")
public class FoodContorller {

    FoodTypeService foodTypeService;

    public FoodContorller(FoodTypeService foodTypeService) {
        this.foodTypeService = foodTypeService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllFoodtype(){

       List<FoodType> FoodTypeList = foodTypeService.findAllFoodType();

        return new ResponseEntity(FoodTypeList, HttpStatus.FOUND);

    }

}
