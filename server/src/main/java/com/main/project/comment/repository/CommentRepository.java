package com.main.project.comment.repository;

import com.main.project.comment.entity.Comment;
import com.main.project.review.entity.Review;
import com.main.project.user.entity.WebUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByWebUser(WebUser user);
    Page<Comment> findByReview(long reviewId, Pageable pageable);
}
