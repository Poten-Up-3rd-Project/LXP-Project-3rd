package com.lxp.user.domain.profile.model.entity;

import com.lxp.common.domain.event.AggregateRoot;
import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.vo.LearnerLevel;
import com.lxp.user.domain.profile.model.vo.Tags;
import com.lxp.user.domain.profile.model.vo.UserProfileId;

import java.util.List;
import java.util.Objects;

public class UserProfile extends AggregateRoot {

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

    public void update(LearnerLevel level, List<String> tags, String job) {
        this.level = Objects.requireNonNull(level);
        this.tags = this.tags.withTags(tags);
        this.job = job;
    }

    public UserProfileId id() {
        return id;
    }

    public UserId userId() {
        return userId;
    }

    public LearnerLevel level() {
        return level;
    }

    public Tags tags() {
        return tags;
    }

    public String job() {
        return job;
    }
}
