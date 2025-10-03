package com.airtransport.entity;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for UserEntity.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class UserEntityTest {
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
    }

    /**
     * Test setting and getting userId (normal case).
     */
    @Test
    void testUserId_Normal() {
        userEntity.setUserId("U123");
        assertEquals("U123", userEntity.getUserId());
    }

    /**
     * Test setting and getting name (normal case).
     */
    @Test
    void testName_Normal() {
        userEntity.setName("John Doe");
        assertEquals("John Doe", userEntity.getName());
    }

    /**
     * Test setting and getting email (normal case).
     */
    @Test
    void testEmail_Normal() {
        userEntity.setEmail("john@example.com");
        assertEquals("john@example.com", userEntity.getEmail());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        userEntity.setUserId(null);
        userEntity.setName(null);
        userEntity.setEmail(null);
        assertNull(userEntity.getUserId());
        assertNull(userEntity.getName());
        assertNull(userEntity.getEmail());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        userEntity.setUserId("");
        userEntity.setName("");
        userEntity.setEmail("");
        assertEquals("", userEntity.getUserId());
        assertEquals("", userEntity.getName());
        assertEquals("", userEntity.getEmail());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
