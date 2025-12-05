package com.lxp.user.infrastructure.persistence.adapter;

import com.lxp.user.application.user.port.provided.external.ExternalUserStatusPort;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class StubUserStatusPort implements ExternalUserStatusPort {

    private final UserRepository userRepository;

    public StubUserStatusPort(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getStatusByUserId(String userId) {
        return userRepository.findUserStatusById(UserId.of(userId))
            .orElseThrow(UserNotFoundException::new)
            .name();
    }
}
