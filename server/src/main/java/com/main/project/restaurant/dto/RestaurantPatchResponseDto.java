package com.main.project.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantPatchResponseDto {
    private long restaurantId;
    private String restaurantName;          // 식당명
    private String category;                // 푸드타입
    private String description;             // 식당 설명
    private String restaurantPhone;         // 전화번호
    private String address;                 // 주소
    private double aveTaste;                // 맛 별점 평균
    private double aveFacility;             // 시설 별점 평균
    private double avePrice;                // 가격 별점 평균
    private int mapx;
    private int mapy;
    private long locationId;
    String foodTypeName;
}
