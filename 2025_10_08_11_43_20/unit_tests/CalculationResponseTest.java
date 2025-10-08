package com.example.calculator.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for CalculationResponse.
 * Purpose: Test constructors, getters, and setters for normal and edge cases.
 */
public class CalculationResponseTest {
    /**
     * Test default constructor and setter.
     */
    @Test
    void testDefaultConstructorAndSetter() {
        CalculationResponse response = new CalculationResponse();
        response.setResult(42.0);
        assertEquals(42.0, response.getResult());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        CalculationResponse response = new CalculationResponse(3.14);
        assertEquals(3.14, response.getResult());
    }

    /**
     * Test edge case: null value.
     */
    @Test
    void testNullValue() {
        CalculationResponse response = new CalculationResponse(null);
        assertNull(response.getResult());
    }
}
