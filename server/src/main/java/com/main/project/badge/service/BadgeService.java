package com.main.project.badge.service;

import com.main.project.badge.entity.Badge;
import org.springframework.data.domain.Page;
import java.util.*;

public interface BadgeService {


    public Badge makeNewBadge(Badge badge);

    public Badge findBadge(long badgeid);

    public List<Badge> findAllBadges();

    public void assignBadge(long userid);// DB에 저장된 뱃지를 회원에게 할당(뱃지별 할당 조건 체크)

    public Badge removeBadge();// 특정 뱃지 삭제



}
