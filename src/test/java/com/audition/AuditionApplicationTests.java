package com.audition;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class AuditionApplicationTests {

    // TODO implement unit test. - DONE
    //  Note that an applicant should create additional unit tests as required.

    @Test
    void contextLoads(final ApplicationContext context) {
        assertThat(context).isNotNull();
    }

}
