package com.lxp.user.domain.user.model.entity;

import com.lxp.common.domain.model.BaseEntity;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.vo.UserEmail;
import com.lxp.user.domain.user.model.vo.UserName;
import com.lxp.user.domain.user.model.vo.UserRole;
import com.lxp.user.domain.user.model.vo.UserStatus;

import java.util.Objects;

public class User extends BaseEntity<UserId> {

    private UserId id;

    private UserName name;

    private UserEmail email;

    private UserRole role;

    private UserStatus userStatus;

    private User(UserId id, UserName name, UserEmail email, UserRole userRole) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.email = Objects.requireNonNull(email);
        this.role = Objects.requireNonNull(userRole);
        this.userStatus = UserStatus.ACTIVE;
    }

    public static User of (UserId id, UserName name, UserEmail email, UserRole userRole) {
        return new User(id, name, email, userRole);
    }


    public static User createLearner(UserId id, UserName name, UserEmail email) {
        return new User(id, name, email, UserRole.LEARNER);
    }

    public static User createInstructor(UserId id, UserName name, UserEmail email) {
        return new User(id, name, email, UserRole.INSTRUCTOR);
    }

    public static User createAdmin(UserId id, UserName name, UserEmail email) {
        return new User(id, name, email, UserRole.ADMIN);
    }

    public boolean canManageOwnCourse() {
        return this.role == UserRole.INSTRUCTOR || this.role == UserRole.ADMIN;
    }

    public boolean canManageAllCourses() {
        return this.role == UserRole.ADMIN;
    }


    @Override
    public UserId getId() {
        return id;
    }

    public String getIdForString() {
        return id.getValue().toString();
    }

    public String getName() {
        return name.value();
    }

    public String getEmail() {
        return email.value();
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
