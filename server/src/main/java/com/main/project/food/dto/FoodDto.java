package com.main.project.food.dto;

import com.main.project.foodType.FoodType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FoodDto {




    @Getter
    @Setter
    @NoArgsConstructor
    public static class PostDto{

        private String foodName;

        private String foodTypeName;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetDto{

        private String foodType;
    }





    @Getter
    @Setter
    @NoArgsConstructor
    public static class ResponseDto {

        private String foodName;

        private FoodType foodType;

    }



}
