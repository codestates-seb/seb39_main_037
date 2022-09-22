package com.main.project.foodType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodTypeMapper {

    FoodType postDtoToFoodType(FoodTypeDto.postDto postDto);
    FoodType patchDtoToFoodType(FoodTypeDto.patchDto patchDto);
}
