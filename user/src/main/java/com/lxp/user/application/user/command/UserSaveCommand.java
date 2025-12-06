package com.lxp.user.application.user.command;

import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.exception.UserRoleNotFoundException;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.model.vo.UserEmail;
import com.lxp.user.domain.user.model.vo.UserName;
import com.lxp.user.domain.user.model.vo.UserRole;

import java.util.UUID;

public record UserSaveCommand(UUID userId, String name, String email, String role) {

    public User toDomain() {
        UserRole role = UserRole.fromString(this.role).orElseThrow(UserRoleNotFoundException::new);

        return switch (role) {
            case LEARNER ->
                User.createLearner(UserId.of(this.userId), UserName.of(this.name), UserEmail.of(this.email));
            case INSTRUCTOR ->
                User.createInstructor(UserId.of(this.userId), UserName.of(this.name), UserEmail.of(this.email));
            case ADMIN -> User.createAdmin(UserId.of(this.userId), UserName.of(this.name), UserEmail.of(this.email));
        };
    }

}
