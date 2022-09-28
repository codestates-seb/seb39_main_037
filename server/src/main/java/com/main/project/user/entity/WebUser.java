package com.main.project.user.entity;

import com.main.project.badge.UserBadge;
import com.main.project.comment.entity.Comment;
import com.main.project.review.entity.Review;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.qna.entity.QnA;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@NoArgsConstructor
@Setter
@Entity
@Getter
@Table(name = "webUser")
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column
    private String userName;
    @Column
    private String nickName;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "authority")
    private Authority authority = Authority.REGULAR_USER;

    @Lob
    byte[] profileImg;

    @Column
    String profileImgName;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "is_Activated")
    private UserActive isUserActive =  UserActive.Active;//유저 데이터 바로 삭제하는 대신 비활성화 -> 일정 기간 지난 후 삭제(1~2년)

    @Column
    double userLevel = 1.0d;// 유저 활동에 따른 레벨업

    @Column
    String provider;

    @Column
    String providerId;

    @Column
    LocalDateTime createdAt = LocalDateTime.now();

    @Column
    LocalDateTime updatedAt = LocalDateTime.now();


    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private List<ThumbUp> thumbUps = new ArrayList<>();

    @OneToMany(mappedBy = "qnaUser", cascade = CascadeType.ALL)
    private List<QnA> userQnAs = new ArrayList<>();

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "webUser", cascade = CascadeType.DETACH)
    private List<Review> reviews = new ArrayList<>();


    @Getter
    public enum Authority implements GrantedAuthority {
        REGULAR_USER("일반 계정"),
        ADMIN_USER("관리자 게정");


        @Getter
        private final String authority;


        Authority(String authority) {
            this.authority =authority;
        }
    }


    public enum UserActive {

        Active("활동 회원"),
        UnActive("휴면 회원"),
        Withdrawal("탈퇴 회원");


        @Getter
        private final String isActive;


        UserActive(String isActive) {
            this.isActive =isActive;
        }
    }

}
