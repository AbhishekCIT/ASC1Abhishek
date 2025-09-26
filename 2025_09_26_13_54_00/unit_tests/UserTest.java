package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
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
     * Test setting and getting all fields.
     */
    @Test
    void testSettersAndGetters() {
        Long id = 5L;
        String email = "test@example.com";
        String name = "John Doe";

        user.setId(id);
        user.setEmail(email);
        user.setName(name);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
    }

    /**
     * Test setting null values for optional fields.
     */
    @Test
    void testSettersWithNulls() {
        user.setEmail(null);
        user.setName(null);
        assertNull(user.getEmail());
        assertNull(user.getName());
    }

    /**
     * Test default values after construction.
     */
    @Test
    void testDefaultValues() {
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getName());
    }
}
