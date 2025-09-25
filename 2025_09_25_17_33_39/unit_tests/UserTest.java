package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity.
 */
class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    @DisplayName("Should set and get id correctly")
    void testIdGetterSetter() {
        user.setId(501L);
        assertEquals(501L, user.getId());
    }

    @Test
    @DisplayName("Should set and get name correctly")
    void testNameGetterSetter() {
        user.setName("Alice");
        assertEquals("Alice", user.getName());
    }

    @Test
    @DisplayName("Should set and get email correctly")
    void testEmailGetterSetter() {
        user.setEmail("alice@example.com");
        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    @DisplayName("Should handle null values for fields")
    void testNullFields() {
        user.setName(null);
        user.setEmail(null);
        assertNull(user.getName());
        assertNull(user.getEmail());
    }
}
