package com.main.project.thumbUp.repository;

import com.main.project.thumbUp.entity.ThumbUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThumbUpRepository extends JpaRepository<ThumbUp, Long> {
}
