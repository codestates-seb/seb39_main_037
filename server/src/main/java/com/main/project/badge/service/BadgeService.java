package com.main.project.badge.service;

import com.main.project.badge.entity.Badge;
import org.springframework.data.domain.Page;

public interface BadgeService {


    public Badge makeNewBadge();

    public Badge findBadge();

    public Page<Badge> findAllBadges();

    public Badge assignBadge();// DB에 저장된 뱃지를 회원에게 할당(뱃지별 할당 조건 체크)

    public Badge removeBadge();// 특정 뱃지 삭제



}
