package com.main.project.location.repository;

import com.main.project.location.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    State findByStateName(String stateName);
}
