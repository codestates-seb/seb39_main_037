package com.main.project.restaurant.dto;

import com.main.project.location.entity.Location;
import com.main.project.review.entity.Review;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class RestaurantDto { // DB에 저장할 내용
    private long restaurantId;
    private String restaurantName;          // 식당명
    private String category;                // 푸드타입
    private String description;             // 식당 설명
    private String restaurantPhone;         // 전화번호
    private String address;                 // 주소
    private int mapx;
    private int mapy;
    private Location location;
}
