package com.lxp.user.application.service;

import com.lxp.user.application.port.provided.command.ExecuteWithdrawUserCommand;
import com.lxp.user.application.port.provided.usecase.WithdrawUserUseCase;
import com.lxp.user.application.port.required.AuthUserWithdrawPort;
import com.lxp.user.application.port.required.dto.WithdrawalRequest;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WithdrawUserService implements WithdrawUserUseCase {

    private final UserRepository userRepository;
    private final AuthUserWithdrawPort authUserWithdrawPort;

    @Override
    @Transactional
    public Void execute(ExecuteWithdrawUserCommand command) {
        User user = userRepository.findUserById(UserId.of(command.userId())).orElseThrow(UserNotFoundException::new);
        user.withdraw();
        userRepository.deactivate(user);

        authUserWithdrawPort.invalidateUser(new WithdrawalRequest(command.userId(), command.cookie()));
        return null;
    }
}
