package com.lxp.auth.application.local.service;

import com.lxp.auth.application.local.command.HandleLoginCommand;
import com.lxp.auth.application.local.command.HandleUserRegisterCommand;
import com.lxp.auth.application.local.usecase.AuthenticateUserUseCase;
import com.lxp.auth.application.local.usecase.RegisterUserUseCase;
import com.lxp.auth.domain.common.exception.AuthErrorCode;
import com.lxp.auth.domain.common.exception.LoginFailureException;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.policy.PasswordPolicy;
import com.lxp.auth.domain.local.repository.LocalAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocalAuthService implements AuthenticateUserUseCase, RegisterUserUseCase {

    private final LocalAuthRepository localAuthRepository;
    private final PasswordPolicy passwordPolicy;

    public LocalAuthService(LocalAuthRepository localAuthRepository, PasswordPolicy passwordPolicy) {
        this.localAuthRepository = localAuthRepository;
        this.passwordPolicy = passwordPolicy;
    }

    @Override
    public void authenticate(HandleLoginCommand command) {
        LocalAuth auth = localAuthRepository.findByLoginIdentifier(command.email())
            .orElseThrow(() -> new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS));

        if (!passwordPolicy.isMatch(command.password(), auth.getHashedPassword())) {
            throw new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS);
        }
    }

    @Override
    public void register(HandleUserRegisterCommand command) {
        log.info("회원가입 로직 시작");
        String hashedPassword = passwordPolicy.apply(command.password());
        localAuthRepository.save(LocalAuth.of(command.email(), hashedPassword));
    }
}
