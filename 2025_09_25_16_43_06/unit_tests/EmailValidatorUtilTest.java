package com.example.scheduler.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EmailValidatorUtil.
 */
class EmailValidatorUtilTest {
    private final EmailValidatorUtil validator = new EmailValidatorUtil();

    @Test
    @DisplayName("Should return true for valid email addresses")
    void testValidEmails() {
        assertTrue(validator.isValid("user@example.com"));
        assertTrue(validator.isValid("USER@EXAMPLE.COM"));
        assertTrue(validator.isValid("user.name+tag@sub.domain.co"));
        assertTrue(validator.isValid("a@b.co"));
    }

    @Test
    @DisplayName("Should return false for invalid email addresses")
    void testInvalidEmails() {
        assertFalse(validator.isValid("userexample.com"));
        assertFalse(validator.isValid("user@.com"));
        assertFalse(validator.isValid("user@com"));
        assertFalse(validator.isValid("user@domain."));
        assertFalse(validator.isValid("@domain.com"));
        assertFalse(validator.isValid("user@domain.c")); // TLD too short
        assertFalse(validator.isValid("user@domain.toolongtld")); // TLD too long
    }

    @Test
    @DisplayName("Should return false for null or empty email")
    void testNullOrEmptyEmail() {
        assertFalse(validator.isValid(null));
        assertFalse(validator.isValid(""));
    }
}
