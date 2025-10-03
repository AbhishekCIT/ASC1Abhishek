package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for NotificationService.
 */
public class NotificationServiceTest {
    /**
     * Test sendConfirmation does not throw exception.
     */
    @Test
    void testSendConfirmation() {
        NotificationService service = new NotificationService();
        Booking booking = new Booking();
        booking.setBookingRef("ABC123");
        assertDoesNotThrow(() -> service.sendConfirmation(booking));
    }
}
