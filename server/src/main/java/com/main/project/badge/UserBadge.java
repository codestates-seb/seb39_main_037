package com.main.project.badge;

import com.main.project.badge.entity.Badge;
import com.main.project.user.entity.WebUser;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
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

}
