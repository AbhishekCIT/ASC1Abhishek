package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for User entity.
 * Covers normal, edge, and boundary scenarios for all fields and methods.
 */
class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    /**
     * Test setting and getting userId (normal scenario).
     */
    @Test
    void testUserId_setAndGet() {
        user.setUserId(42L);
        assertEquals(42L, user.getUserId());
    }

    /**
     * Test setting and getting name (normal scenario).
     */
    @Test
    void testName_setAndGet() {
        user.setName("Alice");
        assertEquals("Alice", user.getName());
    }

    /**
     * Test setting and getting email (normal scenario).
     */
    @Test
    void testEmail_setAndGet() {
        user.setEmail("alice@example.com");
        assertEquals("alice@example.com", user.getEmail());
    }

    /**
     * Test setting and getting passwordHash (normal scenario).
     */
    @Test
    void testPasswordHash_setAndGet() {
        user.setPasswordHash("hashedpassword");
        assertEquals("hashedpassword", user.getPasswordHash());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testSetNullValues() {
        user.setName(null);
        user.setEmail(null);
        user.setPasswordHash(null);
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPasswordHash());
    }

    /**
     * Test boundary condition for userId (edge case).
     */
    @Test
    void testUserId_boundaryValues() {
        user.setUserId(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, user.getUserId());
        user.setUserId(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, user.getUserId());
    }
}
