package com.lxp.user.infrastructure.persistence.adapter;

import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.model.vo.UserEmail;
import com.lxp.user.domain.user.model.vo.UserName;
import com.lxp.user.domain.user.model.vo.UserStatus;
import com.lxp.user.domain.user.repository.UserRepository;
import com.lxp.user.infrastructure.persistence.entity.JpaUser;
import com.lxp.user.infrastructure.persistence.repository.JpaUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserPersistenceAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<UserStatus> findUserStatusById(UserId userId) {
        return jpaUserRepository.findUserStatusById(userId.getValue().toString());
    }

    @Override
    public Optional<User> findUserById(UserId userId) {
        return jpaUserRepository.findById(userId.getValue().toString()).map(this::toDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public void save(User user) {
        jpaUserRepository.save(toEntity(user));
    }

    private JpaUser toEntity(User user) {
        return JpaUser.of(
            user.getId().getValue(),
            user.getName().getValue(),
            user.getEmail().getValue(),
            user.getRole(),
            user.getUserStatus()
        );
    }

    private User toDomain(JpaUser jpaUser) {
        return User.of(
            UserId.of(jpaUser.getId()),
            UserName.of(jpaUser.getName()),
            UserEmail.of(jpaUser.getEmail()),
            jpaUser.getRole()
        );
    }


}
