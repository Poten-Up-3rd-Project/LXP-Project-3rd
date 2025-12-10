package com.lxp.user.application.port.provided.external;

import java.util.Optional;

@FunctionalInterface
public interface ExternalUserStatusPort {

    Optional<String> getStatusByUserId(String userId);
}
