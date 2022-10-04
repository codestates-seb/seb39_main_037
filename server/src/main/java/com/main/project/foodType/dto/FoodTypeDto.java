package com.main.project.foodType.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FoodTypeDto {


    @Getter
    @NoArgsConstructor
    public static class PostDto {
        String typeName;
        String typeImgUrl;

    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {
        String oldTypeName;
        String newTypeName;

    }



    @Getter
    @Setter
    @NoArgsConstructor
    public static class ResponseDto{
        String foodTypeId;
        String typeName;

    }

}
