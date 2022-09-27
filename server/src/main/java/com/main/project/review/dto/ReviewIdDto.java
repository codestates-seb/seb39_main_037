package com.main.project.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ReviewIdDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class locationDto {
        long locationId;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    public static class restaurantDto {
        long restaurantId;
    }
}
