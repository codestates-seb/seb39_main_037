package com.main.project.restaurant.mapper;


import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    List<RestaurantDto> restaurantsToRestaurantDtos(List<RestaurantDto> restaurants);
}
