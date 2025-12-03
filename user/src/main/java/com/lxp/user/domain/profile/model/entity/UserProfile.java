package com.lxp.user.domain.profile.model.entity;

import com.lxp.common.domain.model.BaseEntity;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.vo.LearnerLevel;

import java.util.Objects;

public class UserProfile extends BaseEntity<UserId> {

    private UserId id;
    private LearnerLevel level;

    private UserProfile(UserId id, LearnerLevel level) {
        this.id = Objects.requireNonNull(id);
        this.level = Objects.requireNonNull(level);
    }

    @Override
    public UserId getId() {
        return id;
    }
}
