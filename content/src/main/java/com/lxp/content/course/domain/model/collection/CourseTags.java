package com.lxp.content.course.domain.model.collection;

import com.lxp.content.course.domain.model.id.TagId;

import java.util.HashSet;
import java.util.Set;

public record CourseTags(Set<TagId> values) {

    public CourseTags {
        values = Set.copyOf(values == null ? Set.of() : values);
    }

    public static CourseTags empty() {
        return new CourseTags(Set.of());
    }

    public CourseTags add(TagId tag) {
        Set<TagId> newSet = new HashSet<>(values);
        newSet.add(tag);
        return new CourseTags(newSet);
    }

    public CourseTags remove(TagId tag) {
        Set<TagId> newSet = new HashSet<>(values);
        newSet.remove(tag);
        return new CourseTags(newSet);
    }

    public boolean contains(TagId tagId) {
        return values.contains(tagId);
    }
}
