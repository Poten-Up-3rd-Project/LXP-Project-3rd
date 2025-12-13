package com.lxp.user.application.port.provided.dto;

import com.lxp.user.application.port.required.dto.TagResult;

import java.util.List;

public record UserInfoDto(
    String id,
    String name,
    String email,
    List<TagResult> tags,
    String level,
    String job
) {
}
