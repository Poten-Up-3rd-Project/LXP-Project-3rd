package com.lxp.user.presentation.rest.dto.response;

import com.lxp.user.application.port.provided.dto.UserInfoDto;
import com.lxp.user.application.port.required.dto.TagResult;

import java.util.List;

public record UserProfileResponse(
    String userId,
    String email,
    String name,
    List<TagResult> tags, // redis 연결 후 tagDto로 변경할 것
    String level
) {

    public static UserProfileResponse to(UserInfoDto userInfoDto) {
        return new UserProfileResponse(
            userInfoDto.id(),
            userInfoDto.email(),
            userInfoDto.name(),
            userInfoDto.tags(),
            userInfoDto.level()
        );
    }

}
