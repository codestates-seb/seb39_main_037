package com.main.project.user.entity;

import com.main.project.user.entity.WebUser;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Setter
public class WithdrawalUser {

    @Id
    private long withdrawalUserId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private WebUser webUser;
}
