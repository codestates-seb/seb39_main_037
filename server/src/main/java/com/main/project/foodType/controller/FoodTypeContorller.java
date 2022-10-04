package com.main.project.foodType.controller;


import com.main.project.entity.Muti_ResponseDTO;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.foodType.entity.FoodType;
import com.main.project.foodType.mapper.FoodTypeMapper;
import com.main.project.foodType.dto.FoodTypeDto;
import com.main.project.foodType.service.FoodTypeServiceImpl;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
       FoodTypeDto.ResponseDtoWithType responseDtoWithType = foodTypeMapper.FoodTypeToResponseDto(newFoodType);
        responseDtoWithType.setImgUrl(uriMaker(newFoodType.getTypeName()));
        return new ResponseEntity(responseDtoWithType, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchFoodType(@RequestBody FoodTypeDto.PatchDto patchDto){//기존에 등록한 푸드타입 이름 변경 api

       FoodType newNamedFoodType  = foodTypeServiceImpl.editFoodType(patchDto.getOldTypeName(), patchDto.getNewTypeName());
        FoodTypeDto.ResponseDtoWithType responseDtoWithType = foodTypeMapper.FoodTypeToResponseDto(newNamedFoodType);
        responseDtoWithType.setImgUrl(uriMaker(newNamedFoodType.getTypeName()));


        return  new ResponseEntity<>(responseDtoWithType, HttpStatus.OK);
    }

    @PatchMapping("/edit/img/{foodtypename}")
    public ResponseEntity patchFoodTypeImg(@PathVariable("foodtypename") String foodTypeName, @RequestPart MultipartFile multipartFile) throws IOException {//기존에 등록한 푸드타입 이름 변경 api

        FoodType newNamedFoodType  = foodTypeServiceImpl.editFoodTypeImg(foodTypeName, multipartFile.getBytes());
        FoodTypeDto.ResponseDtoWithType responseDtoWithType = foodTypeMapper.FoodTypeToResponseDto(newNamedFoodType);
        responseDtoWithType.setImgUrl(uriMaker(newNamedFoodType.getTypeName()));
        return  new ResponseEntity<>(responseDtoWithType, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity getAllFoodtype(){//foodtype 목록 반환

       List<FoodType> FoodTypeList = foodTypeServiceImpl.findAllFoodType();
        List<FoodTypeDto.ResponseDtoWithType> responseDtoWithTypes = FoodTypeList
                .stream()
                .map(FoodType -> new FoodTypeDto.ResponseDtoWithType(FoodType.getFoodTypeId(), FoodType.getTypeName(), uriMaker(FoodType.getTypeName())))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtoWithTypes), HttpStatus.FOUND);

    }

    @GetMapping("/all/{foodtype}")
    public ResponseEntity getAllFoodtype(@PathVariable("foodtype") String foodTypeName) {//특정 푸트타입에 속하는 푸드들을 전부 가져오는 api
        FoodType foodType = foodTypeServiceImpl.findFoodType(foodTypeName);
        List<Food> FoodList = foodTypeServiceImpl.findAllFoodByFoodType(foodType);
        List<FoodDto.ResponseDto> responseDtos = FoodList
                .stream()
                .map(Food -> new FoodDto.ResponseDto(Food.getFoodName()))
                .collect(Collectors.toList());

        return new ResponseEntity(new Muti_ResponseDTO<>(responseDtos), HttpStatus.FOUND);
    }


    @DeleteMapping("/delete/{foodtype}")
    public ResponseEntity deleteFoodType(@PathVariable("foodtype") String foodTypeName){
        FoodType foodType = foodTypeServiceImpl.findFoodType(foodTypeName);
        foodTypeServiceImpl.removeFoodType(foodType);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private String uriMaker(String foodType){

         String test =  ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/foodtype/download/img/")
                .path(foodType)
                 .encode()
                .toUriString();

        return test;
    }

    @GetMapping("/download/img/{foodtypename}")
    public ResponseEntity<Resource> downloadPhoto(@PathVariable("foodtypename") String filename) throws Exception {


        FoodType foodType = foodTypeServiceImpl.findFoodType(filename);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "photo; filename=\"" + foodType.getTypeName()
                                + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/png")
                .body(new ByteArrayResource(foodType.getImage()));

    }
}
