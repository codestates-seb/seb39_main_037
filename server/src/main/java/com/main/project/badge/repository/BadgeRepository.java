package com.main.project.badge.repository;

import com.main.project.badge.entity.Badge;
import com.main.project.foodType.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    Optional<Badge> findByBadgeName(String badgeName);
}
