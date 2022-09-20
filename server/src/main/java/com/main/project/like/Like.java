package com.main.project.like;


import com.main.project.review.Review;
import com.main.project.user.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Like {

    @Id
    long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    Review review;
}
