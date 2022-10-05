package com.main.project.badge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    String badgeName;
    String description;

    @Lob
    @Column
    byte[] badgeImg;


    @JsonIgnore
    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    public void addBadgeUser(UserBadge UserBadge) {
        this.userBadges.add(UserBadge);

    }



}
