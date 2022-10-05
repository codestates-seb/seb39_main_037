package com.main.project.restaurant.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.project.foodType.entity.FoodType;
import com.main.project.location.entity.Location;
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
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long restaurantId;

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
    @Column(columnDefinition = "double default 0")
    private double aveTaste;
    @Column(columnDefinition = "double default 0")
    private double aveFacility;
    @Column(columnDefinition = "double default 0")
    private double avePrice;
    @Column
    private double mapx;
    @Column
    private double mapy;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foodType_Id")
    private FoodType foodType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="location_Id")
    private Location location;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<RestaurantFood> foodList = new ArrayList();

    public void addFoodType(FoodType foodType){
        this.foodType = foodType;
    }
    public void addLocation(Location location) {
        this.location = location;
    }

}
