package com.main.project.user.service;

import com.main.project.badge.entity.Badge;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import  java.util.*;
public interface UserService {


    public WebUser registerUser();

    public WebUser editUser();

    public Page<WebUser> findAllUser();

    WebUser registerUser(WebUser newUser);

    WebUser editUser(WebUser editUser);

    Page<WebUser> findAllUser(int page, int size, String sortby /* 정렬기준 */);

    public WebUser findUser();

    public WebUser inActiveUser();

    public Page<WebUser> findUsersByLevel();


    public List<Badge> findUsersBadge();//회원이 가진 뱃지 조회

    public List<ThumbUp> findUsersLikes();//회원이 등록한 좋아요 리뷰 목록 조회




/* ------------------------------------------------------------ */
// 관리자 전용 메서드
    public WebUser findAllUserByMonth();//월별 총 가입자 검색

    public WebUser findAllUserByYear();//연 신규 가입자를 월별로 반환 Ex)1월:11명, ```` 12월 : 243명

    public WebUser findNewUsersByMonth();

    public WebUser findNewUsersByYear();

    // 추가가능 메서드 : 가장 많이 리뷰가 쓰여진 메뉴

    /* ------------------------------------------------------------ */










}
