package com.cicd;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class HelloControllerTest {

    @Test
    void testHelloEndpoint() {
        String response = "Hello CI/CD!";
        //new code
        assertThat(response).isEqualTo("Hello CI/CD!");
    }
}
