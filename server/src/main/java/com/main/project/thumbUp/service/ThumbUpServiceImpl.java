package com.main.project.thumbUp.service;

import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.review.entity.Review;
import com.main.project.review.repository.ReviewRepository;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.thumbUp.repository.ThumbUpRepository;
import com.main.project.user.entity.WebUser;
import com.main.project.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class ThumbUpServiceImpl {

    private final ThumbUpRepository thumbUpRepository;
    ReviewRepository reviewRepository;
    UserRepository userRepository;

    public ThumbUpServiceImpl(ThumbUpRepository thumbUpRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.thumbUpRepository = thumbUpRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public boolean createThumbUp(long userId, long reviewId) {

        //유저 존재 확인
        WebUser user = userRepository.findById(userId).orElseThrow();
        //리뷰 존재 확인
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        //중복 좋아요 방지
        if (isNotAlreadyLike(user, review)) {
            thumbUpRepository.save(new ThumbUp(user, review));
            return true;
        }

        return false;
    }

    public List<String> count(long reviewId, long userId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow();
        WebUser user = userRepository.findById(userId).orElseThrow();

        Integer likeCount = thumbUpRepository.countByReview(review).orElse(0);

        List<String> result =
                new ArrayList<>(Arrays.asList(String.valueOf(likeCount)));

        if (Objects.nonNull(user)) {
            result.add(String.valueOf(isNotAlreadyLike(user, review)));
            return result;
        }

        return result;
    }

    public void deleteThumbUp(long thumbUpId, long userId) {

        ThumbUp thumbUp = thumbUpRepository.findById(thumbUpId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.LIKE_IS_NOT_EXISTS));

//      좋아요 한 유저를 확인 후 좋아요 삭제
        if(thumbUp.getWebUser().getUserId() == userId) {thumbUpRepository.delete(thumbUp);}
        else{
            new BusinessLogicException(ExceptionCode.USER_IS_NOT_MATCH);
        }
    }

//  좋아요 중복 체크
    private boolean isNotAlreadyLike(WebUser user, Review review) {
        return thumbUpRepository.findByWebUserAndReview(user, review).isEmpty();
    }
}
