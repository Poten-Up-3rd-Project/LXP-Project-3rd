package com.lxp.auth.infrastructure.persistence.local.adapter;

import com.lxp.auth.domain.common.model.vo.UserId;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.model.vo.HashedPassword;
import com.lxp.auth.infrastructure.persistence.local.entity.JpaLocalAuth;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class LocalAuthDomainMapper {

    public LocalAuth toDomain(JpaLocalAuth jpaLocalAuth) {
        return LocalAuth.of(
            UserId.of(jpaLocalAuth.getId()),
            jpaLocalAuth.getLoginIdentifier(),
            new HashedPassword(jpaLocalAuth.getHashedPassword()),
            jpaLocalAuth.getCreatedAt().atOffset(ZoneOffset.of("+09:00")),
            jpaLocalAuth.getUpdatedAt().atOffset(ZoneOffset.of("+09:00"))
        );
    }

    public JpaLocalAuth toEntity(LocalAuth localAuth) {
        return JpaLocalAuth.of(
            localAuth.userId().value(),
            localAuth.loginIdentifier(),
            localAuth.hashedPasswordAsString()
        );
    }
}
