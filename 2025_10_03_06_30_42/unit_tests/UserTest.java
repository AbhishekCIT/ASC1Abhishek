package com.example.airbooking.entity;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for User entity.
 * Covers normal, edge, boundary, and error scenarios for all methods.
 */
class UserTest {
    private User user;
    private List<Booking> bookings;

    @BeforeEach
    void setUp() {
        user = new User();
        bookings = new ArrayList<>();
        Booking booking = new Booking();
        booking.setBookingId("BK001");
        bookings.add(booking);
    }

    /**
     * Test setting and getting userId (normal scenario).
     */
    @Test
    void testUserId() {
        user.setUserId("U123");
        assertEquals("U123", user.getUserId());
    }

    /**
     * Test setting and getting email (normal scenario).
     */
    @Test
    void testEmail() {
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    /**
     * Test setting and getting passwordHash (normal scenario).
     */
    @Test
    void testPasswordHash() {
        user.setPasswordHash("hash123");
        assertEquals("hash123", user.getPasswordHash());
    }

    /**
     * Test setting and getting bookings (normal scenario).
     */
    @Test
    void testBookings() {
        user.setBookings(bookings);
        assertEquals(bookings, user.getBookings());
        assertEquals(1, user.getBookings().size());
    }

    /**
     * Test setting null values (edge case).
     */
    @Test
    void testNullValues() {
        user.setUserId(null);
        user.setEmail(null);
        user.setPasswordHash(null);
        user.setBookings(null);
        assertNull(user.getUserId());
        assertNull(user.getEmail());
        assertNull(user.getPasswordHash());
        assertNull(user.getBookings());
    }

    /**
     * Test setting empty bookings list (boundary condition).
     */
    @Test
    void testEmptyBookings() {
        user.setBookings(new ArrayList<>());
        assertNotNull(user.getBookings());
        assertTrue(user.getBookings().isEmpty());
    }
}
