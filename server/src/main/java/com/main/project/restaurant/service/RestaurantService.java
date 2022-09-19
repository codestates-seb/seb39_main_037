package com.main.project.restaurant.service;

import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    public RestaurantDto search(String query);
    public RestaurantDto add(RestaurantDto restaurantDto);
    public List<RestaurantDto> findAll();
    public Restaurant findRestaurant(long restaurantId);
    public void delete(long restaurantId);
}
