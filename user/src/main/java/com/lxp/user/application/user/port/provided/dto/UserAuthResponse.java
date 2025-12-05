package com.lxp.user.application.user.port.provided.dto;

import com.lxp.user.domain.user.model.entity.User;

public record UserAuthResponse(String userId, String email, String role) {

    public static UserAuthResponse of(User user) {
        return new UserAuthResponse(
            user.getId().getValue().toString(),
            user.getEmail().getValue(),
            user.getRole().name()
        );
    }

}
