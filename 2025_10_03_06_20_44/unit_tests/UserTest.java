package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for User entity.
 * Purpose: Verify builder, getters/setters, equals/hashCode, and edge/boundary cases.
 */
public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .name("Test User")
                .email("test@example.com")
                .build();
    }

    /**
     * Test: Builder and getters
     */
    @Test
    void testBuilderAndGetters() {
        assertEquals(1, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
    }

    /**
     * Test: Setters
     */
    @Test
    void testSetters() {
        user.setName("Changed Name");
        assertEquals("Changed Name", user.getName());
    }

    /**
     * Test: Equals and hashCode
     */
    @Test
    void testEqualsAndHashCode() {
        User user2 = User.builder()
                .id(1)
                .name("Test User")
                .email("test@example.com")
                .build();
        assertEquals(user, user2);
        assertEquals(user.hashCode(), user2.hashCode());
    }

    /**
     * Test: Edge case - null fields
     */
    @Test
    void testNullFields() {
        User nullUser = new User();
        assertNull(nullUser.getName());
        assertNull(nullUser.getEmail());
    }

    /**
     * Test: Boundary case - empty name/email
     */
    @Test
    void testEmptyFields() {
        user.setName("");
        user.setEmail("");
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
    }
}
