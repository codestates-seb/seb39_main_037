package com.main.project.comment.service;

import com.main.project.comment.entity.Comment;
import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    public Comment createComment(long userId, long reviewId, Comment comment);
    public Comment updateComment(long commentId, long userId, Comment comment);
    public Page<Comment> findUserComment(WebUser user, int page);
    public Page<Comment> findReviewComment(Review review, int page, int size);
    public void deleteComment(long commentId);
}
