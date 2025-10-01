package com.example.airbooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for AirBookingApplication.
 * Verifies that the Spring application context loads successfully.
 */
@SpringBootTest
public class AirBookingApplicationTest {

    /**
     * Purpose: Ensure that the Spring Boot application context loads without exceptions.
     */
    @Test
    void contextLoads() {
        // The test will fail if the application context cannot start.
    }

    /**
     * Purpose: Test the main method for coverage.
     * This test ensures that the main method can be called without throwing exceptions.
     */
    @Test
    void testMainMethod() {
        AirBookingApplication.main(new String[]{});
    }
}
