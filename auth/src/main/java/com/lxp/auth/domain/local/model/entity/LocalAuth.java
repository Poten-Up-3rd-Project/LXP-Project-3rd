package com.lxp.auth.domain.local.model.entity;

import com.lxp.auth.domain.common.model.vo.UserId;
import com.lxp.auth.domain.local.model.vo.HashedPassword;
import com.lxp.common.domain.model.BaseEntity;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
public class LocalAuth extends BaseEntity<UserId> {

    private UserId id;
    private String loginIdentifier;
    private HashedPassword hashedPassword;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastPasswordModifiedAt;

    private LocalAuth(UserId id, String loginIdentifier, HashedPassword hashedPassword, OffsetDateTime createdAt) {
        this.id = Objects.requireNonNull(id);
        this.loginIdentifier = Objects.requireNonNull(loginIdentifier);
        this.hashedPassword = Objects.requireNonNull(hashedPassword);
        this.createdAt = createdAt;
    }

    private LocalAuth(UserId id, String loginIdentifier, HashedPassword hashedPassword, OffsetDateTime createdAt, OffsetDateTime lastPasswordModifiedAt) {
        this(id, loginIdentifier, hashedPassword, createdAt);
        this.lastPasswordModifiedAt = lastPasswordModifiedAt;
    }

    public static LocalAuth of(String loginIdentifier, HashedPassword hashedPassword) {
        return new LocalAuth(UserId.create(), loginIdentifier, hashedPassword, OffsetDateTime.now());
    }

    public static LocalAuth of(UserId id, String loginIdentifier, HashedPassword passwordHash) {
        return new LocalAuth(id, loginIdentifier, passwordHash, OffsetDateTime.now());
    }

    public static LocalAuth of(UserId id, String loginIdentifier, HashedPassword passwordHash, OffsetDateTime createdAt, OffsetDateTime lastPasswordModifiedAt) {
        return new LocalAuth(id, loginIdentifier, passwordHash, createdAt, lastPasswordModifiedAt);
    }

    public void updatePassword(final HashedPassword newHashedPassword) {
        this.hashedPassword = Objects.requireNonNull(newHashedPassword, "비밀번호는 null일 수 없습니다.");
        lastPasswordModifiedAt = OffsetDateTime.now();
    }

    @Override
    public UserId getId() {
        return this.id;
    }

    public String getIdValue() {
        return this.id.getValue().toString();
    }

    public String getLoginIdentifier() {
        return loginIdentifier;
    }

    public HashedPassword getHashedPassword() {
        return hashedPassword;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getLastPasswordModifiedAt() {
        return lastPasswordModifiedAt;
    }
}
