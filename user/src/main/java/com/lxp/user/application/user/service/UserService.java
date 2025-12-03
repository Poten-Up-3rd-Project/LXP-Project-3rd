package com.lxp.user.application.user.service;

import com.lxp.user.application.user.command.UserSaveCommand;
import com.lxp.user.application.user.usecase.UserSaveUseCase;
import com.lxp.user.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserSaveUseCase {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserSaveCommand command) {
        userRepository.save(command.toDomain());
    }
}
