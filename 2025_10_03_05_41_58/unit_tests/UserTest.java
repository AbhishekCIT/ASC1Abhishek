package com.airtransport.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for User entity.
 * Covers constructors, builder, getters, setters, equals, hashCode, and toString.
 */
class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("U123", "John Doe", "john@example.com");
    }

    @Test
    @DisplayName("Test all-args constructor and getters")
    void testAllArgsConstructorAndGetters() {
        assertEquals("U123", user.getUserId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    @DisplayName("Test setters and no-args constructor")
    void testSettersAndNoArgsConstructor() {
        User u = new User();
        u.setUserId("U999");
        u.setName("Jane Smith");
        u.setEmail("jane@example.com");
        assertEquals("U999", u.getUserId());
        assertEquals("Jane Smith", u.getName());
        assertEquals("jane@example.com", u.getEmail());
    }

    @Test
    @DisplayName("Test builder pattern")
    void testBuilder() {
        User u = User.builder()
                .userId("U777")
                .name("Alice")
                .email("alice@example.com")
                .build();
        assertEquals("U777", u.getUserId());
        assertEquals("Alice", u.getName());
        assertEquals("alice@example.com", u.getEmail());
    }

    @Test
    @DisplayName("Test equals and hashCode")
    void testEqualsAndHashCode() {
        User u1 = new User("U123", "John Doe", "john@example.com");
        User u2 = new User("U123", "John Doe", "john@example.com");
        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    @DisplayName("Test toString does not throw")
    void testToString() {
        assertDoesNotThrow(() -> user.toString());
    }

    @Test
    @DisplayName("Test edge case: null fields")
    void testNullFields() {
        User u = new User(null, null, null);
        assertNull(u.getUserId());
        assertNull(u.getName());
        assertNull(u.getEmail());
    }
}
