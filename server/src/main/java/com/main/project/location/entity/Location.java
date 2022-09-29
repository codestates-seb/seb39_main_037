package com.main.project.location.entity;


import com.main.project.restaurant.entity.Restaurant;
import com.main.project.review.entity.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.*;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long locationId;
    @ManyToOne
    @JoinColumn(name = "state_Id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_Id")
    private City city;

    @OneToMany(mappedBy ="foodType", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();








}
