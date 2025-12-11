package com.lxp.content.course.infrastructure.external.adapter;

import com.lxp.api.user.port.dto.result.UserInfoResponse;
import com.lxp.api.user.port.external.ExternalUserInfoPort;
import com.lxp.content.course.application.port.required.UserQueryPort;
import com.lxp.content.course.application.port.required.dto.InstructorInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoAdapter implements UserQueryPort {

    private final ExternalUserInfoPort externalUserInfoPort;

    @Override
    public InstructorInfo getInstructorInfo(String userId) {
        UserInfoResponse userInfo = externalUserInfoPort.userInfo(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        return new InstructorInfo(
            userInfo.userId(),
            userInfo.name(),
            userInfo.role(),
            userInfo.status()
        );

    }
}
