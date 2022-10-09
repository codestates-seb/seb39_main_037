package com.main.project.food.mapper;

import com.main.project.food.dto.FoodDto;
import com.main.project.food.entity.Food;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {

    Food postDtoToFood(FoodDto.PostDto postDto);

    FoodDto.ResponseDto foodToResponseDto(Food food);

}
