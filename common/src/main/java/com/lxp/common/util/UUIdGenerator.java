package com.lxp.common.util;

import java.util.UUID;

public class UUIdGenerator {
    private UUIdGenerator() {}

    public static UUID createUuid() {
        return UUID.randomUUID();
    }

    public static String createString() {
        return UUID.randomUUID().toString();
    }
}
