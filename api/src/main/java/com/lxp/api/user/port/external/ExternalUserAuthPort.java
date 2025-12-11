package com.lxp.api.user.port.external;

import com.lxp.api.user.port.dto.result.UserAuthResponse;

import java.util.Optional;

public interface ExternalUserAuthPort {

    Optional<UserAuthResponse> userAuth(String userId);

    Optional<UserAuthResponse> getUserInfoByEmail(String email);

}
