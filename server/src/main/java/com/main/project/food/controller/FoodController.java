package com.main.project.food.controller;


import com.main.project.entity.Multi_ResponseDTOwithPageInfo;
import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import org.springframework.data.domain.*;
import com.main.project.food.mapper.FoodMapper;
import com.main.project.food.service.FoodServiceImple;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.restaurant.mapper.RestaurantMapper;
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

    RestaurantMapper restaurantMapper;

    public FoodController(FoodServiceImple foodService, FoodMapper foodMapper, RestaurantMapper restaurantMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
        this.restaurantMapper = restaurantMapper;
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
    public ResponseEntity getFoods(@RequestParam String foodType){

        List<Food> threeRandomFood = foodService.random3Foods(foodType);
        List<FoodDto.random3ResponseDto> responseDtos =  threeRandomFood.stream().map(Food -> new FoodDto.random3ResponseDto(Food.getFoodId(),Food.getFoodName(),Food.getFoodType().getTypeName())).collect(Collectors.toList());


      return  new ResponseEntity(responseDtos, HttpStatus.OK );
    }



    @GetMapping("/randoms")
    public ResponseEntity getFoods(@RequestParam  List<String> foodTypes){

//          List filterTypes = List.of(getMultiDto.getFoodTypes());

        List<Food> threeRandomFood = foodService.random3FoodsByManyFilter(foodTypes);

        List<FoodDto.random3ResponseDto> responseDtos =  threeRandomFood.stream().map(Food -> new FoodDto.random3ResponseDto(Food.getFoodId(),Food.getFoodName(),Food.getFoodType().getTypeName())).collect(Collectors.toList());


        return new ResponseEntity(responseDtos, HttpStatus.OK );

    }


    @GetMapping("/find/{food-id}/{location-id}/{page}")
    public ResponseEntity getFoodsByRestaurant(@PathVariable("food-id") long foodId, @PathVariable("location-id") long locationId,@PathVariable("page") int page) {
        List<Restaurant> restaurants = foodService.findRestaurantByFood(foodId, locationId);
        Page pageRestaurant = new PageImpl(restaurants, PageRequest.of(page, 10, Sort.by("restaurantId").descending()), restaurants.size());

        return new ResponseEntity(new Multi_ResponseDTOwithPageInfo<>(restaurantMapper.restaurantsToRestaurantPatchResponseDtos(restaurants),pageRestaurant), HttpStatus.OK);

    }


}
