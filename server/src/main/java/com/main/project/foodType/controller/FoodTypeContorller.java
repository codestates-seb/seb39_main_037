package com.main.project.foodType.controller;


import com.main.project.entity.Muti_ResponseDTO;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.foodType.entity.FoodType;
import com.main.project.foodType.mapper.FoodTypeMapper;
import com.main.project.foodType.dto.FoodTypeDto;
import com.main.project.foodType.service.FoodTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/foodtype")
public class FoodTypeContorller {

    FoodTypeServiceImpl foodTypeServiceImpl;
    FoodTypeMapper foodTypeMapper;
    FoodMapper foodMapper;

    public FoodTypeContorller(FoodTypeServiceImpl foodTypeServiceImpl, FoodTypeMapper foodTypeMapper, FoodMapper foodMapper) {
        this.foodTypeServiceImpl = foodTypeServiceImpl;
        this.foodTypeMapper = foodTypeMapper;
        this.foodMapper = foodMapper;
    }

    @PostMapping("/add")
    public ResponseEntity postFoodType(@RequestPart FoodTypeDto.PostDto postDto, @RequestPart MultipartFile multipartFile) throws IOException {
        String newFoodTypeName = postDto.getTypeName();
        byte[] foodTypeImg =   multipartFile.getBytes();
       FoodType newFoodType  = foodTypeServiceImpl.makeFoodType(newFoodTypeName, foodTypeImg);

        return new ResponseEntity(foodTypeMapper.FoodTypeToResponseDto(newFoodType), HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchFoodType( FoodTypeDto.PatchDto patchDto){//기존에 등록한 푸드타입 이름 변경 api

       FoodType newNamedFoodType  = foodTypeServiceImpl.editFoodType(patchDto.getOldTypeName(), patchDto.getNewTypeName());

        return  new ResponseEntity<>(foodTypeMapper.FoodTypeToResponseDto(newNamedFoodType), HttpStatus.OK);
    }

    @PatchMapping("/edit/img/{foodtypename}")
    public ResponseEntity patchFoodTypeImg(@PathVariable("foodtypename") String foodTypeName, @RequestPart MultipartFile multipartFile) throws IOException {//기존에 등록한 푸드타입 이름 변경 api

        FoodType newNamedFoodType  = foodTypeServiceImpl.editFoodTypeImg(foodTypeName, multipartFile.getBytes());

        return  new ResponseEntity<>(foodTypeMapper.FoodTypeToResponseDto(newNamedFoodType), HttpStatus.OK);
    }




    @GetMapping("/all")
    public ResponseEntity getAllFoodtype(){//foodtype 목록 반환

       List<FoodType> FoodTypeList = foodTypeServiceImpl.findAllFoodType();
        List<FoodTypeDto.ResponseDto> responseDtos = FoodTypeList
                .stream()
                .map(FoodType -> foodTypeMapper.FoodTypeToResponseDto(FoodType))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtos), HttpStatus.FOUND);

    }

    @GetMapping("/all/{foodtype}")
    public ResponseEntity getAllFoodtype(@PathVariable("foodtype") String foodTypeName) {//특정 푸트타입에 속하는 푸드들을 전부 가져오는 api
        FoodType foodType = foodTypeServiceImpl.findFoodType(foodTypeName);
        List<Food> FoodList = foodTypeServiceImpl.findAllFoodByFoodType(foodType);
        List<FoodDto.ResponseDto> responseDtos = FoodList
                .stream()
                .map(Food -> foodMapper.foodToResponseDto(Food))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtos), HttpStatus.FOUND);
    }


    @DeleteMapping("/delete/{foodtype}")
    public ResponseEntity deleteFoodType(@PathVariable("foodtype") String foodTypeName){
        FoodType foodType = foodTypeServiceImpl.findFoodType(foodTypeName);
        foodTypeServiceImpl.removeFoodType(foodType);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }




}
