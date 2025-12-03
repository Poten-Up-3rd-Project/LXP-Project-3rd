package com.lxp.user.infrastructure.persistence.adapter;

import com.lxp.user.application.user.port.provided.UserStatusReader;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class StubUserStatusReader implements UserStatusReader {

    private final UserRepository userRepository;

    public StubUserStatusReader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getStatusByUserId(String userId) {
        return userRepository.findUserStatusById(UserId.of(userId)).name();
    }
}
