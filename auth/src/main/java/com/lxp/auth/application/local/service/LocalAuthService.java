package com.lxp.auth.application.local.service;

import com.lxp.auth.application.local.handler.UserInfoSearchPortHandler;
import com.lxp.auth.application.local.port.provided.policy.AuthenticationConverter;
import com.lxp.auth.application.local.port.required.command.HandleLoginCommand;
import com.lxp.auth.application.local.port.required.command.HandleUserInfoCommand;
import com.lxp.auth.application.local.port.required.command.HandleUserRegisterCommand;
import com.lxp.auth.application.local.port.required.usecase.AuthenticateUserUseCase;
import com.lxp.auth.application.local.port.required.usecase.RegisterUserUseCase;
import com.lxp.auth.domain.common.exception.AuthErrorCode;
import com.lxp.auth.domain.common.exception.LoginFailureException;
import com.lxp.auth.domain.common.model.vo.TokenClaims;
import com.lxp.auth.domain.common.policy.JwtPolicy;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.policy.PasswordPolicy;
import com.lxp.auth.domain.local.repository.LocalAuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocalAuthService implements AuthenticateUserUseCase, RegisterUserUseCase {

    private final LocalAuthRepository localAuthRepository;
    private final PasswordPolicy passwordPolicy;
    private final JwtPolicy jwtPolicy;
    private final AuthenticationConverter authenticationConverter;
    private final UserInfoSearchPortHandler userInfoSearchPortHandler;

    public LocalAuthService(LocalAuthRepository localAuthRepository,
                            PasswordPolicy passwordPolicy,
                            JwtPolicy jwtPolicy,
                            AuthenticationConverter authenticationConverter,
                            UserInfoSearchPortHandler userInfoSearchPortHandler) {
        this.localAuthRepository = localAuthRepository;
        this.passwordPolicy = passwordPolicy;
        this.jwtPolicy = jwtPolicy;
        this.authenticationConverter = authenticationConverter;
        this.userInfoSearchPortHandler = userInfoSearchPortHandler;
    }

    @Override
    public String authenticate(HandleLoginCommand command) {
        LocalAuth auth = localAuthRepository.findByLoginIdentifier(command.email())
            .orElseThrow(() -> new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS));

        if (!passwordPolicy.isMatch(command.password(), auth.getHashedPassword())) {
            throw new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        HandleUserInfoCommand info = userInfoSearchPortHandler.getUserInfo(auth.getIdValue());
        TokenClaims claims = authenticationConverter.convertToClaims(info.userId(), info.email(), info.role());

        return jwtPolicy.createToken(claims);
    }

    @Override
    public void register(HandleUserRegisterCommand command) {
        log.info("회원가입 로직 시작");
        String hashedPassword = passwordPolicy.apply(command.password());
        localAuthRepository.save(LocalAuth.of(command.email(), hashedPassword));
    }
}
