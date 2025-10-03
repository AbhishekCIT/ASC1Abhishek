package com.example.airbooking.controller;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingController.
 */
public class BookingControllerTest {
    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal booking scenario.
     */
    @Test
    void testBookFlight_Success() throws Exception {
        BookingRequest request = new BookingRequest();
        Booking booking = new Booking();
        when(bookingService.createBooking(request)).thenReturn(booking);
        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(booking, response.getBody());
    }

    /**
     * Test booking with service throwing exception (error scenario).
     */
    @Test
    void testBookFlight_Failure() throws Exception {
        BookingRequest request = new BookingRequest();
        when(bookingService.createBooking(request)).thenThrow(new Exception("Flight not available"));
        ResponseEntity<?> response = bookingController.bookFlight(request);
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().toString().contains("Flight not available"));
    }
}
