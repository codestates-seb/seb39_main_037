package com.main.project.food.controller;


import com.main.project.food.entity.Food;
import com.main.project.food.service.FoodServiceImple;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
@RestController
@RequestMapping("/select/dish")
public class FoodController {

    FoodServiceImple foodService;

    public FoodController(FoodServiceImple foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/post")
    public Food test1() {
      return  foodService.posttest();
    }

    @GetMapping("/test")
    public List<Food> test(){
        List<Food> test1 = foodService.random3Foods();
      return test1;
    }
}
