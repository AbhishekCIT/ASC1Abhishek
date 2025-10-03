package com.airtransport.entity;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingEntity.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class BookingEntityTest {
    private BookingEntity bookingEntity;

    @BeforeEach
    void setUp() {
        bookingEntity = new BookingEntity();
    }

    /**
     * Test setting and getting bookingId (normal case).
     */
    @Test
    void testBookingId_Normal() {
        bookingEntity.setBookingId("B123");
        assertEquals("B123", bookingEntity.getBookingId());
    }

    /**
     * Test setting and getting userId (normal case).
     */
    @Test
    void testUserId_Normal() {
        bookingEntity.setUserId("U456");
        assertEquals("U456", bookingEntity.getUserId());
    }

    /**
     * Test setting and getting flightId (normal case).
     */
    @Test
    void testFlightId_Normal() {
        bookingEntity.setFlightId("F789");
        assertEquals("F789", bookingEntity.getFlightId());
    }

    /**
     * Test setting and getting status (normal case).
     */
    @Test
    void testStatus_Normal() {
        bookingEntity.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", bookingEntity.getStatus());
    }

    /**
     * Test setting and getting bookingDate (normal case).
     */
    @Test
    void testBookingDate_Normal() {
        LocalDateTime now = LocalDateTime.now();
        bookingEntity.setBookingDate(now);
        assertEquals(now, bookingEntity.getBookingDate());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        bookingEntity.setBookingId(null);
        bookingEntity.setUserId(null);
        bookingEntity.setFlightId(null);
        bookingEntity.setStatus(null);
        bookingEntity.setBookingDate(null);
        assertNull(bookingEntity.getBookingId());
        assertNull(bookingEntity.getUserId());
        assertNull(bookingEntity.getFlightId());
        assertNull(bookingEntity.getStatus());
        assertNull(bookingEntity.getBookingDate());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        bookingEntity.setBookingId("");
        bookingEntity.setUserId("");
        bookingEntity.setFlightId("");
        bookingEntity.setStatus("");
        assertEquals("", bookingEntity.getBookingId());
        assertEquals("", bookingEntity.getUserId());
        assertEquals("", bookingEntity.getFlightId());
        assertEquals("", bookingEntity.getStatus());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
