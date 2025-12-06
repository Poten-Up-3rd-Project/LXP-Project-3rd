package com.lxp.user.domain.user.model.vo;

import java.util.Objects;

public record UserInfoUpdate(UserName name, UserEmail email){

    public UserInfoUpdate {
        Objects.requireNonNull(name);
        Objects.requireNonNull(email);
    }

    public String getName() {
        return name.value();
    }

    public String getEmail() {
        return email.value();
    }

}
