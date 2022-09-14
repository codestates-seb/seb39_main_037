package com.main.project.review.entity;

import com.main.project.comment.entity.Comment;
import com.main.project.entity.BaseTimeEntity;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.location.Location;
import com.main.project.restaurant.Restaurant;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@NoArgsConstructor
@Setter
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long reviewId;

    String reviewTitle;

    String reviewBody;

    int fiveStar;

    int view;

    long userid;

    long foodTypeId;


    @ManyToOne
    @JoinColumn(name = "location_Id")
    Location location;

    @ManyToOne
    @JoinColumn(name = "restaurant_Id")
    Restaurant restaurant;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<ThumbUp> thumbUps = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<ReviewImg> reviewImgs = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();


}
