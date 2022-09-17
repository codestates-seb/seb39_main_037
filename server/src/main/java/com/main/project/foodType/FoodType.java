package com.main.project.foodType;

import com.main.project.food.entity.Food;
import com.main.project.restaurant.entity.Restaurant;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Entity
@Setter
@Table(name = "foodType")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodTypeId;

    @Column(nullable = false)
    String typeName;


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Food> foodList = new ArrayList<>();


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Restaurant> restaurant = new ArrayList();



}
