package com.lxp.auth.application.local.service;

import com.lxp.api.user.port.dto.command.UserRegisterCommand;
import com.lxp.auth.application.local.port.provided.command.HandleRegisterAuthCommand;
import com.lxp.auth.application.local.port.provided.usecase.RegisterUserUseCase;
import com.lxp.auth.application.local.port.required.UserSavePort;
import com.lxp.auth.application.local.port.required.dto.UserRegistrationInfo;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.model.vo.HashedPassword;
import com.lxp.auth.domain.local.policy.PasswordPolicy;
import com.lxp.auth.domain.local.repository.LocalAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LocalRegisterService implements RegisterUserUseCase {

    private final LocalAuthRepository localAuthRepository;
    private final PasswordPolicy passwordPolicy;
    private final UserSavePort userSavePort;

    @Override
    public Void execute(HandleRegisterAuthCommand command) {
        log.info("회원가입 로직 시작");

        HashedPassword hashedPassword = passwordPolicy.apply(command.password());
        LocalAuth register = LocalAuth.register(command.email(), hashedPassword);
        localAuthRepository.save(register);

        userSavePort.save(new UserRegistrationInfo(
            register.userId().value(),
            command.name(),
            command.email(),
            command.role(),
            command.tags(),
            command.level(),
            command.job()
        ));
        return null;
    }

}
