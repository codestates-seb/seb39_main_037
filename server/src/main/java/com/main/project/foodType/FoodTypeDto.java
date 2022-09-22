package com.main.project.foodType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
public class FoodTypeDto {


    @Getter
    @NoArgsConstructor
    public static class postDto{
        String typeName;
    }

    @Getter
    @NoArgsConstructor
    public static class patchDto{
        String oldTypeName;
        String newTypeName;

    }

}
