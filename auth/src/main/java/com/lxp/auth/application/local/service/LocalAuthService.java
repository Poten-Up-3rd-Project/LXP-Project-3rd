package com.lxp.auth.application.local.service;

import com.lxp.auth.application.local.port.provided.command.HandleLoginCommand;
import com.lxp.auth.application.local.port.provided.policy.AuthenticationConverter;
import com.lxp.auth.application.local.port.provided.usecase.AuthenticateUserUseCase;
import com.lxp.auth.application.local.port.required.UserAuthSearchPort;
import com.lxp.auth.application.local.port.required.dto.AuthDomainInfo;
import com.lxp.auth.domain.common.exception.AuthErrorCode;
import com.lxp.auth.domain.common.exception.LoginFailureException;
import com.lxp.auth.domain.common.model.vo.AuthTokenInfo;
import com.lxp.auth.domain.common.model.vo.TokenClaims;
import com.lxp.auth.domain.common.policy.JwtPolicy;
import com.lxp.auth.domain.local.model.entity.LocalAuth;
import com.lxp.auth.domain.local.policy.PasswordPolicy;
import com.lxp.auth.domain.local.repository.LocalAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalAuthService implements AuthenticateUserUseCase {

    private final LocalAuthRepository localAuthRepository;
    private final PasswordPolicy passwordPolicy;
    private final JwtPolicy jwtPolicy;
    private final AuthenticationConverter authenticationConverter;
    private final UserAuthSearchPort userAuthSearchPort;

    @Override
    public AuthTokenInfo execute(HandleLoginCommand command) {
        LocalAuth auth = localAuthRepository.findByLoginIdentifier(command.email())
            .orElseThrow(() -> new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS));

        if (!passwordPolicy.isMatch(command.password(), auth.hashedPassword())) {
            log.info("비밀번호가 일치하지 않습니다.");
            throw new LoginFailureException(AuthErrorCode.INVALID_CREDENTIALS);
        }

        AuthDomainInfo info = userAuthSearchPort.retrieveAuthorityByUserId(auth.userId().asString());
        TokenClaims claims = authenticationConverter.convertToClaims(info.userId(), info.email(), info.role());

        return jwtPolicy.createToken(claims);
    }

}
