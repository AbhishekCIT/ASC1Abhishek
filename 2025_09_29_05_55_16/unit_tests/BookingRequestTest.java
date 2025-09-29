package com.example.airtransport.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest model.
 * Covers all getters, setters, and edge cases.
 */
class BookingRequestTest {
    private BookingRequest bookingRequest;

    @BeforeEach
    void setUp() {
        bookingRequest = new BookingRequest();
    }

    @Test
    void testFlightIdGetterSetter() {
        // Purpose: Test normal and null values for flightId
        bookingRequest.setFlightId("F123");
        assertEquals("F123", bookingRequest.getFlightId());
        bookingRequest.setFlightId(null);
        assertNull(bookingRequest.getFlightId());
    }

    @Test
    void testUserIdGetterSetter() {
        // Purpose: Test normal and null values for userId
        bookingRequest.setUserId("U001");
        assertEquals("U001", bookingRequest.getUserId());
        bookingRequest.setUserId(null);
        assertNull(bookingRequest.getUserId());
    }

    @Test
    void testPaymentInfoGetterSetter() {
        // Purpose: Test normal and null values for paymentInfo
        PaymentInfo paymentInfo = new PaymentInfo();
        bookingRequest.setPaymentInfo(paymentInfo);
        assertEquals(paymentInfo, bookingRequest.getPaymentInfo());
        bookingRequest.setPaymentInfo(null);
        assertNull(bookingRequest.getPaymentInfo());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
