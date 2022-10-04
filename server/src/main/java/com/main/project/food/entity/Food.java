package com.main.project.food.entity;

import com.main.project.foodType.entity.FoodType;
import com.main.project.restaurant.entity.RestaurantFood;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long foodId;

    @Column(nullable = false)
    String foodName;

    @ManyToOne
    @JoinColumn(name="foodType_Id")
    FoodType foodType;


    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    List<RestaurantFood> foodList = new ArrayList();



}
