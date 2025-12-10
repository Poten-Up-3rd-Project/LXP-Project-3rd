package com.lxp.user.application.port.provided.external;

import com.lxp.user.application.port.provided.dto.UserAuthResponse;

import java.util.Optional;

public interface ExternalUserAuthPort {

    Optional<UserAuthResponse> userAuth(String userId);

    Optional<UserAuthResponse> getUserInfoByEmail(String email);

}
