package com.lxp.user.domain.user.model.vo;

import com.lxp.common.domain.model.ValueObject;

import java.util.Objects;

public class UserName extends ValueObject {

    private String value;

    protected UserName() {
    }

    private UserName(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }
        if (value.length() > 12) {
            throw new IllegalArgumentException("사용자 이름은 12자를 초과할 수 없습니다.");
        }
        this.value = value;
    }

    public static UserName of(String value) {
        return new UserName(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
