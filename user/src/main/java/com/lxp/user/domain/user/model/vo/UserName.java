package com.lxp.user.domain.user.model.vo;

import java.util.Objects;

public record UserName(String value) {

    public UserName {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("사용자 이름은 필수입니다.");
        }
        if (value.length() > 5) {
            throw new IllegalArgumentException("사용자 이름은 5자를 초과할 수 없습니다.");
        }
    }

    public static UserName of(String value) {
        return new UserName(value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(value, userName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
