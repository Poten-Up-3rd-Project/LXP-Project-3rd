package com.lxp.content.course.infrastructure.persistence.read.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

// 나중에 분리
@Table(name = "course_read")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseReadJpaEntity {
    @Id
    private String uuid;
    private String instructorId;
    private String instructorName;
    private String title;
    private String thumbnail;
    private String description;
    private String difficulty;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private List<Long> tagIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
