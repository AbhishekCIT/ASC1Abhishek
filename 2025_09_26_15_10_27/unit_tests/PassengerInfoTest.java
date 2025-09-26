package com.example.airtransport.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for PassengerInfo model.
 */
class PassengerInfoTest {

    @Test
    @DisplayName("Test getters and setters for PassengerInfo")
    void testGettersAndSetters() {
        PassengerInfo info = new PassengerInfo();
        info.setName("Alice");
        info.setEmail("alice@example.com");
        info.setPhone("1234567890");

        assertEquals("Alice", info.getName());
        assertEquals("alice@example.com", info.getEmail());
        assertEquals("1234567890", info.getPhone());
    }

    @Test
    @DisplayName("Test PassengerInfo with null fields")
    void testNullFields() {
        PassengerInfo info = new PassengerInfo();
        assertNull(info.getName());
        assertNull(info.getEmail());
        assertNull(info.getPhone());
    }
}
