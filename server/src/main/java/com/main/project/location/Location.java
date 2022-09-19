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

    @Column
    private Enum<State> locationState ;

    @Column
    private String city;




    @OneToMany(mappedBy ="foodType", cascade = CascadeType.ALL)
    private List<Restaurant> restaurants = new ArrayList<>();

//    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
//    private List<WebUser> usersOfLocation = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();







}
