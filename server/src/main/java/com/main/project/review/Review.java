package com.main.project.review;

import com.main.project.food.Food;
import com.main.project.like.Like;
import com.main.project.location.Location;
import com.main.project.restaurant.Restaurant;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long reviewId;

    String reviewTitle;

    String reviewBody;

    int fiveStar;

    int view;

    LocalDateTime createdAt = LocalDateTime.now();

    LocalDateTime updatedAt = LocalDateTime.now();

    long userid;

    long foodTypeId;

    @ManyToOne
    @JoinColumn(name = "location_Id")
    Location location;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<Like> likes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_Id")
    Restaurant restaurant;

    @OneToOne(mappedBy = "review", cascade=CascadeType.ALL)
    Food food;


}
