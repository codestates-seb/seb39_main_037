package com.main.project.food.dto;

import lombok.AllArgsConstructor;
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
    public static class PatchDto{

        private String oldFoodName;
        private String newFoodName;

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
    public static class GetMultiDto{

        private String[] foodTypes;

    }





    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto {

        private String foodName;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class random3ResponseDto {

        private String foodName;
        private String foodTypeName;
    }






}
