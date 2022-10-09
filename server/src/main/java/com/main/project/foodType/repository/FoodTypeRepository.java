package com.main.project.foodType.repository;

import com.main.project.foodType.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
     Optional<FoodType> findByTypeNameContaining(String typeName);
     Optional<FoodType> findByTypeName(String typeName);
}
