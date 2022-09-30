package com.main.project.foodType.mapper;

import com.main.project.foodType.dto.FoodTypeDto;
import com.main.project.foodType.entity.FoodType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodTypeMapper {

    FoodType postDtoToFoodType(FoodTypeDto.PostDto postDto);
    FoodType patchDtoToFoodType(FoodTypeDto.PatchDto patchDto);
    FoodTypeDto.ResponseDto FoodTypeToResponseDto(FoodType foodType);

}
