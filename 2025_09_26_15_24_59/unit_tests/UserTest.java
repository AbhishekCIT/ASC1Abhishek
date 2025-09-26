package com.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity.
 */
class UserTest {
    /**
     * Test getters and setters for User entity.
     */
    @Test
    @DisplayName("User entity getters and setters work as expected")
    void testUserEntityGettersSetters() {
        User user = new User();
        String userId = "U123";
        String email = "user@example.com";
        String name = "Jane Doe";

        user.setUserId(userId);
        user.setEmail(email);
        user.setName(name);

        assertEquals(userId, user.getUserId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
    }

    /**
     * Test default values for User entity.
     */
    @Test
    @DisplayName("User entity default values are null")
    void testUserEntityDefaults() {
        User user = new User();
        assertNull(user.getUserId());
        assertNull(user.getEmail());
        assertNull(user.getName());
    }
}
