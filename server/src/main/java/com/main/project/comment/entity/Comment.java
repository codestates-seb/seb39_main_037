package com.main.project.comment.entity;

import com.main.project.entity.BaseTimeEntity;
import com.main.project.review.entity.Review;
import com.main.project.user.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    private String commentBody;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;
}
