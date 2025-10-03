package com.example.airtransport.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for User entity.
 * Covers builder, getters/setters, equals/hashCode, and edge cases.
 */
class UserTest {

    @Test
    @DisplayName("Should create User using builder and access fields")
    void testBuilderAndGetters() {
        User user = User.builder()
                .userId("U1")
                .name("Alice")
                .email("alice@example.com")
                .passwordHash("hash")
                .build();
        assertEquals("U1", user.getUserId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("hash", user.getPasswordHash());
    }

    @Test
    @DisplayName("Should set and get fields via setters and getters")
    void testSettersAndGetters() {
        User user = new User();
        user.setUserId("U2");
        user.setName("Bob");
        user.setEmail("bob@example.com");
        user.setPasswordHash("pw");
        assertEquals("U2", user.getUserId());
        assertEquals("Bob", user.getName());
        assertEquals("bob@example.com", user.getEmail());
        assertEquals("pw", user.getPasswordHash());
    }

    @Test
    @DisplayName("Should test equals and hashCode contract")
    void testEqualsAndHashCode() {
        User u1 = User.builder().userId("U1").email("a@b.com").build();
        User u2 = User.builder().userId("U1").email("a@b.com").build();
        User u3 = User.builder().userId("U2").email("c@d.com").build();
        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
        assertNotEquals(u1, u3);
    }

    @Test
    @DisplayName("Should handle null fields (edge case)")
    void testNullFields() {
        User user = new User();
        assertNull(user.getUserId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPasswordHash());
    }
}
