package com.example.airbooking.controller;

import com.example.airbooking.dto.BookingRequest;
import com.example.airbooking.dto.BookingResponse;
import com.example.airbooking.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for BookingController.
 * Covers normal, edge, boundary, and error-handling scenarios for booking endpoints.
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
     * Test booking a flight with valid input (normal scenario).
     */
    @Test
    @DisplayName("bookFlight: should return BookingResponse for valid request")
    void bookFlight_validRequest_returnsBookingResponse() {
        BookingRequest request = new BookingRequest();
        BookingResponse expected = new BookingResponse();
        when(bookingService.createBooking(request)).thenReturn(expected);

        ResponseEntity<BookingResponse> response = bookingController.bookFlight(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expected, response.getBody());
        verify(bookingService).createBooking(request);
    }

    /**
     * Test booking a flight with null request (edge case).
     */
    @Test
    @DisplayName("bookFlight: should throw NullPointerException for null request")
    void bookFlight_nullRequest_throwsException() {
        assertThrows(NullPointerException.class, () -> bookingController.bookFlight(null));
    }

    /**
     * Test getBooking with valid booking ID (normal scenario).
     */
    @Test
    @DisplayName("getBooking: should return BookingResponse for valid bookingId")
    void getBooking_validId_returnsBookingResponse() {
        int bookingId = 123;
        BookingResponse expected = new BookingResponse();
        when(bookingService.getBooking(bookingId)).thenReturn(expected);

        ResponseEntity<BookingResponse> response = bookingController.getBooking(bookingId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expected, response.getBody());
        verify(bookingService).getBooking(bookingId);
    }

    /**
     * Test getBooking with non-existent booking ID (error scenario).
     */
    @Test
    @DisplayName("getBooking: should propagate exception for non-existent bookingId")
    void getBooking_nonExistentId_throwsException() {
        int bookingId = 9999;
        when(bookingService.getBooking(bookingId)).thenThrow(new RuntimeException("Booking not found"));

        Exception ex = assertThrows(RuntimeException.class, () -> bookingController.getBooking(bookingId));
        assertEquals("Booking not found", ex.getMessage());
        verify(bookingService).getBooking(bookingId);
    }

    /**
     * Test getBooking with null booking ID (edge case).
     */
    @Test
    @DisplayName("getBooking: should throw NullPointerException for null bookingId")
    void getBooking_nullId_throwsException() {
        assertThrows(NullPointerException.class, () -> bookingController.getBooking(null));
    }
}
