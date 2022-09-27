package com.main.project.badge.service;

import com.main.project.badge.UserBadge;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.repository.BadgeRepository;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.UserRepository;
import com.main.project.user.service.UserService;
import com.main.project.user.service.UserServieImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BadgeServiceImpl implements  BadgeService{



    
    UserRepository userRepository;
    BadgeRepository badgeRepository;

    public BadgeServiceImpl( UserRepository userRepository, BadgeRepository badgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
    }

    @Override
    public Badge makeNewBadge(Badge badge) { // 뱃지 저장 추가(담기)
        badge.setBadgename(badge.getBadgename());
        badge.setBadgeDescription(badge.getBadgeDescription());
        badge.setBadgeImg(badge.getBadgeImg());
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

    public void assignBadge(long userId) {//첫 리뷰작성 후 기념 뱃지 제공

        WebUser foundUser = userRepository.findByUserId(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        int howManyReviews = foundUser.getReviews().size();

        switch(howManyReviews){
            case 1: reviewBadgeAssign(foundUser,1);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
            case 10: reviewBadgeAssign(foundUser,2);//뱃지 번호 2번은 10번째 리뷰 작성시 주는 뱃지
            case 50: reviewBadgeAssign(foundUser,3);//뱃지 번호 3번은 50번째 리뷰 작성시 주는 뱃지
        }

    }

    private void reviewBadgeAssign(WebUser foundUser, long bageId) {

        Badge firstReviewBadge = findBadge(bageId);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
        new UserBadge().addUserAndBage(foundUser, firstReviewBadge);
        userRepository.save(foundUser);//첫리뷰 배지를 받은 유저를 저장
    }

    @Override
    public void removeBadge(long userId) {
        Badge badge = badgeRepository.findById(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.Badge_ID_IS_NOT_CORRECT));
        badgeRepository.delete(badge);
    }
}
