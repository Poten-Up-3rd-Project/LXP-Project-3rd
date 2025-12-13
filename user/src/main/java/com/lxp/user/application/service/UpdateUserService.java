package com.lxp.user.application.service;

import com.lxp.common.enums.Level;
import com.lxp.user.application.port.provided.command.ExecuteUpdateUserCommand;
import com.lxp.user.application.port.provided.dto.UserInfoDto;
import com.lxp.user.application.port.provided.usecase.UpdateUserProfileUseCase;
import com.lxp.user.application.port.required.UserTagQueryPort;
import com.lxp.user.application.port.required.dto.TagResult;
import com.lxp.user.domain.common.exception.UserInactiveException;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.entity.UserProfile;
import com.lxp.user.domain.user.exception.UserRoleNotFoundException;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.model.vo.UserName;
import com.lxp.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserProfileUseCase {

    private final UserRepository userRepository;
    private final UserTagQueryPort userTagQueryPort;

    @Override
    @Transactional(rollbackFor = UserNotFoundException.class)
    public UserInfoDto execute(ExecuteUpdateUserCommand command) {
        User user = userRepository.findAggregateUserById(UserId.of(command.userId()))
            .orElseThrow(UserNotFoundException::new);
        UserProfile profile = user.profile();

        if (!user.isActive()) {
            throw new UserInactiveException("비활성화된 사용자는 정보를 업데이트할 수 없습니다.");
        }

        UserName userName = command.name() == null ? null : UserName.of(command.name());
        Level learnerLevel = Level.fromString(command.level()).orElseThrow(UserRoleNotFoundException::new);

        user.update(userName, learnerLevel, command.tags(), command.job());

        userRepository.saveWithProfile(user);

        List<TagResult> tagResults = userTagQueryPort.findTagByIds(profile.tags().values());

        return new UserInfoDto(
            command.userId(),
            user.name(),
            user.email(),
            tagResults,
            profile.level().name(),
            profile.job()
        );
    }
}
