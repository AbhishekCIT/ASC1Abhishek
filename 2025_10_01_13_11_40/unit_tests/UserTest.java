package com.example.airlinebooking.entity;

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
        user = User.builder()
                .userId("U001")
                .name("John Doe")
                .email("john@example.com")
                .password("pass")
                .build();
    }

    /**
     * Test normal creation of User entity.
     */
    @Test
    @DisplayName("User entity is created with all fields set")
    void testUserCreation_Normal() {
        assertEquals("U001", user.getUserId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("pass", user.getPassword());
    }

    /**
     * Test edge case: null name.
     */
    @Test
    @DisplayName("User with null name should allow setting and getting")
    void testUser_NullName() {
        user.setName(null);
        assertNull(user.getName());
    }

    /**
     * Test boundary case: empty userId.
     */
    @Test
    @DisplayName("User with empty userId")
    void testUser_EmptyUserId() {
        user.setUserId("");
        assertEquals("", user.getUserId());
    }

    /**
     * Test error scenario: null email.
     */
    @Test
    @DisplayName("User with null email should allow setting and getting")
    void testUser_NullEmail() {
        user.setEmail(null);
        assertNull(user.getEmail());
    }

    /**
     * Test edge case: empty password.
     */
    @Test
    @DisplayName("User with empty password")
    void testUser_EmptyPassword() {
        user.setPassword("");
        assertEquals("", user.getPassword());
    }
}
