package com.example.airlinebooking.controller;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.BookingRequest;
import com.example.airlinebooking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingController.
 */
class BookingControllerTest {
    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test booking a flight successfully.
     */
    @Test
    void testBookFlight_Success() {
        BookingRequest request = new BookingRequest();
        Booking booking = new Booking();
        when(bookingService.createBooking(any())).thenReturn(booking);
        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(booking, response.getBody());
    }

    /**
     * Test booking a flight with error (e.g., payment failure).
     */
    @Test
    void testBookFlight_Error() {
        BookingRequest request = new BookingRequest();
        when(bookingService.createBooking(any())).thenThrow(new RuntimeException("Payment failed"));
        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Payment failed"));
    }

    /**
     * Test getting booking info successfully.
     */
    @Test
    void testGetBookingInfo_Success() {
        Booking booking = new Booking();
        when(bookingService.getBookingByReference("ABC123")).thenReturn(booking);
        ResponseEntity<?> response = bookingController.getBookingInfo("ABC123");
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(booking, response.getBody());
    }

    /**
     * Test getting booking info with invalid reference.
     */
    @Test
    void testGetBookingInfo_Error() {
        when(bookingService.getBookingByReference("BADREF")).thenThrow(new RuntimeException("Booking reference not found"));
        ResponseEntity<?> response = bookingController.getBookingInfo("BADREF");
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Booking reference not found"));
    }
}
