package com.airtransport.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRequest.
 * Covers all getters/setters, normal, edge, and boundary cases.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
    }

    /**
     * Test setting and getting flightId (normal case).
     */
    @Test
    void testFlightId_Normal() {
        bookingRequest.setFlightId("F123");
        assertEquals("F123", bookingRequest.getFlightId());
    }

    /**
     * Test setting and getting userId (normal case).
     */
    @Test
    void testUserId_Normal() {
        bookingRequest.setUserId("U456");
        assertEquals("U456", bookingRequest.getUserId());
    }

    /**
     * Test setting and getting passengerDetails (normal case).
     */
    @Test
    void testPassengerDetails_Normal() {
        Object passenger = new Object();
        bookingRequest.setPassengerDetails(passenger);
        assertEquals(passenger, bookingRequest.getPassengerDetails());
    }

    /**
     * Test setting and getting paymentInfo (normal case).
     */
    @Test
    void testPaymentInfo_Normal() {
        PaymentInfo paymentInfo = new PaymentInfo();
        bookingRequest.setPaymentInfo(paymentInfo);
        assertEquals(paymentInfo, bookingRequest.getPaymentInfo());
    }

    /**
     * Test edge case: setting null values.
     */
    @Test
    void testSetters_NullValues() {
        bookingRequest.setFlightId(null);
        bookingRequest.setUserId(null);
        bookingRequest.setPassengerDetails(null);
        bookingRequest.setPaymentInfo(null);
        assertNull(bookingRequest.getFlightId());
        assertNull(bookingRequest.getUserId());
        assertNull(bookingRequest.getPassengerDetails());
        assertNull(bookingRequest.getPaymentInfo());
    }

    /**
     * Test boundary case: empty strings for flightId and userId.
     */
    @Test
    void testSetters_EmptyStrings() {
        bookingRequest.setFlightId("");
        bookingRequest.setUserId("");
        assertEquals("", bookingRequest.getFlightId());
        assertEquals("", bookingRequest.getUserId());
    }

    @AfterEach
    void tearDown() {
        // Cleanup if needed
    }
}
