package com.main.project.thumbUp.entity;



import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "thumbUp")
public class ThumbUp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //SQLException thumbup id 오류가 떠서 타입을 IDENTITY에서 AUTO로 바꿈
    private long thumbUpId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "review_Id")
    private Review review;

    public ThumbUp(WebUser webUser, Review review){
        this.webUser = webUser;
        this.review = review;
    }
}
