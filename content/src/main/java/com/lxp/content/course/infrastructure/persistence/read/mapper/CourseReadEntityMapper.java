package com.lxp.content.course.infrastructure.persistence.read.mapper;

import com.lxp.common.application.port.out.DomainMapper;
import com.lxp.common.enums.Level;
import com.lxp.content.course.application.port.required.dto.CourseReadModel;
import com.lxp.content.course.infrastructure.persistence.read.entity.CourseReadJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseReadEntityMapper implements DomainMapper<CourseReadModel, CourseReadJpaEntity> {
    @Override
    public CourseReadModel toDomain(CourseReadJpaEntity entity) {
        return new CourseReadModel(
                entity.getUuid(),
                entity.getInstructorId(),
                entity.getInstructorName(),
                entity.getThumbnail(),
                entity.getTitle(),
                entity.getDescription(),
                Level.valueOf(entity.getDifficulty()),
                entity.getTagIds(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    @Override
    public CourseReadJpaEntity toEntity(CourseReadModel domain) {
        return CourseReadJpaEntity.builder()
                .uuid(domain.uuid())
                .instructorId(domain.instructorId())
                .instructorName(domain.instructorName())
                .thumbnail(domain.thumbnailUrl())
                .title(domain.title())
                .description(domain.description())
                .difficulty(domain.difficulty().name())
                .tagIds(domain.tags())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
