package com.main.project.badge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.project.badge.entity.Badge;
import com.main.project.user.entity.WebUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBadgeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private WebUser webUser;

    @ManyToOne
    @JoinColumn(name = "badge_Id")
    private Badge badge;

    public void addUserAndBage(WebUser webUser, Badge badge) {
        this.webUser = webUser;
        this.badge = badge;
//UserBadge 생성 후 유저의 badge 리스트를 돌며 새로 부여하는 뱃지를 이미 갖고 있는지 stream으로 체크, 받은 적 없다면 추가
        if (this.webUser.getUserBadges().stream()
                .map(UserBadge -> UserBadge.getBadge().equals(badge))
                .equals(false)) {
            this.webUser.getUserBadges().add(this);

        }
    }

}
