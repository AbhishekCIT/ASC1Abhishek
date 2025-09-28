package com.example.calculator.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for StatusResponse DTO.
 * Covers constructors, getters, setters, and edge cases.
 */
class StatusResponseTest {
    private StatusResponse response;

    @BeforeEach
    void setUp() {
        response = new StatusResponse();
    }

    /**
     * Test default constructor and setter/getter.
     */
    @Test
    @DisplayName("Default Constructor, Setter, and Getter")
    void testDefaultConstructorSetterGetter() {
        response.setStatus("ok");
        assertEquals("ok", response.getStatus());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    @DisplayName("Parameterized Constructor")
    void testParameterizedConstructor() {
        StatusResponse resp = new StatusResponse("added");
        assertEquals("added", resp.getStatus());
    }

    /**
     * Test edge case: null and empty status.
     */
    @Test
    @DisplayName("Edge Case: Null and Empty Status")
    void testNullAndEmptyStatus() {
        response.setStatus(null);
        assertNull(response.getStatus());
        response.setStatus("");
        assertEquals("", response.getStatus());
    }
}
