package com.main.project.foodType;


import com.main.project.food.entity.Food;
import com.main.project.foodType.repository.FoodTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/food")
public class FoodTypeContorller {

    FoodTypeService foodTypeService;


    public FoodTypeContorller(FoodTypeService foodTypeService) {
        this.foodTypeService = foodTypeService;
    }


    @PostMapping("/add")
    public ResponseEntity postFoodType(FoodTypeDto.postDto postDto){
        String newFoodTypeName = postDto.getTypeName();
       FoodType newFoodType  = foodTypeService.makeFoodType(newFoodTypeName);

        return new ResponseEntity(newFoodType, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchFoodType(FoodTypeDto.patchDto patchDto){//기존에 등록한 푸드타입 이름 변경 api
        patchDto.getOldTypeName();


       FoodType newNamedFoodType  = foodTypeService.editFoodType(patchDto.getOldTypeName(), patchDto.getNewTypeName());

        return  new ResponseEntity<>(newNamedFoodType, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllFoodtype(){//foodtype 목록 반환

       List<FoodType> FoodTypeList = foodTypeService.findAllFoodType();

        return new ResponseEntity(FoodTypeList, HttpStatus.FOUND);

    }

    @GetMapping("/all/{foodtype}")
    public ResponseEntity getAllFoodtype(@PathVariable("foodtype") String foodTypeName) {//특정 푸트타입에 속하는 푸드들을 전부 가져오는 api
        FoodType foodType = foodTypeService.findFoodType(foodTypeName);
        List<Food> FoodList = foodTypeService.findAllFoodByFoodType(foodType);
        return new ResponseEntity(FoodList, HttpStatus.FOUND);
    }


    @DeleteMapping("/delete/{foodtype}")
    public ResponseEntity deleteFoodType(@PathVariable("foodtype") String foodTypeName){
        FoodType foodType = foodTypeService.findFoodType(foodTypeName);
        foodTypeService.removeFoodType(foodType);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
