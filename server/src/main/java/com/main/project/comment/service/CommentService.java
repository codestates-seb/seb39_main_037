package com.main.project.comment.service;


import com.main.project.comment.entity.Comment;
import com.main.project.comment.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(String input) {

        return null;
    }

    public Comment updateComment(String input) {

        return null;
    }

    public Comment findComment(String input) {

        return null;
    }

    public Page<Comment> findAllComment(int page, int size) {

        return null;
    }

    public void deleteComment(long commentId) {

    }
}
