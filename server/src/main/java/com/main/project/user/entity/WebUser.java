package com.main.project.user.entity;

import com.main.project.badge.UserBadge;
import com.main.project.thumbUp.ThumbUp;
import com.main.project.location.Location;
import com.main.project.qna.QnA;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import static com.main.project.user.entity.WebUser.authority.REGULAR_USER;

@NoArgsConstructor
@Setter
@Entity
@Getter
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String userName;

    private String nickName;

    @Email
    private String email;

    private String password;

    private Enum<WebUser.authority> authority = REGULAR_USER;

    byte profileImg;

    String profileImgName;
/////

    boolean isUserActive;//유저 데이터 바로 삭제하는 대신 비활성화 -> 일정 기간 지난 후 삭제(1~2년)

    double userLevel = 1.0d;// 유저 활동에 따른 레벨업

////


//    @ManyToOne
//    @JoinColumn(name = "location_Id")
//    private Location location;

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private List<ThumbUp> thumbUps = new ArrayList<>();

    @OneToMany(mappedBy = "qnaUser", cascade = CascadeType.ALL)
    private List<QnA> userQnAs = new ArrayList<>();







    public enum authority {

        REGULAR_USER("일반 계정"),
        ADMIN_USER("관리자 게정");


        @Getter
        private final String authority;


        authority(String authority) {
            this.authority =authority;
        }
    }



}
