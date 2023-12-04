package com.audition;

import static org.assertj.core.api.Assertions.assertThat;

import com.audition.common.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class AuditionApplicationTests {

    @Test
    void contextLoads(final ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void validatorTest() {
        assertThat(Validator.isValidId(null)).isFalse();
        assertThat(Validator.isValidId(-97)).isFalse();
        assertThat(Validator.isValidId(0)).isTrue();
        assertThat(Validator.isValidId(100)).isTrue();
    }

}
