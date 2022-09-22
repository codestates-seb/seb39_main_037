package com.main.project.user.repository;

import com.main.project.user.entity.WithdrawalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalUserRepository extends JpaRepository<WithdrawalUser, Long> {

}
