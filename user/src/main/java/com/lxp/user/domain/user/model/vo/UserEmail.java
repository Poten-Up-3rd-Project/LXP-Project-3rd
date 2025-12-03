package com.lxp.user.domain.user.model.vo;

import com.lxp.common.domain.model.ValueObject;

import java.util.regex.Pattern;

import static java.util.Objects.isNull;

public final class UserEmail extends ValueObject {

    private final String value;

    private UserEmail(String value) {
        if (isNull(value) || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(value);
        }

        this.value = value;
    }

    public static UserEmail of(String value) {
        return new UserEmail(value);
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

    public String getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
