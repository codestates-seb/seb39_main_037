package com.main.project.review.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.project.comment.entity.Comment;
import com.main.project.entity.BaseTimeEntity;
import com.main.project.restaurant.entity.Restaurant;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.location.entity.Location;
import com.main.project.user.entity.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "review")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;

    @Column(length = 100, nullable = false)
    private String reviewTitle;

    @Column(nullable = false)
    @Lob
    private String reviewBody;

    @Column(nullable = false)
    private Integer tasteStar;

    @Column(nullable = false)
    private Integer facilityStar;

    @Column(nullable = false)
    private Integer priceStar;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column
    private String reviewImgUrl;

    private long foodTypeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_Id")
    private WebUser webUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "location_Id")
    private Location location;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_Id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ThumbUp> thumbUps = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void addWebUser(WebUser webUser){
        this.webUser = webUser;
    }
    public void addRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

}
