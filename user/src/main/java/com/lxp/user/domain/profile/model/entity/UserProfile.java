package com.lxp.user.domain.profile.model.entity;

import com.lxp.common.domain.model.BaseEntity;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.vo.LearnerLevel;
import com.lxp.user.domain.profile.model.vo.Tags;
import com.lxp.user.domain.profile.model.vo.UserProfileId;

import java.util.Objects;

public class UserProfile extends BaseEntity<UserProfileId> {

    private UserProfileId id;
    private UserId userId;
    private LearnerLevel level;
    private Tags tags;
    private String job;

    private UserProfile(UserProfileId id, UserId userId, LearnerLevel level, Tags tags, String job) {
        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.level = Objects.requireNonNull(level);
        this.tags = Objects.requireNonNull(tags);
        this.job = job;
    }

    public static UserProfile create(UserProfileId id, UserId userId, LearnerLevel level, Tags tags, String job) {
        return new UserProfile(id, userId, level, tags, job);
    }

    @Override
    public UserProfileId getId() {
        return id;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUserIdForString() {
        return userId.getValue().toString();
    }

    public LearnerLevel getLevel() {
        return level;
    }

    public Tags getTags() {
        return tags;
    }

    public String getJob() {
        return job;
    }
}
