package com.asc.airbooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for AirBookingApplication.
 * Verifies that the Spring application context loads successfully.
 */
@SpringBootTest
public class AirBookingApplicationTest {

    /**
     * Purpose: Ensure that the application context loads without exceptions.
     */
    @Test
    void contextLoads() {
        // If the context fails to load, this test will fail.
    }

    /**
     * Purpose: Test that the main method runs without throwing exceptions.
     */
    @Test
    void testMainMethodRuns() {
        AirBookingApplication.main(new String[]{});
        // No assertion needed; test will fail if an exception is thrown.
    }
}
