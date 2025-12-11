package com.lxp.enrollment.infrastructure.persistence.mapper;


import com.lxp.common.domain.pagination.Page;

import java.util.function.Function;

public final class PageConverter {
    private PageConverter() {}

    public static <T, U> Page<U> toPage(
            org.springframework.data.domain.Page<T> springPage,
            Function<T, U> mapper
    ) {
        if (springPage.isEmpty()) {
            return Page.empty(springPage.getSize());
        }

        var convertedContent = springPage.getContent()
                .stream()
                .map(mapper)
                .toList();

        return Page.of(
                convertedContent,
                springPage.getNumber(),
                springPage.getSize(),
                springPage.getTotalElements()
        );
    }
}
