package com.lxp.user.infrastructure.persistence.repository;

import com.lxp.user.domain.user.model.vo.UserStatus;
import com.lxp.user.infrastructure.persistence.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<JpaUser, String> {

    Optional<UserStatus> findUserStatusById(String userId);

    Optional<JpaUser> findByEmail(String email);
}
