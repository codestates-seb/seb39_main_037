package com.main.project.thumbUp;


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
public class ThumbUp {

    @Id
    long thumbUpId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    Review review;
}
