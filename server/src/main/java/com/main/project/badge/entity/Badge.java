package com.main.project.badge.entity;

import com.main.project.badge.UserBadge;

import javax.persistence.*;
import java.util.*;

@Entity
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long badgeId;
    String badgename;
    String badgeDescription;

    byte badgeImg;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

}
