package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for User.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    /**
     * Test setting and getting userId (normal case).
     */
    @Test
    void testUserId_Normal() {
        user.setUserId("U123");
        assertEquals("U123", user.getUserId());
    }

    /**
     * Test setting and getting name (normal case).
     */
    @Test
    void testName_Normal() {
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }

    /**
     * Test setting and getting email (normal case).
     */
    @Test
    void testEmail_Normal() {
        user.setEmail("john@example.com");
        assertEquals("john@example.com", user.getEmail());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        user.setUserId(null);
        user.setName(null);
        user.setEmail(null);
        assertNull(user.getUserId());
        assertNull(user.getName());
        assertNull(user.getEmail());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        user.setUserId("");
        user.setName("");
        user.setEmail("");
        assertEquals("", user.getUserId());
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
