package com.example.airbooking;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for AirBookingApplication.
 * Ensures that the Spring application context loads successfully.
 */
@SpringBootTest
public class AirBookingApplicationTest {

    /**
     * Test to verify that the application context loads without exceptions.
     */
    @Test
    void contextLoads() {
        // The context loading is tested by the @SpringBootTest annotation.
        // If the context fails to load, this test will fail.
    }
}
