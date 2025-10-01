package com.airtransport.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity.
 */
class UserTest {

    /**
     * Test getters and setters for User.
     */
    @Test
    void testUserGettersSetters() {
        User user = new User();
        user.setUserId("U123");
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPasswordHash("hashedpassword");

        assertEquals("U123", user.getUserId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("hashedpassword", user.getPasswordHash());
    }
}
