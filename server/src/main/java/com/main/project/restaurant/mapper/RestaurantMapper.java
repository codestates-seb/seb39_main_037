package com.main.project.restaurant.mapper;


import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.dto.RestaurantResponseDto;
import com.main.project.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    List<RestaurantResponseDto> restaurantsToRestaurantResponseDtos(List<Restaurant> restaurants);

    default public RestaurantResponseDto restaurantToRestaurantResponseDto(Restaurant restaurant) {

        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setRestaurantId( restaurant.getRestaurantId() );
        restaurantResponseDto.setRestaurantName( restaurant.getRestaurantName() );
        restaurantResponseDto.setCategory( restaurant.getCategory() );
        restaurantResponseDto.setRestaurantPhone( restaurant.getRestaurantPhone() );
        restaurantResponseDto.setAddress( restaurant.getAddress() );
        restaurantResponseDto.setAveTaste( restaurant.getAveTaste() );
        restaurantResponseDto.setAveFacility( restaurant.getAveFacility() );
        restaurantResponseDto.setAvePrice( restaurant.getAvePrice() );
        restaurantResponseDto.setMapx(restaurant.getMapx());
        restaurantResponseDto.setMapy(restaurant.getMapy());

        return restaurantResponseDto;
    }
}
