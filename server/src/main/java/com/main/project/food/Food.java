package com.main.project.food;

import com.main.project.foodType.FoodType;
import com.main.project.review.Review;

import javax.persistence.*;


@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodId;
    String foodName;
    String foodTypeId;

    @ManyToOne
    @JoinColumn(name="foodType_Id")
    FoodType foodType;

    @OneToOne(mappedBy = "food", cascade=CascadeType.ALL)
    Review review;

}
