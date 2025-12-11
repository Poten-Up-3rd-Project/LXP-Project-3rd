package com.lxp.user.application.port.adapter;

import com.lxp.api.user.port.dto.result.UserAuthResponse;
import com.lxp.user.domain.user.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserAuthMapper {

    public UserAuthResponse toResponse(User user) {
        return new UserAuthResponse(
            user.id().asString(),
            user.email(),
            user.role().name()
        );
    }
}
