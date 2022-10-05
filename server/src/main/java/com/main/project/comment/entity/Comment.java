package com.main.project.comment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.main.project.entity.BaseTimeEntity;
import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "comment")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(length = 500, nullable = false)
    private String commentBody;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "review_Id")
    private Review review;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;

    public void addUser(WebUser user){this.webUser = user;}

    public void addReview(Review review){
        this.review = review;
    }
}
