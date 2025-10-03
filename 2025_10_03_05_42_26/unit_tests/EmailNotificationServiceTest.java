package com.airtransport.service;

import com.airtransport.model.EmailResponse;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for EmailNotificationService.
 * Covers normal, edge, and error scenarios for sendBookingConfirmation.
 */
class EmailNotificationServiceTest {
    private EmailNotificationService emailNotificationService;

    @BeforeEach
    void setUp() {
        emailNotificationService = new EmailNotificationService();
    }

    /**
     * Test sending booking confirmation email (normal case).
     */
    @Test
    void testSendBookingConfirmation_Normal() {
        EmailResponse response = emailNotificationService.sendBookingConfirmation("B123", "user@example.com");
        assertNotNull(response);
        assertEquals("SENT", response.getNotificationStatus());
    }

    /**
     * Test sending booking confirmation with null bookingId (edge case).
     */
    @Test
    void testSendBookingConfirmation_NullBookingId() {
        EmailResponse response = emailNotificationService.sendBookingConfirmation(null, "user@example.com");
        assertNotNull(response);
        assertEquals("SENT", response.getNotificationStatus());
    }

    /**
     * Test sending booking confirmation with null email (edge case).
     */
    @Test
    void testSendBookingConfirmation_NullEmail() {
        EmailResponse response = emailNotificationService.sendBookingConfirmation("B123", null);
        assertNotNull(response);
        assertEquals("SENT", response.getNotificationStatus());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
