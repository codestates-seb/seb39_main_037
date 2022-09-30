package com.main.project.location.repository;

import com.main.project.location.entity.City;
import com.main.project.location.entity.Location;
import com.main.project.location.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByState (State state);
    Location findByStateAndCity(State state, City city);
}
