package com.main.project.foodType;

import com.main.project.foodType.dto.FoodTypeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodTypeMapper {

    FoodType postDtoToFoodType(FoodTypeDto.PostDto postDto);
    FoodType patchDtoToFoodType(FoodTypeDto.PatchDto patchDto);
    FoodTypeDto.ResponseDto FoodTypeToResponseDto(FoodType foodType);

}
