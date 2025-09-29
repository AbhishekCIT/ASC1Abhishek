package com.example.airtransport.repository;

import com.example.airtransport.model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRepository.
 * Covers saveBooking and findByBookingId methods, including normal, edge, and error scenarios.
 */
class BookingRepositoryTest {
    private BookingRepository repository;

    @BeforeEach
    void setUp() {
        repository = new BookingRepository();
    }

    @Test
    void saveBooking_and_findByBookingId_normalFlow() {
        // Purpose: Test saving and retrieving a booking
        BookingRequest req = new BookingRequest();
        req.setUserId("U001");
        req.setFlightId("F123");
        PaymentResult paymentResult = new PaymentResult(true, "OK");
        paymentResult.setAmount(123.45);
        Booking booking = repository.saveBooking(req, paymentResult);
        assertNotNull(booking.getBookingId());
        assertEquals("U001", booking.getUserId());
        assertEquals("F123", booking.getFlightId());
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(123.45, booking.getTotalPrice());
        assertNotNull(booking.getBookingDate());
        // Retrieve by ID
        Booking found = repository.findByBookingId(booking.getBookingId());
        assertNotNull(found);
        assertEquals(booking.getBookingId(), found.getBookingId());
    }

    @Test
    void findByBookingId_notFound_returnsNull() {
        // Purpose: Test retrieval of non-existent booking
        Booking found = repository.findByBookingId("NON_EXISTENT_ID");
        assertNull(found);
    }

    @Test
    void saveBooking_nullFields_allowed() {
        // Purpose: Test saving booking with null userId/flightId
        BookingRequest req = new BookingRequest();
        req.setUserId(null);
        req.setFlightId(null);
        PaymentResult paymentResult = new PaymentResult(true, "OK");
        paymentResult.setAmount(0.0);
        Booking booking = repository.saveBooking(req, paymentResult);
        assertNull(booking.getUserId());
        assertNull(booking.getFlightId());
        assertEquals(0.0, booking.getTotalPrice());
    }

    @AfterEach
    void tearDown() {
        // Purpose: Clean up after each test if needed
    }
}
