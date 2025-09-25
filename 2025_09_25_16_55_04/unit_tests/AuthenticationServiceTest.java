package com.example.scheduling.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for AuthenticationService.
 */
class AuthenticationServiceTest {
    private final AuthenticationService authenticationService = new AuthenticationService();

    /**
     * Test authenticate returns true for a valid token (stub behavior).
     */
    @Test
    @DisplayName("authenticate: should return true for non-null token (stub)")
    void testAuthenticate_NonNullToken() {
        assertTrue(authenticationService.authenticate("sometoken"));
        assertTrue(authenticationService.authenticate("another-token"));
    }

    /**
     * Test authenticate returns true for null and empty string (stub behavior).
     */
    @Test
    @DisplayName("authenticate: should return true for null and empty string (stub)")
    void testAuthenticate_NullAndEmpty() {
        assertTrue(authenticationService.authenticate(null));
        assertTrue(authenticationService.authenticate(""));
    }
}
