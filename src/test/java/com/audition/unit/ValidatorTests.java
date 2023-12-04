package com.audition.unit;

import static com.audition.common.validation.Validator.isNull;
import static com.audition.common.validation.Validator.isValidId;
import static com.audition.common.validation.Validator.notNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidatorTests {

    @Test
    void isValidIdTest() {
        assertThat(isValidId(null)).isFalse();
        assertThat(isValidId(-97)).isFalse();
        assertThat(isValidId(0)).isTrue();
        assertThat(isValidId(100)).isTrue();
    }

    @Test
    void notNullTest() {
        assertThat(notNull(null)).isFalse();
        assertThat(notNull(-97)).isTrue();
        assertThat(notNull("value")).isTrue();
    }

    @Test
    void isNullTest() {
        assertThat(isNull(null)).isTrue();
        assertThat(isNull(-97)).isFalse();
        assertThat(isNull("value")).isFalse();
    }

}
