package com.main.project.review.entity;

import com.main.project.comment.entity.Comment;
import com.main.project.entity.BaseTimeEntity;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.location.Location;
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
    private long reviewId;

    @Column(length = 100, nullable = false)
    private String reviewTitle;

    @Column(nullable = false)
    private String reviewBody;

    private int fiveStar;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    private long userid;

    private long foodTypeId;


    @ManyToOne
    @JoinColumn(name = "location_Id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "restaurant_Id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ThumbUp> thumbUps = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImg> reviewImgs = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


}
