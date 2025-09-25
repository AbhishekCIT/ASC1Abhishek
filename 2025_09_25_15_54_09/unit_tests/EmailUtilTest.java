package com.example.scheduler.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EmailUtil.
 */
public class EmailUtilTest {

    /**
     * Test valid email addresses.
     */
    @Test
    void testIsValidEmail_ValidEmails() {
        assertTrue(EmailUtil.isValidEmail("user@domain.com"));
        assertTrue(EmailUtil.isValidEmail("user.name+tag@sub.domain.co"));
        assertTrue(EmailUtil.isValidEmail("user_name@domain.co.in"));
        assertTrue(EmailUtil.isValidEmail("user-name@domain.com"));
    }

    /**
     * Test invalid email addresses.
     */
    @Test
    void testIsValidEmail_InvalidEmails() {
        assertFalse(EmailUtil.isValidEmail("user@domain")); // missing TLD
        assertFalse(EmailUtil.isValidEmail("userdomain.com")); // missing @
        assertFalse(EmailUtil.isValidEmail("@domain.com")); // missing local part
        assertFalse(EmailUtil.isValidEmail("user@.com")); // missing domain
        assertFalse(EmailUtil.isValidEmail("user@domain..com")); // double dot
        assertFalse(EmailUtil.isValidEmail("user@domain,com")); // comma instead of dot
    }

    /**
     * Test null and empty email addresses (edge cases).
     */
    @Test
    void testIsValidEmail_NullOrEmpty() {
        assertFalse(EmailUtil.isValidEmail(null));
        assertFalse(EmailUtil.isValidEmail(""));
    }
}
