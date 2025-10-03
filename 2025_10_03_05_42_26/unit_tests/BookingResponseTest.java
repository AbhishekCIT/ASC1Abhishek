package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingResponse.
 * Covers constructors, getters/setters, normal, edge, and boundary cases.
 */
class BookingResponseTest {
    private BookingResponse bookingResponse;

    @BeforeEach
    void setUp() {
        bookingResponse = new BookingResponse();
    }

    /**
     * Test default constructor.
     */
    @Test
    void testDefaultConstructor() {
        assertNull(bookingResponse.getBookingId());
        assertNull(bookingResponse.getStatus());
    }

    /**
     * Test parameterized constructor.
     */
    @Test
    void testParameterizedConstructor() {
        BookingResponse response = new BookingResponse("B123", "CONFIRMED");
        assertEquals("B123", response.getBookingId());
        assertEquals("CONFIRMED", response.getStatus());
    }

    /**
     * Test setting and getting bookingId (normal case).
     */
    @Test
    void testBookingId_Normal() {
        bookingResponse.setBookingId("B456");
        assertEquals("B456", bookingResponse.getBookingId());
    }

    /**
     * Test setting and getting status (normal case).
     */
    @Test
    void testStatus_Normal() {
        bookingResponse.setStatus("SUCCESS");
        assertEquals("SUCCESS", bookingResponse.getStatus());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        bookingResponse.setBookingId(null);
        bookingResponse.setStatus(null);
        assertNull(bookingResponse.getBookingId());
        assertNull(bookingResponse.getStatus());
    }

    /**
     * Test boundary case: empty strings.
     */
    @Test
    void testSetters_EmptyStrings() {
        bookingResponse.setBookingId("");
        bookingResponse.setStatus("");
        assertEquals("", bookingResponse.getBookingId());
        assertEquals("", bookingResponse.getStatus());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
