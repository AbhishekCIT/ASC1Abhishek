package com.example.airbooking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingEntity (POJO).
 */
class BookingEntityTest {
    private BookingEntity bookingEntity;

    @BeforeEach
    void setUp() {
        bookingEntity = new BookingEntity();
    }

    /**
     * Test setting and getting bookingId.
     */
    @Test
    @DisplayName("Set and get bookingId")
    void testBookingId() {
        bookingEntity.setBookingId(10L);
        assertEquals(10L, bookingEntity.getBookingId());
    }

    /**
     * Test setting and getting userId.
     */
    @Test
    @DisplayName("Set and get userId")
    void testUserId() {
        bookingEntity.setUserId(20L);
        assertEquals(20L, bookingEntity.getUserId());
    }

    /**
     * Test setting and getting flightId.
     */
    @Test
    @DisplayName("Set and get flightId")
    void testFlightId() {
        bookingEntity.setFlightId(30L);
        assertEquals(30L, bookingEntity.getFlightId());
    }

    /**
     * Test setting and getting status.
     */
    @Test
    @DisplayName("Set and get status")
    void testStatus() {
        bookingEntity.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", bookingEntity.getStatus());
    }

    /**
     * Test setting and getting bookingDate.
     */
    @Test
    @DisplayName("Set and get bookingDate")
    void testBookingDate() {
        LocalDateTime now = LocalDateTime.now();
        bookingEntity.setBookingDate(now);
        assertEquals(now, bookingEntity.getBookingDate());
    }

    /**
     * Test setting and getting ticketNumber.
     */
    @Test
    @DisplayName("Set and get ticketNumber")
    void testTicketNumber() {
        bookingEntity.setTicketNumber("TKT123");
        assertEquals("TKT123", bookingEntity.getTicketNumber());
    }

    /**
     * Test default values (should be null).
     */
    @Test
    @DisplayName("Default values are null")
    void testDefaultValues() {
        assertNull(bookingEntity.getBookingId());
        assertNull(bookingEntity.getUserId());
        assertNull(bookingEntity.getFlightId());
        assertNull(bookingEntity.getStatus());
        assertNull(bookingEntity.getBookingDate());
        assertNull(bookingEntity.getTicketNumber());
    }
}
