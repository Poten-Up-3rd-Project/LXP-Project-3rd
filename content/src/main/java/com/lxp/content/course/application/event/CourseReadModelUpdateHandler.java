package com.lxp.content.course.application.event;

import com.lxp.common.infrastructure.cqrs.ReadModelUpdater;
import com.lxp.content.course.application.port.required.CourseReadModelPort;
import com.lxp.content.course.application.port.required.UserQueryPort;
import com.lxp.content.course.application.port.required.dto.CourseReadModel;
import com.lxp.content.course.domain.event.CourseCreatedEvent;
import com.lxp.content.course.domain.event.CrudEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;


@Component
@RequiredArgsConstructor
public class CourseReadModelUpdateHandler implements ReadModelUpdater<CrudEvent> {
    private final CourseReadModelPort courseReadModelPort;
    private final UserQueryPort userQueryPort;

    @Retryable
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void update(CrudEvent event) {
        switch (event.getCrudType()) {
            case CREATED -> onCreate((CourseCreatedEvent) event);
//            case UPDATED -> onUpdate((CourseUpdatedEvent) event);
//            case DELETED -> onDelete((CourseDeletedEvent) event);
        }
    }

    @Override
    public Class<CrudEvent> supportedEventType() {
        return CrudEvent.class;
    }

    private void onCreate(CourseCreatedEvent event) {

        String instructorName = userQueryPort.getInstructorInfo(event.getInstructorUuid())
                .name();
        CourseReadModel model = new CourseReadModel(
                event.getAggregateId(),
                event.getInstructorUuid(),
                instructorName,
                event.getThumbnailUrl(),
                event.getTitle(),
                event.getDescription(),
                event.getDifficulty(),
                event.getTagIds(),
                event.getOccurredAt(),
                event.getOccurredAt()
        );

        courseReadModelPort.save(model);
    }

//    private void onUpdate(CourseUpdatedEvent event) {
//        repository.findByUuid(event.getAggregateId())
//                .ifPresent(entity -> entity.update(event));
//    }
//
//    private void onDelete(CourseDeletedEvent event) {
//        repository.deleteByUuid(event.getAggregateId());
//    }
}
