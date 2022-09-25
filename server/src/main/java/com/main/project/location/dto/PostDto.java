package com.main.project.location.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;


public class PostDto {

    @Getter
    @NoArgsConstructor
    public static class StatePostDto{
        String stateName;
    }

    @Getter
    @NoArgsConstructor
    public static class CityPostDto{
        String cityName;
    }
    @NoArgsConstructor
    @Getter
    public static class LocationPostDto{
        long stateId;
        long cityId;
    }

}
