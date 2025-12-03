package com.lxp.user.application.user.port.provided;

@FunctionalInterface
public interface UserStatusReader {
    String getStatusByUserId(String userId);
}
