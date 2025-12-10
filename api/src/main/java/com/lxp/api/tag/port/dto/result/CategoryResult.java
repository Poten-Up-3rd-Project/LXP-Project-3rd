package com.lxp.api.tag.port.dto.result;

import java.util.List;

public record CategoryResult(
        long tagCategoryId,
        String name,
        String state, // ACTIVE, INACTIVE
        List<TagResult> tags
) {
}
