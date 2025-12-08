package com.lxp.tag.application.port.provided.dto;

public record TagLookup(
    Long tagId,
    String name,
    boolean active
) { }
