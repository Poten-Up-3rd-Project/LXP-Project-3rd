package com.lxp.user.domain.user.model.vo;

import com.lxp.common.domain.model.ValueObject;

public final class UserInfoUpdate extends ValueObject {
    private final UserName name;
    private final UserEmail email;

    public UserInfoUpdate(UserName name, UserEmail email) {
        this.name = name;
        this.email = email;
    }

    public UserName getName() {
        return name;
    }

    public UserEmail getEmail() {
        return email;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{name, email};
    }
}
