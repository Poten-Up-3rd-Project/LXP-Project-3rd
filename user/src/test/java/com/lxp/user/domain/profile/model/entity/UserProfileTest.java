package com.lxp.user.domain.profile.model.entity;

import com.lxp.user.domain.common.model.vo.UserId;
import com.lxp.user.domain.profile.model.vo.LearnerLevel;
import com.lxp.user.domain.profile.model.vo.Tags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class UserProfileTest {

    private UserId userId;
    private LearnerLevel initialLevel;
    private Tags initialTags;
    private String initialJob;

    @BeforeEach
    void setUp() {
        userId = UserId.create();
        initialLevel = LearnerLevel.EXPERT;
        initialTags = Mockito.mock(Tags.class);
        initialJob = "Software Engineer";
    }

    // --- UserProfile.create() 테스트 ---

    @Test
    @DisplayName("UserProfile 생성 테스트")
    void create_ShouldCreateUserProfileWithGivenValues() {
        // when
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);

        // then
        assertAll(
            () -> assertNotNull(userProfile, "UserProfile 객체는 null이 아니어야 합니다."),
            () -> assertEquals(userId, userProfile.userId(), "UserId는 일치해야 합니다."),
            () -> assertEquals(initialLevel, userProfile.level(), "LearnerLevel은 일치해야 합니다."),
            () -> assertEquals(initialTags, userProfile.tags(), "Tags는 일치해야 합니다."),
            () -> assertEquals(initialJob, userProfile.job(), "Job은 일치해야 합니다.")
        );
    }

    @Test
    @DisplayName("UserProfile 생성 시 필수 필드 누락 검증 (Null Check)")
    void create_ShouldThrowNPEWhenRequiredFieldsAreNull() {
        // given
        UserId nullUserId = null;
        LearnerLevel nullLevel = null;
        Tags nullTags = null;

        // when & then
        assertAll(
            // 💡 UserProfile.create(userId, initialLevel, initialTags, initialJob)를 검증하는 로직은 유효하지 않으므로 삭제

            () -> assertThrows(NullPointerException.class,
                () -> UserProfile.create(nullUserId, initialLevel, initialTags, initialJob),
                "UserId가 null이면 NullPointerException이 발생해야 합니다."
            ),

            () -> assertThrows(NullPointerException.class,
                () -> UserProfile.create(userId, nullLevel, initialTags, initialJob),
                "LearnerLevel이 null이면 NullPointerException이 발생해야 합니다."
            ),

            () -> assertThrows(NullPointerException.class,
                () -> UserProfile.create(userId, initialLevel, nullTags, initialJob),
                "Tags가 null이면 NullPointerException이 발생해야 합니다."
            )
            // job은 필수 필드가 아니므로 null을 전달해도 예외가 발생하면 안 됩니다.
        );
    }

    // --- UserProfile.update() 테스트 ---

    @Test
    @DisplayName("UserProfile 정보 업데이트 테스트")
    void update_ShouldUpdateLevelTagsAndJob() {
        // given
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);

        LearnerLevel newLevel = LearnerLevel.MIDDLE;
        List<Long> newTagsList = Arrays.asList(5L, 6L, 7L);
        String newJob = "Lead Developer";

        Tags updatedTags = Mockito.mock(Tags.class);
        given(initialTags.withTags(newTagsList)).willReturn(updatedTags);

        // when
        userProfile.update(newLevel, newTagsList, newJob);

        // then
        assertAll(
            () -> assertEquals(newLevel, userProfile.level(), "LearnerLevel이 업데이트되어야 합니다."),
            () -> assertEquals(updatedTags, userProfile.tags(), "Tags는 withTags()의 결과를 받아 업데이트되어야 합니다."),
            () -> assertEquals(newJob, userProfile.job(), "Job이 업데이트되어야 합니다.")
        );

        then(initialTags).should().withTags(newTagsList);
    }

    @Test
    @DisplayName("UserProfile 업데이트 시 LearnerLevel이 null인 경우 예외 발생")
    void update_ShouldThrowNPEWhenLevelIsNull() {
        // given
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);
        List<Long> tags = Collections.emptyList();
        String job = "Job";

        // when & then
        assertThrows(NullPointerException.class, () -> userProfile.update(null, tags, job),
            "LearnerLevel이 null이면 NullPointerException이 발생해야 합니다.");
    }

    @Test
    @DisplayName("UserProfile 업데이트 시 tags가 null인 경우")
    void update_ShouldHandleNullTagsList() {
        // given
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);
        LearnerLevel newLevel = LearnerLevel.MIDDLE;
        String newJob = "New Job";

        Tags tagsHandlingNull = Mockito.mock(Tags.class);

        // 💡 withTags(null) 호출 시 반환될 Mock 객체 지정
        given(initialTags.withTags(null)).willReturn(tagsHandlingNull);

        // when & then
        // update 메서드 내부에서 tags 인수가 바로 Objects.requireNonNull() 검증 대상이 아니므로 예외가 발생하면 안 됩니다.
        assertDoesNotThrow(() -> userProfile.update(newLevel, null, newJob),
            "Tags 리스트가 null이어도 update 메소드 자체에서 바로 예외가 발생하면 안 됩니다.");

        // then (상태 검증)
        assertAll(
            () -> assertEquals(newLevel, userProfile.level(), "LearnerLevel이 업데이트되어야 합니다."),
            // Tags 필드가 Mock 객체가 반환한 값으로 업데이트되었는지 확인
            () -> assertEquals(tagsHandlingNull, userProfile.tags(), "Tags 필드가 withTags(null)의 결과로 업데이트되어야 합니다.")
        );

        // withTags(null)이 정확히 호출되었는지 검증
        then(initialTags).should().withTags(null);
    }

    @Test
    @DisplayName("UserProfile 업데이트 시 job이 null인 경우")
    void update_ShouldAcceptNullJob() {
        // given
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);
        LearnerLevel newLevel = LearnerLevel.EXPERT;
        List<Long> tags = Arrays.asList(1L);

        // when
        userProfile.update(newLevel, tags, null);

        // then
        assertNull(userProfile.job(), "Job은 null로 업데이트될 수 있어야 합니다.");
    }

    @Test
    @DisplayName("Getter 메소드 검증")
    void getters_ShouldReturnCorrectValues() {
        // when
        UserProfile userProfile = UserProfile.create(userId, initialLevel, initialTags, initialJob);

        // then
        assertAll(
            () -> assertEquals(userId, userProfile.userId(), "userId()는 정확한 UserId를 반환해야 합니다."),
            () -> assertEquals(initialLevel, userProfile.level(), "level()은 정확한 LearnerLevel을 반환해야 합니다."),
            () -> assertEquals(initialTags, userProfile.tags(), "tags()는 정확한 Tags를 반환해야 합니다."),
            () -> assertEquals(initialJob, userProfile.job(), "job()은 정확한 job을 반환해야 합니다.")
        );
    }
}
