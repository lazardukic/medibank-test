package com.audition.common.validation;

public class Validator {

    public static boolean isValidId(final Integer input) {
        return input != null && input >= 0;
    }

    public static boolean notNull(final Object input) {
        return input != null;
    }

    public static boolean isNull(final Object input) {
        return input == null;
    }

}
