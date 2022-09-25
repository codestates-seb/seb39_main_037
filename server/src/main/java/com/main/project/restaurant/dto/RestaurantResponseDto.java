package com.main.project.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class RestaurantResponseDto {
    // 별점 평균, 식당정보, 리뷰 정보
    private long restaurantId;
    private String restaurantName;          // 식당명
    private String category;                // 푸드타입
    private String description;             // 식당 설명
    private String restaurantPhone;         // 전화번호
    private String address;                 // 주소
    private double aveTaste;                // 맛 별점 평균
    private double aveFacility;             // 시설 별점 평균
    private double avePrice;                // 가격 별점 평균
    private long reviewId;
}
