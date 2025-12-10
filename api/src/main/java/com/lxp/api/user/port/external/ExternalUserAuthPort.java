package com.lxp.api.user.port.external;

import com.lxp.api.user.port.dto.result.UserAuthResponse;

public interface ExternalUserAuthPort {

    UserAuthResponse userAuth(String userId);

    UserAuthResponse getUserInfoByEmail(String email);

}
