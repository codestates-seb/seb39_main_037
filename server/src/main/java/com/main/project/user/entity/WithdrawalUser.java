package com.main.project.user.entity;

import com.main.project.user.entity.WebUser;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
public class WithdrawalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long withdrawalUserId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;
}
