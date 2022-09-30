package com.main.project.restaurant.service;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RestaurantService {
    public Page<Restaurant> searchApi(String query);
//    public RestaurantDto add(RestaurantDto restaurantDto); //add 내용을 search에 추가 - 검색하면 저장
    public Page<Restaurant> search(String title, int page);
    public Page<Restaurant> findAll(int page, int size);
    public Restaurant findRestaurant(long restaurantId);
    public void delete(long restaurantId);
}
