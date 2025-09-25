package com.example.scheduling;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for Application main class.
 */
@SpringBootTest
public class ApplicationTest {

    /**
     * Test that the Spring application context loads successfully.
     * This ensures that the main class is correctly configured for Spring Boot startup.
     */
    @Test
    void contextLoads() {
        // No exception means success
    }

    /**
     * Test that the main method runs without throwing exceptions.
     * This is a basic smoke test for application startup.
     */
    @Test
    void testMainMethodRuns() {
        Application.main(new String[]{});
        // If no exception is thrown, the test passes
    }
}
