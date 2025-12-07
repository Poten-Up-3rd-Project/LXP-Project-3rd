package com.lxp.user.domain.profile.model.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Tags(List<String> values) {

    public Tags {
        if (Objects.isNull(values) || values.isEmpty()) {
            values = Collections.emptyList();
        }
        values = List.copyOf(values);
    }

    public Tags withTags(List<String> newTags) {
        return new Tags(newTags);
    }

}
