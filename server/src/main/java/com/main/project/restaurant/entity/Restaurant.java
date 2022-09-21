package com.main.project.restaurant.entity;


import com.main.project.foodType.FoodType;
import com.main.project.location.Location;
import com.main.project.review.entity.Review;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurant")
public class Restaurant extends MemoryRestaurant{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long restaurantId;

    @Column
    private String restaurantName;
    @Column
    private String category; // 푸드 타입- 로직 구현
    @Column
    private String address;
    @Column
    private String restaurantPhone;
    @Column
    private String RestaurantDescription;

    private double aveStar;


    @ManyToOne
    @JoinColumn(name = "foodType_Id")
    private FoodType foodType;

    @ManyToOne
    @JoinColumn(name="location_Id")
    private Location location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();


}
