package com.main.project.badge.entity;

import com.main.project.badge.UserBadge;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long badgeId;
    String badgename;
    String badgeDescription;

    @Column
    byte badgeImg;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

}
