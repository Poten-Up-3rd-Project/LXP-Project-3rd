package com.lxp.content.course.infrastructure.persistence.read.entity;

public record Tag(
        Long id,
        String content,
        String color,
        String variant
) {
}
