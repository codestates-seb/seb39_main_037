package com.main.project.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantPatchDto {
    long restaurantId;
    String foodTypeName;
}
