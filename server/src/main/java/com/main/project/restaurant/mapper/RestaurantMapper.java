package com.main.project.restaurant.mapper;


import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.dto.RestaurantPatchDto;
import com.main.project.restaurant.dto.RestaurantPatchResponseDto;
import com.main.project.restaurant.dto.RestaurantResponseDto;
import com.main.project.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    List<RestaurantResponseDto> restaurantsToRestaurantResponseDtos(List<Restaurant> restaurants);
    List<RestaurantPatchResponseDto> restaurantsToRestaurantPatchResponseDtos(List<Restaurant> restaurant);

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
    default public RestaurantPatchResponseDto restaurantToRestaurantPatchResponseDto(Restaurant restaurant) {

        RestaurantPatchResponseDto restaurantResponseDto = new RestaurantPatchResponseDto();

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
        restaurantResponseDto.setFoodTypeName(restaurant.getFoodType().getTypeName()); // 검색 response로 사용시 null 에러
        restaurantResponseDto.setLocationId(restaurant.getLocation().getLocationId()); // 검색 response로 사용시 null 에러

        return restaurantResponseDto;
    }
}
