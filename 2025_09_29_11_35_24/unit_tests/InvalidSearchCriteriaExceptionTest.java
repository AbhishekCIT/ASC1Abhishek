package com.airtransport.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for InvalidSearchCriteriaException.
 */
public class InvalidSearchCriteriaExceptionTest {
    @Test
    @DisplayName("Test constructor with message")
    void testConstructorWithMessage() {
        InvalidSearchCriteriaException ex = new InvalidSearchCriteriaException("Invalid criteria");
        assertEquals("Invalid criteria", ex.getMessage());
    }

    @Test
    @DisplayName("Test constructor with null message (edge case)")
    void testConstructorWithNullMessage() {
        InvalidSearchCriteriaException ex = new InvalidSearchCriteriaException(null);
        assertNull(ex.getMessage());
    }
}
