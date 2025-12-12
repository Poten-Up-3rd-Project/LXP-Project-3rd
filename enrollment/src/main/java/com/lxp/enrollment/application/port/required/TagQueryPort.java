package com.lxp.enrollment.application.port.required;

import com.lxp.enrollment.application.port.provided.dto.result.TagResult;

import java.util.List;

public interface TagQueryPort {
    List<TagResult> findByIds(List<Long> ids);
}
