package com.main.project.user.repository;

import com.main.project.user.entity.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<WebUser, Long> {



}
