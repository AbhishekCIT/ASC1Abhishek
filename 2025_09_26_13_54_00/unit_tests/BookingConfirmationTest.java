package com.airtransport.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingConfirmation model.
 */
public class BookingConfirmationTest {
    /**
     * Test all-args constructor and getters.
     */
    @Test
    void testAllArgsConstructorAndGetters() {
        Long bookingId = 123L;
        String status = "CONFIRMED";
        String ticketNumber = "TICKET123";
        boolean emailSent = true;
        BookingConfirmation confirmation = new BookingConfirmation(bookingId, status, ticketNumber, emailSent);
        assertEquals(bookingId, confirmation.getBookingId());
        assertEquals(status, confirmation.getStatus());
        assertEquals(ticketNumber, confirmation.getTicketNumber());
        assertTrue(confirmation.isEmailSent());
    }

    /**
     * Test no-args constructor and setters.
     */
    @Test
    void testNoArgsConstructorAndSetters() {
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(456L);
        confirmation.setStatus("PENDING");
        confirmation.setTicketNumber("TICKET456");
        confirmation.setEmailSent(false);
        assertEquals(456L, confirmation.getBookingId());
        assertEquals("PENDING", confirmation.getStatus());
        assertEquals("TICKET456", confirmation.getTicketNumber());
        assertFalse(confirmation.isEmailSent());
    }

    /**
     * Test setting null values for optional fields.
     */
    @Test
    void testSettersWithNulls() {
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setStatus(null);
        confirmation.setTicketNumber(null);
        assertNull(confirmation.getStatus());
        assertNull(confirmation.getTicketNumber());
    }
}
