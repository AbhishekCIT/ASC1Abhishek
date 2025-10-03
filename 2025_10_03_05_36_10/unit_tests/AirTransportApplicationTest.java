package com.airtransport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AirTransportApplication.
 * Verifies that the main method runs without throwing exceptions.
 */
public class AirTransportApplicationTest {

    /**
     * Test main method execution.
     * Purpose: Ensure that the Spring Boot application starts without exceptions.
     */
    @Test
    void testMainMethodRunsSuccessfully() {
        assertDoesNotThrow(() -> AirTransportApplication.main(new String[]{}));
    }
}
