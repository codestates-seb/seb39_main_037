package com.main.project.restaurant.entity;


import com.main.project.food.entity.Food;
import com.main.project.location.entity.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RestaurantFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long restaurantFoodId;

    @ManyToOne
    @JoinColumn(name="restaurant_Id")
    Restaurant restaurant;;

    @ManyToOne
    @JoinColumn(name = "Location_ID")
    Location location;


    @ManyToOne
    @JoinColumn(name="food_Id")
    Food food;



}
