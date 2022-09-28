package com.main.project.restaurant.service;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {
    public RestaurantDto searchApi(String query);
//    public RestaurantDto add(RestaurantDto restaurantDto); //add 내용을 search에 추가 - 검색하면 저장
    public List<Restaurant> search(String title);
    public Page<Restaurant> findAll(int page, int size);
    public Restaurant findRestaurant(long restaurantId);
    public void delete(long restaurantId);
    public Restaurant addAveStar(long restaurantId);  //평균 별점 저장 로직
}
