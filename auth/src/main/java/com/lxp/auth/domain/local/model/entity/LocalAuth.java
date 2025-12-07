package com.lxp.auth.domain.local.model.entity;

import com.lxp.auth.domain.common.model.vo.UserId;
import com.lxp.auth.domain.local.model.vo.HashedPassword;
import com.lxp.common.domain.event.AggregateRoot;

import java.time.OffsetDateTime;
import java.util.Objects;

public class LocalAuth extends AggregateRoot {

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

    public static LocalAuth register(String loginIdentifier, HashedPassword hashedPassword) {
        return new LocalAuth(UserId.create(), loginIdentifier, hashedPassword, OffsetDateTime.now());
    }

    public static LocalAuth of(UserId id, String loginIdentifier, HashedPassword passwordHash) {
        return new LocalAuth(id, loginIdentifier, passwordHash, OffsetDateTime.now());
    }

    public void updatePassword(final HashedPassword newHashedPassword) {
        this.hashedPassword = Objects.requireNonNull(newHashedPassword, "비밀번호는 null일 수 없습니다.");
        lastPasswordModifiedAt = OffsetDateTime.now();
    }

    public boolean matchesId(UserId id) {
        return this.id.matches(id);
    }

    public boolean matchesId(String id) {
        return this.id.matches(id);
    }

    public UserId userId() {
        return this.id;
    }

    public String getIdValue() {
        return this.id.asString();
    }

    public String loginIdentifier() {
        return loginIdentifier;
    }

    public HashedPassword hashedPassword() {
        return hashedPassword;
    }

    public String hashedPasswordAsString() {
        return hashedPassword.value();
    }

    public OffsetDateTime createdAt() {
        return createdAt;
    }

    public OffsetDateTime lastPasswordModifiedAt() {
        return lastPasswordModifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocalAuth localAuth = (LocalAuth) o;
        return Objects.equals(id, localAuth.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginIdentifier, hashedPassword, createdAt, lastPasswordModifiedAt);
    }
}
