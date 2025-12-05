package com.lxp.user.infrastructure.persistence.entity;

import com.lxp.common.infrastructure.persistence.BaseUuidJpaEntity;
import com.lxp.user.domain.user.model.vo.UserRole;
import com.lxp.user.domain.user.model.vo.UserStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class JpaUser extends BaseUuidJpaEntity {

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    protected JpaUser() {
    }

    private JpaUser(String id, String name, String email, UserRole role, UserStatus userStatus) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.role = role;
        this.userStatus = userStatus;
    }

    public static JpaUser of(UUID id, String name, String email, UserRole role, UserStatus userStatus) {
        return new JpaUser(id.toString(), name, email, role, userStatus);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
