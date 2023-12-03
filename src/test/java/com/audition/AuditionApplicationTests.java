package com.audition;

import static org.assertj.core.api.Assertions.assertThat;

import com.audition.common.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class AuditionApplicationTests {

    // TODO implement unit test. - DONE
    //  Note that an applicant should create additional unit tests as required.

    // didn't have time to add more unit tests, would have added more unit and integration tests for
    // controller, service, integration client for expected outputs with a mocking framework e.g. mockito
    // and for other classes where applicable

    @Test
    void contextLoads(final ApplicationContext context) {
        assertThat(context).isNotNull();
    }

    @Test
    void validatorTest() {
        assertThat(Validator.isInputValid(null) == false);
        assertThat(Validator.isInputValid("input") == true);
    }

}
