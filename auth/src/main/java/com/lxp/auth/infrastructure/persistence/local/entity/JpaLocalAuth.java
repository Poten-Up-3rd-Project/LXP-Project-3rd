package com.lxp.auth.infrastructure.persistence.local.entity;

import com.lxp.common.infrastructure.persistence.BaseUuidJpaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "local_auths")
@Getter
public class JpaLocalAuth extends BaseUuidJpaEntity {

    @Column(name = "login_identifier")
    private String loginIdentifier;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column
    private OffsetDateTime lastPasswordModifiedAt;

    protected JpaLocalAuth() {
    }

    private JpaLocalAuth(UUID id, String loginIdentifier, String hashedPassword, OffsetDateTime createdAt) {
        super.setId(id.toString());
        this.loginIdentifier = loginIdentifier;
        this.hashedPassword = hashedPassword;
        this.createdAt = createdAt;
    }

    public JpaLocalAuth(UUID id, String loginIdentifier, String hashedPassword, OffsetDateTime createdAt, OffsetDateTime lastPasswordModifiedAt) {
        this(id, loginIdentifier, hashedPassword, createdAt);
        this.lastPasswordModifiedAt = lastPasswordModifiedAt;
    }

    public static JpaLocalAuth of(UUID userId, String loginIdentifier, String hashedPassword) {
        return new JpaLocalAuth(userId, loginIdentifier, hashedPassword, OffsetDateTime.now());
    }

    public static JpaLocalAuth of(UUID userId, String loginIdentifier, String hashedPassword, OffsetDateTime createdAt, OffsetDateTime lastPasswordModifiedAt) {
        return new JpaLocalAuth(userId, loginIdentifier, hashedPassword, createdAt, lastPasswordModifiedAt);
    }
}
