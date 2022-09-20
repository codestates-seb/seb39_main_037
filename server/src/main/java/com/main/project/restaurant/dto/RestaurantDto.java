package com.main.project.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDto { // DB에 저장할 내용
    private long restaurantId;
    private String restaurantName;          // 식당명
    private String category;                // 푸드타입
    private String description;             // 식당 설명
    private String restaurantPhone;         // 전화번호
    private String address;                 // 주소
    private String readAddress;             // 도로명
    private int mapx;
    private int mapy;
//    private String homePageLink;           홈페이지 주소
}
