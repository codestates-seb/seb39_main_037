package com.main.project.badge;

import com.main.project.badge.entity.Badge;
import com.main.project.user.entity.WebUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBadgeId;

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

//    public void addBadge(Badge badge) {
//        this.badge = badge;
//        if (!this.badge.getUserBadges().contains(this)) {
//            this.badge.addBadgeUser(this);
//        }
//    }

}
