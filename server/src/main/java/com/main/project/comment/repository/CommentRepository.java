package com.main.project.comment.repository;

import com.main.project.comment.entity.Comment;
import com.main.project.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByWebUser(long userId, Pageable pageable);
    Page<Comment> findByReview(long reviewId, Pageable pageable);
}
