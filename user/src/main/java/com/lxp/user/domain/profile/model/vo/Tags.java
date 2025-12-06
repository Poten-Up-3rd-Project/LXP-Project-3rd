package com.lxp.user.domain.profile.model.vo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Tags(List<String> values) {

    public Tags {
        if (Objects.isNull(values) || values.isEmpty()) {
            values = Collections.emptyList();
        } else {
            values = List.copyOf(values);
        }
    }

    public Tags withTags(List<String> newTags) {
        return new Tags(newTags);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return Objects.equals(values, tags.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }
}
