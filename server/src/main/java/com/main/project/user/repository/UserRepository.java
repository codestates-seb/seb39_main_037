package com.main.project.user.repository;

import com.main.project.user.entity.WebUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<WebUser, Long> {


    Optional<WebUser> findByEmail(String email);
    Optional<WebUser> findByProviderAndProviderId(String provider, String providerId);

    Optional<WebUser> findByUserId(long userid);

    Optional<List<WebUser>> findAllByCreatedAtBetween(LocalDate start, LocalDate end);


}
