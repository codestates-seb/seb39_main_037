package com.main.project.badge.service;

import com.main.project.badge.entity.UserBadge;
import com.main.project.badge.entity.Badge;
import com.main.project.badge.repository.BadgeRepository;
import com.main.project.badge.repository.UserBadgeRepository;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BadgeServiceImpl implements  BadgeService{



    
    UserRepository userRepository;
    BadgeRepository badgeRepository;
    UserBadgeRepository userBadgeRepository;

    public BadgeServiceImpl(UserRepository userRepository, BadgeRepository badgeRepository, UserBadgeRepository userBadgeRepository) {
        this.userRepository = userRepository;
        this.badgeRepository = badgeRepository;
        this.userBadgeRepository = userBadgeRepository;
    }

    @Override
    public Badge makeNewBadge(Badge badge) { // 뱃지 저장 추가(담기)
        badge.setBadgeName(badge.getBadgeName());
        badge.setDescription(badge.getDescription());
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

    public void assignReviewBadge(long userId) {//첫 리뷰작성 후 기념 뱃지 제공

        WebUser foundUser = userRepository.findByUserId(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        int howManyReviews = foundUser.getReviews().size();

        if(howManyReviews == 1 ) {
            reviewBadgeAssign(foundUser, 5);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
        }else if(howManyReviews == 5 ) {
            reviewBadgeAssign(foundUser,6);//뱃지 번호 2번은 5번째 리뷰 작성시 주는 뱃지
        }
        else if(howManyReviews == 10){
            reviewBadgeAssign(foundUser,7);//뱃지 번호 3번은 10번째 리뷰 작성시 주는 뱃지
        }

    }

    private void reviewBadgeAssign(WebUser foundUser, long bageId) {

        Badge foundReviewBadge = findBadge(bageId);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
        UserBadge newBadge = new UserBadge();
        newBadge.setBadge(foundReviewBadge);
        newBadge.setWebUser(foundUser);
        userBadgeRepository.save(newBadge);//첫리뷰 배지를 받은 유저를 저장
    }

    public void assignCommentBadge(long userId) {//첫 리뷰작성 후 기념 뱃지 제공

        WebUser foundUser = userRepository.findByUserId(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_IS_NOT_EXIST));
        int howManyComments = foundUser.getComments().size();

        if(howManyComments == 1 ) {
            commentBadgeAssign(foundUser,1);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
        }else if(howManyComments == 5 ) {
            commentBadgeAssign(foundUser,2);//뱃지 번호 2번은 5번째 리뷰 작성시 주는 뱃지
        }
        else if(howManyComments == 10){
            commentBadgeAssign(foundUser,3);//뱃지 번호 3번은 10번째 리뷰 작성시 주는 뱃지
        }
    }

    private void commentBadgeAssign(WebUser foundUser, long bageId) {

        Badge foundCommentBadge = findBadge(bageId);//뱃지 번호 1번은 첫 리뷰 작성시 주는 뱃지
        UserBadge newBadge = new UserBadge();
        newBadge.setBadge(foundCommentBadge);
        newBadge.setWebUser(foundUser);
        userBadgeRepository.save(newBadge);//첫리뷰 배지를 받은 유저를 저장
    }






    @Override
    public void removeBadge(long userId) {
        Badge badge = badgeRepository.findById(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.Badge_ID_IS_NOT_CORRECT));
        badgeRepository.delete(badge);
    }
}
