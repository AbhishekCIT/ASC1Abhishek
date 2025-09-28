package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for CalculationResponse DTO.
 * Covers getters, setters, and edge cases.
 */
class CalculationResponseTest {
    private CalculationResponse response;

    @BeforeEach
    void setUp() {
        response = new CalculationResponse();
    }

    /**
     * Test getters and setters for result and error.
     */
    @Test
    @DisplayName("Getters and Setters Work Correctly")
    void testGettersAndSetters() {
        response.setResult(42.0);
        response.setError("none");
        assertEquals(42.0, response.getResult());
        assertEquals("none", response.getError());
    }

    /**
     * Test edge case: null values for result and error.
     */
    @Test
    @DisplayName("Edge Case: Null Values")
    void testNullValues() {
        response.setResult(null);
        response.setError(null);
        assertNull(response.getResult());
        assertNull(response.getError());
    }
}
