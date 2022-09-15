package com.main.project.thumbUp.entity;


import com.main.project.review.entity.Review;
import com.main.project.user.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class ThumbUp {

    @Id
    private long thumbUpId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    private Review review;
}
