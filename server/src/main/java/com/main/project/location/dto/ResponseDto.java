package com.main.project.location.dto;

import com.main.project.location.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ResponseDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class StateResponseDto{
        long stateId;
        String stateName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CityResponseDto{
        long stateId;
        long cityId;
        String stateName;
        String cityName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LocationResponseDto{
//        long stateId;
//        long cityId;
        long locationId;
        String stateName;
        String cityName;


    }


}
