package com.main.project.comment.service;

import com.main.project.comment.entity.Comment;

import java.util.List;

public interface CommentService {
    public Comment createComment(long userId, long reviewId, Comment comment);
    public Comment updateComment(long userId, Comment comment);
    public List<Comment> findAllCommentByUserId(long userId);
    public List<Comment> findAllCommentByReviewId(long reviewId);
    public void deleteComment(long commentId);
}
