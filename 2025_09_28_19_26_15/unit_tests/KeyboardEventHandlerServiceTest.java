package com.example.calculator.util;

import com.example.calculator.exception.InvalidKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for KeyboardEventHandlerService.
 * Covers valid keys, invalid keys, and null input.
 */
class KeyboardEventHandlerServiceTest {
    private KeyboardEventHandlerService service;

    @BeforeEach
    void setUp() {
        service = new KeyboardEventHandlerService();
    }

    /**
     * Test valid keys are accepted and mapped correctly.
     */
    @Test
    @DisplayName("Valid Keys Are Accepted")
    void testValidKeys() {
        String[] validKeys = {"0","1","2","3","4","5","6","7","8","9",".","+","-","*","/","Enter","Esc"};
        for (String key : validKeys) {
            assertEquals(key, service.handleKey(key));
        }
    }

    /**
     * Test invalid key throws exception.
     */
    @Test
    @DisplayName("Invalid Key Throws Exception")
    void testInvalidKey() {
        assertThrows(InvalidKeyException.class, () -> service.handleKey("A"));
        assertThrows(InvalidKeyException.class, () -> service.handleKey("Tab"));
        assertThrows(InvalidKeyException.class, () -> service.handleKey(" "));
    }

    /**
     * Test null key throws exception.
     */
    @Test
    @DisplayName("Null Key Throws Exception")
    void testNullKey() {
        assertThrows(InvalidKeyException.class, () -> service.handleKey(null));
    }
}
