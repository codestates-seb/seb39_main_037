package com.main.project.badge.service;

import com.main.project.badge.UserBadge;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.repository.BadgeRepository;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.user.entity.WebUser;

import java.util.*;
public class    BadgeServiceImpl implements  BadgeService{

    BadgeRepository badgeRepository;

    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    @Override
    public Badge makeNewBadge(Badge badge) {
       Badge newBadge = badgeRepository.save(badge);
        return newBadge;
    }

    @Override
    public Badge findBadge(long badgeId) {
       return badgeRepository.findById(badgeId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.Badge_ID_IS_NOT_CORRECT));
    }

    @Override
    public List<Badge> findAllBadges() {
       List<Badge> allBadge  = badgeRepository.findAll();

       return allBadge;
    }

    public void assignBadge(long userID) {//첫 리뷰작성 후 기념 뱃지 제공

        WebUser foundUser = findUser(userID);
        int howManyReviews = foundUser.getReviews().size();

        if (howManyReviews == 1) {
            Badge firstReviewBadge = badgeService.findBadge(1);
            new UserBadge().addUserAndBage(foundUser, firstReviewBadge);
            userRepository.save(foundUser);//첫리뷰 배지를 받은 유저를 저장
        }

    }

    @Override
    public Badge removeBadge() {
        return null;
    }
}
