package com.lxp.api.user.port.external;

@FunctionalInterface
public interface ExternalUserStatusPort {

    String getStatusByUserId(String userId);
}
