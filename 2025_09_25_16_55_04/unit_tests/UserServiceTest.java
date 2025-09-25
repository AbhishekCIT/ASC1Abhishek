package com.example.scheduling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService.
 */
class UserServiceTest {
    private final UserService userService = new UserService();

    /**
     * Test isValidUser returns true for a positive user ID.
     */
    @Test
    @DisplayName("isValidUser: should return true for positive user ID")
    void testIsValidUser_PositiveId() {
        assertTrue(userService.isValidUser(1L));
        assertTrue(userService.isValidUser(100L));
    }

    /**
     * Test isValidUser returns true for zero and negative user IDs (stub behavior).
     */
    @Test
    @DisplayName("isValidUser: should return true for zero and negative user IDs (stub)")
    void testIsValidUser_ZeroAndNegative() {
        assertTrue(userService.isValidUser(0L));
        assertTrue(userService.isValidUser(-1L));
    }

    /**
     * Test isValidUser returns true for null (stub behavior).
     */
    @Test
    @DisplayName("isValidUser: should return true for null (stub)")
    void testIsValidUser_Null() {
        assertTrue(userService.isValidUser(null));
    }
}
