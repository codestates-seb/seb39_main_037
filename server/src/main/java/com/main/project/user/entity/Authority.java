package com.main.project.user.entity;

import lombok.Getter;

public enum Authority {
    REGULAR_USER("일반 계정"),
    ADMIN_USER("관리자 게정");


    @Getter
    private final String authority;


    Authority(String authority) {
        this.authority =authority;
    }
}
