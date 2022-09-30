package com.main.project.comment.service;


import com.main.project.comment.entity.Comment;
import com.main.project.comment.repository.CommentRepository;
import com.main.project.exception.BusinessLogicException;
import com.main.project.exception.ExceptionCode;
import com.main.project.review.entity.Review;
import com.main.project.review.service.ReviewServiceImpl;
import com.main.project.user.entity.WebUser;
import com.main.project.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    UserService userService;
    ReviewServiceImpl reviewServiceImpl;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, ReviewServiceImpl reviewServiceImpl) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.reviewServiceImpl = reviewServiceImpl;
    }

    public Comment createComment(long userId, long reviewId, Comment comment) {

        comment.addUser(userService.findUser(userId));

        if(reviewId!=0)  {
            comment.addReview(reviewServiceImpl.findVerifiedReview(reviewId));
        }
        return  commentRepository.save(comment);

    }

    public Comment updateComment(long commentId, long userId, Comment comment) {

        Comment foundComment = commentRepository.findById(commentId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_IS_NOT_EXIST));
        //작성자와 수정자가 같은지 체크
        if(userId == foundComment.getWebUser().getUserId()) {
            //수정할 사항이 있는지 확인
            Optional.ofNullable(comment.getCommentBody())
                    .ifPresent(foundComment::setCommentBody);
            return commentRepository.save(foundComment);
        } else {

            new BusinessLogicException(ExceptionCode.WRITER_IS_NOT_MATCH);
        }
        return null;
    }

    public Page<Comment> findUserComment(WebUser user, int page) {

        return commentRepository.findByWebUser(user, PageRequest.of(page, 10, Sort.by("commentId").descending()));
    }

    public Page<Comment> findReviewComment(Review review, int page, int size) {

        return commentRepository.findAllByReview(review, PageRequest.of(page,size, Sort.by("commentId").descending()));
    }

    public void deleteComment(long commentId) {

        commentRepository.findById(commentId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_IS_NOT_EXIST));

        commentRepository.deleteById(commentId);


    }
}
