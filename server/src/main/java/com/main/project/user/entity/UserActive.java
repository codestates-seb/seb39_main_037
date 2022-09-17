package com.main.project.user.entity;

import lombok.Getter;

public enum UserActive {

    Active("활동 회원"),
    UnActive("휴면 회원"),
    Aithdrawal("탈퇴 회원");


    @Getter
    private final String isActive;


    UserActive(String isActive) {
        this.isActive =isActive;
    }
}