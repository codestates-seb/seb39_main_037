package com.main.project.foodType;

import com.main.project.food.Food;
import com.main.project.restaurant.Restaurant;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.*;

public class FoodType {

    long foodTypeId;
    String typeName;


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Food> foodList = new ArrayList<>();


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Restaurant> restaurant = new ArrayList();



}
