package com.lxp.user.application.service;

import com.lxp.user.application.port.provided.command.ExecuteSearchUserCommand;
import com.lxp.user.application.port.provided.dto.UserInfoDto;
import com.lxp.user.application.port.provided.usecase.SearchUserProfileUseCase;
import com.lxp.user.application.port.required.UserTagQueryPort;
import com.lxp.user.application.port.required.dto.TagResult;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.entity.UserProfile;
import com.lxp.user.domain.user.model.entity.User;
import com.lxp.user.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchUserProfileService implements SearchUserProfileUseCase {

    private final UserRepository userRepository;
    private final UserTagQueryPort userTagQueryPort;

    @Override
    public UserInfoDto execute(ExecuteSearchUserCommand command) {
        User user = userRepository.findAggregateUserById(UserId.of(command.userId()))
            .orElseThrow(UserNotFoundException::new);
        UserProfile profile = user.profile();

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
