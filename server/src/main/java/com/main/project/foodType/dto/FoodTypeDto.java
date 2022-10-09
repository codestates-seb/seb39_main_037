package com.main.project.foodType.dto;

import lombok.AllArgsConstructor;
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
    @AllArgsConstructor
    public static class ResponseDtoWithType {
        long foodTypeId;
        String typeName;
        String imgUrl;

    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDtoWithFood {
        long foodId;
        String foodName;
    }

}
