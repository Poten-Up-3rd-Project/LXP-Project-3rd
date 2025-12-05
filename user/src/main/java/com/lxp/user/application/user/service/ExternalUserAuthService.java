package com.lxp.user.application.user.service;

import com.lxp.user.application.user.port.provided.dto.UserAuthResponse;
import com.lxp.user.application.user.port.provided.external.ExternalUserAuthPort;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ExternalUserAuthService implements ExternalUserAuthPort {

    private final UserRepository userRepository;

    public ExternalUserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuthResponse userAuth(String userId) {
        User user = userRepository.findUserById(UserId.of(userId)).orElseThrow(UserNotFoundException::new);
        return UserAuthResponse.of(user);
    }

    @Override
    public UserAuthResponse getUserInfoByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserAuthResponse.of(user);
    }
}
