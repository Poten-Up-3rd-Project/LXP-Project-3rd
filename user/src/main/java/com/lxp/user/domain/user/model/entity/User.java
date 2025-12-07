package com.lxp.user.domain.user.model.entity;

import com.lxp.common.domain.event.AggregateRoot;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.vo.UserEmail;
import com.lxp.user.domain.user.model.vo.UserName;
import com.lxp.user.domain.user.model.vo.UserRole;
import com.lxp.user.domain.user.model.vo.UserStatus;

import java.util.Objects;

public class User extends AggregateRoot {

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

    public static User of(UserId id, UserName name, UserEmail email, UserRole userRole) {
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

    public void withdraw() {
        this.userStatus = UserStatus.DELETED;
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name.value();
    }

    public String email() {
        return email.value();
    }

    public UserRole role() {
        return role;
    }

    public UserStatus userStatus() {
        return userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, role, userStatus);
    }
}
