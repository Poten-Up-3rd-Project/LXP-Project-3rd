package com.lxp.enrollment.domain.event;

import com.lxp.common.domain.event.DomainEvent;
import com.lxp.enrollment.domain.model.CourseProgress;
import com.lxp.enrollment.domain.model.vo.CourseId;
import com.lxp.enrollment.domain.model.vo.CourseProgressId;
import com.lxp.enrollment.domain.model.vo.EnrollmentId;
import com.lxp.enrollment.domain.model.vo.UserId;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 강좌 진행 완료 이벤트
 * @param eventId     이벤트 ID
 * @param aggregateId aggregate ID
 * @param userId      사용자 ID
 * @param courseId    강좌 ID
 * @param occurredAt  이벤트 발생 시간
 */
public record ProgressCompletedEvent(
        String eventId,
        String aggregateId,
        UserId userId,
        CourseId courseId,
        LocalDateTime occurredAt
) implements DomainEvent {

    @Override
    public String getEventId() {
        return this.eventId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return this.occurredAt;
    }

    @Override
    public String getAggregateId() {
        return this.aggregateId;
    }

    public static ProgressCompletedEvent of(CourseProgressId courseProgressId, UserId userId, CourseId courseId) {
        return new ProgressCompletedEvent(
                UUID.randomUUID().toString(),
                courseProgressId.toString(),
                userId,
                courseId,
                LocalDateTime.now()
        );
    }
}
