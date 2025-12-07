package com.lxp.content.course.infrastructure.persistence.repository;

import com.lxp.content.course.infrastructure.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<CourseJpaEntity, Long> {
}
