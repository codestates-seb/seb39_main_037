package com.main.project.foodType.controller;


import com.main.project.entity.Muti_ResponseDTO;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.foodType.FoodType;
import com.main.project.foodType.FoodTypeMapper;
import com.main.project.foodType.dto.FoodTypeDto;
import com.main.project.foodType.service.FoodTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food")
public class FoodTypeContorller {

    FoodTypeService foodTypeService;
    FoodTypeMapper foodTypeMapper;
    FoodMapper foodMapper;

    public FoodTypeContorller(FoodTypeService foodTypeService, FoodTypeMapper foodTypeMapper, FoodMapper foodMapper) {
        this.foodTypeService = foodTypeService;
        this.foodTypeMapper = foodTypeMapper;
        this.foodMapper = foodMapper;
    }

    @PostMapping("/add")
    public ResponseEntity postFoodType(@RequestBody FoodTypeDto.PostDto postDto){
        String newFoodTypeName = postDto.getTypeName();
       FoodType newFoodType  = foodTypeService.makeFoodType(newFoodTypeName);

        return new ResponseEntity(foodTypeMapper.FoodTypeToResponseDto(newFoodType), HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchFoodType(@RequestBody FoodTypeDto.PatchDto patchDto){//기존에 등록한 푸드타입 이름 변경 api

       FoodType newNamedFoodType  = foodTypeService.editFoodType(patchDto.getOldTypeName(), patchDto.getNewTypeName());

        return  new ResponseEntity<>(foodTypeMapper.FoodTypeToResponseDto(newNamedFoodType), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllFoodtype(){//foodtype 목록 반환

       List<FoodType> FoodTypeList = foodTypeService.findAllFoodType();
        List<FoodTypeDto.ResponseDto> responseDtos = FoodTypeList
                .stream()
                .map(FoodType -> foodTypeMapper.FoodTypeToResponseDto(FoodType))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtos), HttpStatus.FOUND);

    }

    @GetMapping("/all/{foodtype}")
    public ResponseEntity getAllFoodtype(@PathVariable("foodtype") String foodTypeName) {//특정 푸트타입에 속하는 푸드들을 전부 가져오는 api
        FoodType foodType = foodTypeService.findFoodType(foodTypeName);
        List<Food> FoodList = foodTypeService.findAllFoodByFoodType(foodType);
        List<FoodDto.ResponseDto> responseDtos = FoodList
                .stream()
                .map(Food -> foodMapper.foodToResponseDto(Food))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtos), HttpStatus.FOUND);
    }


    @DeleteMapping("/delete/{foodtype}")
    public ResponseEntity deleteFoodType(@PathVariable("foodtype") String foodTypeName){
        FoodType foodType = foodTypeService.findFoodType(foodTypeName);
        foodTypeService.removeFoodType(foodType);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
