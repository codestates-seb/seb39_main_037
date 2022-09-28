package com.main.project.food.service;

import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;

import java.util.List;

public interface FoodService {

    public List<Food> random3Foods(String foodType);//유저가 고른 조건에 맞춰 메뉴 3개를 랜덤 추천


    Food addFood(FoodDto.PostDto postDto);
}
