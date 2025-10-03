package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for EmailRequest.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class EmailRequestTest {
    private EmailRequest emailRequest;

    @BeforeEach
    void setUp() {
        emailRequest = new EmailRequest();
    }

    /**
     * Test setting and getting bookingId (normal case).
     */
    @Test
    void testBookingId_Normal() {
        emailRequest.setBookingId("B123");
        assertEquals("B123", emailRequest.getBookingId());
    }

    /**
     * Test setting and getting email (normal case).
     */
    @Test
    void testEmail_Normal() {
        emailRequest.setEmail("user@example.com");
        assertEquals("user@example.com", emailRequest.getEmail());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        emailRequest.setBookingId(null);
        emailRequest.setEmail(null);
        assertNull(emailRequest.getBookingId());
        assertNull(emailRequest.getEmail());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        emailRequest.setBookingId("");
        emailRequest.setEmail("");
        assertEquals("", emailRequest.getBookingId());
        assertEquals("", emailRequest.getEmail());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
