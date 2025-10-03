package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity
 */
public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    /**
     * Test setting and getting userId
     */
    @Test
    void testUserId() {
        user.setUserId(55L);
        assertEquals(55L, user.getUserId());
    }

    /**
     * Test setting and getting email
     */
    @Test
    void testEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    /**
     * Test setting and getting name
     */
    @Test
    void testName() {
        user.setName("Bob");
        assertEquals("Bob", user.getName());
    }

    /**
     * Test setting and getting passwordHash
     */
    @Test
    void testPasswordHash() {
        user.setPasswordHash("hashed123");
        assertEquals("hashed123", user.getPasswordHash());
    }

    /**
     * Test edge case: null values
     */
    @Test
    void testNullValues() {
        user.setEmail(null);
        user.setName(null);
        user.setPasswordHash(null);
        assertNull(user.getEmail());
        assertNull(user.getName());
        assertNull(user.getPasswordHash());
    }
}
