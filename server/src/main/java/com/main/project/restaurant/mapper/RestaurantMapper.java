package com.main.project.restaurant.mapper;


import com.main.project.restaurant.dto.RestaurantDto;
import com.main.project.restaurant.dto.RestaurantResponseDto;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.dto.ReviewPatchDto;
import com.main.project.review.dto.ReviewPostDto;
import com.main.project.review.dto.ReviewResponseDto;
import com.main.project.review.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    List<RestaurantResponseDto> restaurantsToRestaurantDtos(List<Restaurant> restaurants);

    default public RestaurantResponseDto restaurantToRestaurantResponseDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setRestaurantId( restaurant.getRestaurantId() );
        restaurantResponseDto.setRestaurantName( restaurant.getRestaurantName() );
        restaurantResponseDto.setCategory( restaurant.getCategory() );
        restaurantResponseDto.setRestaurantPhone( restaurant.getRestaurantPhone() );
        restaurantResponseDto.setAddress( restaurant.getAddress() );
        restaurantResponseDto.setAveTaste( restaurant.getAveTaste() );
        restaurantResponseDto.setAveFacility( restaurant.getAveFacility() );
        restaurantResponseDto.setAvePrice( restaurant.getAvePrice() );
        restaurantResponseDto.setReview(restaurant.getReviewList());

        return restaurantResponseDto;
    }
}
