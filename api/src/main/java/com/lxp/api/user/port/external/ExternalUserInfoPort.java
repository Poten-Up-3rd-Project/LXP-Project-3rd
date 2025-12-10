package com.lxp.api.user.port.external;

import com.lxp.api.user.port.dto.result.UserInfoResponse;

@FunctionalInterface
public interface ExternalUserInfoPort {

    UserInfoResponse userInfo(String userId);

}
