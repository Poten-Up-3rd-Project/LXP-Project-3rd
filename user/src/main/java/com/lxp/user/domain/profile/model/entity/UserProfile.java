package com.lxp.user.domain.profile.model.entity;

import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.vo.LearnerLevel;
import com.lxp.user.domain.profile.model.vo.Tags;

import java.util.List;
import java.util.Objects;

public class UserProfile {

    private UserId userId;
    private LearnerLevel level;
    private Tags tags;
    private String job;

    private UserProfile(UserId userId, LearnerLevel level, Tags tags, String job) {
        this.userId = Objects.requireNonNull(userId, "userId는 null일 수 없습니다.");
        this.level = Objects.requireNonNull(level, "level은 null일 수 없습니다.");
        this.tags = Objects.requireNonNull(tags, "tags는 null일 수 없습니다.");
        this.job = job;
    }

    //todo 추후 도메인 서비스에서 user가 활성화 상태인지 여부 체크
    public static UserProfile create(UserId userId, LearnerLevel level, Tags tags, String job) {
        return new UserProfile(userId, level, tags, job);
    }

    //todo 추후 도메인 서비스에서 user가 활성화 상태인지 여부 체크
    public void update(LearnerLevel level, List<Long> tags, String job) {
        this.level = Objects.requireNonNull(level, "level은 null일 수 없습니다.");
        this.tags = this.tags.withTags(tags);
        this.job = job;
    }

    public UserId userId() {
        return this.userId;
    }

    public LearnerLevel level() {
        return this.level;
    }

    public Tags tags() {
        return this.tags;
    }

    public String job() {
        return this.job;
    }

}
