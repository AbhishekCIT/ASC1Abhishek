package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity.
 */
public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    /**
     * Test all getters and setters for normal values.
     */
    @Test
    @DisplayName("Test getters and setters with normal values")
    void testGettersAndSettersNormal() {
        String userId = "U001";
        String email = "test@example.com";
        String name = "John Doe";

        user.setUserId(userId);
        user.setEmail(email);
        user.setName(name);

        assertEquals(userId, user.getUserId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
    }

    /**
     * Test setters and getters with null values.
     */
    @Test
    @DisplayName("Test setters and getters with null values")
    void testNullValues() {
        user.setUserId(null);
        user.setEmail(null);
        user.setName(null);

        assertNull(user.getUserId());
        assertNull(user.getEmail());
        assertNull(user.getName());
    }

    /**
     * Test setters and getters with empty strings.
     */
    @Test
    @DisplayName("Test setters and getters with empty strings")
    void testEmptyStrings() {
        user.setUserId("");
        user.setEmail("");
        user.setName("");

        assertEquals("", user.getUserId());
        assertEquals("", user.getEmail());
        assertEquals("", user.getName());
    }
}
