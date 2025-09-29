package com.example.airbooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AirBookingApplication.
 */
@SpringBootTest
public class AirBookingApplicationTest {

    /**
     * Test to ensure the Spring application context loads successfully.
     */
    @Test
    void contextLoads() {
        // Purpose: Verifies that the Spring context loads without exceptions.
        // No assertions needed; test will fail if context fails to load.
    }

    /**
     * Test to ensure the main method runs without throwing exceptions.
     */
    @Test
    void testMainMethodRuns() {
        // Purpose: Verifies that the main method can be invoked without error.
        assertDoesNotThrow(() -> AirBookingApplication.main(new String[]{}));
    }
}
