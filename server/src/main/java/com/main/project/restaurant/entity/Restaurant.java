package com.main.project.restaurant.entity;


import com.main.project.foodType.FoodType;
import com.main.project.location.Location;
import com.main.project.review.entity.Review;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;
@NoArgsConstructor
@Setter
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long restaurantId;

    String restaurantName;
    String restaurantAddress;
    String restaurantPhone;


    @ManyToOne
    @JoinColumn(name = "foodType_Id")
    FoodType foodType;

    @ManyToOne
    @JoinColumn(name="location_Id")
    Location location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<Review> reviewList = new ArrayList<>();


}
