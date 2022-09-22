package com.main.project.badge.entity;

import com.main.project.badge.UserBadge;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long badgeId;
    String badgename;
    String badgeDescription;

    @Lob
    @Column
    byte[] badgeImg;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    public void addBadgeUser(UserBadge UserBadge) {
        this.userBadges.add(UserBadge);

    }



}
