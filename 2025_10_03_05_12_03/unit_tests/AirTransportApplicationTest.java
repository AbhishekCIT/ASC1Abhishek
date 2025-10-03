package com.example.airtransport;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for AirTransportApplication.
 * Ensures that the Spring application context loads successfully.
 */
@SpringBootTest
public class AirTransportApplicationTest {

    /**
     * Test to verify that the application context loads without exceptions.
     */
    @Test
    void contextLoads() {
        // Purpose: Ensure that the Spring Boot application context loads successfully.
        // No assertions needed; test will fail if context fails to load.
    }

    /**
     * Test to verify that the main method runs without throwing exceptions.
     */
    @Test
    void testMainMethod() {
        // Purpose: Ensure that the main method can be called without exceptions.
        AirTransportApplication.main(new String[]{});
    }
}
