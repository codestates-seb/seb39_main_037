package com.main.project.foodType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.project.food.entity.Food;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "foodType")
public class FoodType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodTypeId;

    @Column(nullable = false)
    String typeName;

    @JsonIgnore
    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Food> foodList = new ArrayList<>();


    @OneToMany(mappedBy = "foodType", cascade = CascadeType.ALL)
    List<Restaurant> restaurant = new ArrayList();



}
