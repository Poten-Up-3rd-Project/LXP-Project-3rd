package com.lxp.user.domain.profile.model.vo;

import java.util.Objects;

public record UserProfileId(Long id) {

    public UserProfileId {
        Objects.requireNonNull(id, "id is null");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileId that = (UserProfileId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
