package com.lxp.content.course.infrastructure.persistence.read.repository;

import com.lxp.content.course.infrastructure.persistence.read.entity.CourseReadJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReadJpaRepository extends JpaRepository<CourseReadJpaEntity, String> {
}
