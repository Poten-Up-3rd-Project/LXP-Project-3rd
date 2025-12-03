package com.lxp.auth.domain.common.model.vo;


import com.lxp.common.domain.model.ValueObject;

import java.util.UUID;

public class UserId extends ValueObject {

    private final UUID value;

    private UserId(UUID value) {
        this.value = value;
    }

    public static UserId create() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }

    public UUID getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }

}
