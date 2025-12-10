package com.lxp.user.application.port.provided.external;

import com.lxp.user.application.port.provided.dto.UserInfoResponse;

import java.util.Optional;

@FunctionalInterface
public interface ExternalUserInfoPort {

    Optional<UserInfoResponse> userInfo(String userId);

}
