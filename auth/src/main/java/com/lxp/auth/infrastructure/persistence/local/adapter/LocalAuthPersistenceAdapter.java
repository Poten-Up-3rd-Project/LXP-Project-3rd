package com.lxp.auth.infrastructure.persistence.local.adapter;

import com.lxp.auth.domain.common.model.vo.UserId;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.repository.LocalAuthRepository;
import com.lxp.auth.infrastructure.persistence.local.entity.JpaLocalAuth;
import com.lxp.auth.infrastructure.persistence.local.repository.JpaLocalAuthRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class LocalAuthPersistenceAdapter implements LocalAuthRepository {

    public final JpaLocalAuthRepository jpaLocalAuthRepository;

    public LocalAuthPersistenceAdapter(JpaLocalAuthRepository jpaLocalAuthRepository) {
        this.jpaLocalAuthRepository = jpaLocalAuthRepository;
    }

    @Override
    public Optional<LocalAuth> findByLoginIdentifier(String loginIdentifier) {
        return jpaLocalAuthRepository.findByLoginIdentifier(loginIdentifier).map(this::toDomain);
    }

    @Override
    public Optional<LocalAuth> findByUserId(UUID userId) {
        return jpaLocalAuthRepository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public void save(LocalAuth localAuth) {
        jpaLocalAuthRepository.save(toEntity(localAuth));
    }

    private JpaLocalAuth toEntity(LocalAuth localAuth) {
        return JpaLocalAuth.of(
            localAuth.getId().getValue(),
            localAuth.getLoginIdentifier(),
            localAuth.getHashedPassword()
        );
    }

    private LocalAuth toDomain(JpaLocalAuth jpaAuth) {
        return LocalAuth.of(
            UserId.of(jpaAuth.getId()),
            jpaAuth.getLoginIdentifier(),
            jpaAuth.getHashedPassword()
        );
    }
}
