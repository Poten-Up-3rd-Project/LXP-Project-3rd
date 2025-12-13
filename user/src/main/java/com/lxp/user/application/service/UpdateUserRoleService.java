package com.lxp.user.application.service;

import com.lxp.api.auth.port.dto.result.AuthenticationResponse;
import com.lxp.user.application.port.required.AuthTokenProviderPort;
import com.lxp.user.application.port.provided.command.UpdateUserRoleCommand;
import com.lxp.user.application.port.provided.usecase.UpdateUserRoleUseCase;
import com.lxp.user.application.port.required.dto.AuthTokenResult;
import com.lxp.user.application.port.required.dto.TokenRegenerationDto;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateUserRoleService implements UpdateUserRoleUseCase {

    private final UserRepository userRepository;
    private final AuthTokenProviderPort authTokenProviderPort;

    @Override
    public AuthTokenResult execute(UpdateUserRoleCommand command) {
        User user = userRepository.findUserById(UserId.of(command.userId()))
            .orElseThrow(UserNotFoundException::new);
        user.makeInstructor();
        userRepository.save(user);

        return authTokenProviderPort.regenerateToken(new TokenRegenerationDto(command.userId(), user.email(), user.role().name(), command.token()));
    }
}
