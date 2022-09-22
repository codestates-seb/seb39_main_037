package com.main.project.badge;

import javax.persistence.*;
import java.util.*;

@Entity
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long badgeId;
    String badgename;
    String badgeDescription;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

}
