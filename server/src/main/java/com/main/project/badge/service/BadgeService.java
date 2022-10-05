package com.main.project.badge.service;

import com.main.project.badge.entity.Badge;

import java.util.*;

public interface BadgeService {


    public Badge makeNewBadge(Badge badge);

    public Badge findBadge(long badgeid);

    public List<Badge> findAllBadges();

    public void assignReviewBadge(long userid);// DB에 저장된 뱃지를 회원에게 할당(뱃지별 할당 조건 체크)

    public void removeBadge(long userId);// 특정 뱃지 삭제



}
