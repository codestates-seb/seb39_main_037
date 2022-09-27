package com.main.project.thumbUp.repository;

import com.main.project.review.entity.Review;
import com.main.project.thumbUp.entity.ThumbUp;
import com.main.project.user.entity.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ThumbUpRepository extends JpaRepository<ThumbUp, Long> {

    Optional<Integer> countByReview(Review review);
    Optional<ThumbUp> findByWebUserAndReview(WebUser user, Review review);

    List<ThumbUp> findByWebUser(WebUser user);
}
