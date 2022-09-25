package com.main.project.location;


import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationdId;

    @ManyToOne
    @JoinColumn(name = "state_Id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_Id")
    private City cities;

    @OneToMany(mappedBy ="foodType", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();







}
