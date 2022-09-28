package com.main.project.thumbUp.service;

import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.thumbUp.repository.ThumbUpRepository;
import com.main.project.user.entity.WebUser;
import com.main.project.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class ThumbUpServiceImpl implements ThumbUpService{

    private final ThumbUpRepository thumbUpRepository;
    ReviewRepository reviewRepository;
    UserService userService;

    public ThumbUpServiceImpl(ThumbUpRepository thumbUpRepository, ReviewRepository reviewRepository, UserService userService) {
        this.thumbUpRepository = thumbUpRepository;
        this.reviewRepository = reviewRepository;
        this.userService = userService;
    }

    public boolean createThumbUp(WebUser user, long reviewId) {

        //리뷰 존재 확인
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        //중복 좋아요 방지
        if (isNotAlreadyLike(user, review)) {
            thumbUpRepository.save(new ThumbUp(user, review));
            return true;
        }

        return false;
    }

    public List<String> count(long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow();
        WebUser user = review.getWebUser();

        Integer likeCount = thumbUpRepository.countByReview(review).orElse(0);

        List<String> result =
                new ArrayList<>(Arrays.asList(String.valueOf(likeCount)));

        if (Objects.nonNull(user.getUserId())) {
            result.add(String.valueOf(isNotAlreadyLike(user, review)));
            return result;
        }

        return result;
    }

    public void deleteThumbUp(WebUser user, long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow();
        ThumbUp thumbUp = thumbUpRepository.findByWebUserAndReview(user, review).orElseThrow();

        thumbUpRepository.delete(thumbUp);
    }

//  좋아요 중복 체크
    private boolean isNotAlreadyLike(WebUser user, Review review) {
        return thumbUpRepository.findByWebUserAndReview(user, review).isEmpty();
    }
    public Page<ThumbUp> findUserLike(WebUser user, int page) {
        return thumbUpRepository.findByWebUser(user, PageRequest.of(page, 10, Sort.by("thumbUpId").descending()));
    }
}
