package com.main.project.food.controller;


import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.food.service.FoodServiceImple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
@RestController
@RequestMapping("/select/dish")
public class FoodController {

    FoodServiceImple foodService;
    FoodMapper foodMapper;

    public FoodController(FoodServiceImple foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    @PostMapping("/post")
    public ResponseEntity postFood(@RequestBody FoodDto.PostDto postDto){

        Food newAddedFood = foodService.addFood(postDto);
        FoodDto.ResponseDto responseDto = foodMapper.foodToResponseDto(newAddedFood);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }





    @GetMapping("/random")
    public List<Food> getFoods(@RequestBody FoodDto.GetDto getDto){



        List<Food> threeRandomFood = foodService.random3Foods(getDto.getFoodType());
      return threeRandomFood;
    }
}
