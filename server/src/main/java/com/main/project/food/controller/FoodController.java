package com.main.project.food.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.food.service.FoodServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/select/dish")
public class FoodController {

    FoodServiceImple foodService;
    FoodMapper foodMapper;

    public FoodController(FoodServiceImple foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    @PostMapping("/add")
    public ResponseEntity postFood(@RequestBody FoodDto.PostDto postDto){

        Food newAddedFood = foodService.addFood(postDto);
        FoodDto.ResponseDto responseDto = foodMapper.foodToResponseDto(newAddedFood);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity patchFood(@RequestBody FoodDto.PatchDto patchDto){

        Food nameEditedFood = foodService.editFoodInfo(patchDto.getOldFoodName(), patchDto.getNewFoodName());
        FoodDto.ResponseDto responseDto = foodMapper.foodToResponseDto(nameEditedFood);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    @GetMapping("/random")
    public ResponseEntity getFoods(@RequestBody FoodDto.GetDto getDto){

        List<Food> threeRandomFood = foodService.random3Foods(getDto.getFoodType());
        List<FoodDto.random3ResponseDto> responseDtos =  threeRandomFood.stream().map(Food -> new FoodDto.random3ResponseDto(Food.getFoodName(),Food.getFoodType().getTypeName())).collect(Collectors.toList());


      return  new ResponseEntity(responseDtos, HttpStatus.FOUND );
    }



    @GetMapping("/randoms")
    public ResponseEntity getFoods(@RequestBody FoodDto.GetMultiDto getMultiDto){

          List filterTypes = List.of(getMultiDto.getFoodTypes());

        List<Food> threeRandomFood = foodService.random3FoodsByManyFilter(filterTypes);

        List<FoodDto.random3ResponseDto> responseDtos =  threeRandomFood.stream().map(Food -> new FoodDto.random3ResponseDto(Food.getFoodName(),Food.getFoodType().getTypeName())).collect(Collectors.toList());


        return new ResponseEntity(responseDtos, HttpStatus.FOUND );

    }





}
