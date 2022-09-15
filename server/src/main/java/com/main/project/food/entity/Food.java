package com.main.project.food.entity;

import com.main.project.foodType.FoodType;
import com.main.project.review.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Setter
@Getter
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodId;
    String foodName;

    @ManyToOne
    @JoinColumn(name="foodType_Id")
    FoodType foodType;



}
