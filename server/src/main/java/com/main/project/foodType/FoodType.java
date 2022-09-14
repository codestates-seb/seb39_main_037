package com.main.project.foodType;

import com.main.project.food.Food;
import com.main.project.restaurant.Restaurant;

import javax.persistence.*;
import java.util.*;

@Entity
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodTypeId;
    String typeName;


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Food> foodList = new ArrayList<>();


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Restaurant> restaurant = new ArrayList();



}
