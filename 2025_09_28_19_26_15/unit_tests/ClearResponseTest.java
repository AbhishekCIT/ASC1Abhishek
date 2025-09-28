package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for ClearResponse DTO.
 * Covers getters, setters, and edge cases.
 */
class ClearResponseTest {
    private ClearResponse response;

    @BeforeEach
    void setUp() {
        response = new ClearResponse();
    }

    /**
     * Test getters and setters for all fields.
     */
    @Test
    @DisplayName("Getters and Setters Work Correctly")
    void testGettersAndSetters() {
        response.setInput1("abc");
        response.setInput2("def");
        response.setResult("ghi");
        assertEquals("abc", response.getInput1());
        assertEquals("def", response.getInput2());
        assertEquals("ghi", response.getResult());
    }

    /**
     * Test edge case: null and empty values.
     */
    @Test
    @DisplayName("Edge Case: Null and Empty Values")
    void testNullAndEmptyValues() {
        response.setInput1(null);
        response.setInput2("");
        response.setResult(null);
        assertNull(response.getInput1());
        assertEquals("", response.getInput2());
        assertNull(response.getResult());
    }
}
