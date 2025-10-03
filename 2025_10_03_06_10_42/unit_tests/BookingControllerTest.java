package com.airtransport.controller;

import com.airtransport.dto.BookingRequest;
import com.airtransport.dto.BookingResponse;
import com.airtransport.exception.InvalidPassengerException;
import com.airtransport.exception.PaymentFailedException;
import com.airtransport.exception.SeatUnavailableException;
import com.airtransport.exception.ValidationException;
import com.airtransport.service.BookingService;
import com.airtransport.util.ErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
 * Covers normal, edge, boundary, and error-handling scenarios for bookFlight().
 */
class BookingControllerTest {
    @Mock
    private BookingService bookingService;
    @Mock
    private ErrorHandler errorHandler;
    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal booking scenario with valid request.
     */
    @Test
    @DisplayName("bookFlight: should return BookingResponse for valid request")
    void testBookFlight_Success() throws Exception {
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();
        response.setBookingReference("ABC123");
        when(bookingService.createBooking(any(BookingRequest.class))).thenReturn(response);

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof BookingResponse);
        assertEquals("ABC123", ((BookingResponse) result.getBody()).getBookingReference());
        verify(bookingService, times(1)).createBooking(any(BookingRequest.class));
    }

    /**
     * Test booking with invalid passenger details (edge case).
     */
    @Test
    @DisplayName("bookFlight: should handle InvalidPassengerException")
    void testBookFlight_InvalidPassenger() throws Exception {
        BookingRequest request = new BookingRequest();
        InvalidPassengerException ex = new InvalidPassengerException("Passenger details are required.");
        when(bookingService.createBooking(any(BookingRequest.class))).thenThrow(ex);
        when(errorHandler.handleError(ex)).thenReturn(ResponseEntity.badRequest().body("Passenger details are required."));

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Passenger details are required.", result.getBody());
        verify(errorHandler, times(1)).handleError(ex);
    }

    /**
     * Test booking with payment failure (error scenario).
     */
    @Test
    @DisplayName("bookFlight: should handle PaymentFailedException")
    void testBookFlight_PaymentFailed() throws Exception {
        BookingRequest request = new BookingRequest();
        PaymentFailedException ex = new PaymentFailedException("Payment failed.");
        when(bookingService.createBooking(any(BookingRequest.class))).thenThrow(ex);
        when(errorHandler.handleError(ex)).thenReturn(ResponseEntity.badRequest().body("Payment failed."));

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Payment failed.", result.getBody());
        verify(errorHandler, times(1)).handleError(ex);
    }

    /**
     * Test booking with seat unavailable (boundary condition).
     */
    @Test
    @DisplayName("bookFlight: should handle SeatUnavailableException")
    void testBookFlight_SeatUnavailable() throws Exception {
        BookingRequest request = new BookingRequest();
        SeatUnavailableException ex = new SeatUnavailableException("Seat unavailable.");
        when(bookingService.createBooking(any(BookingRequest.class))).thenThrow(ex);
        when(errorHandler.handleError(ex)).thenReturn(ResponseEntity.badRequest().body("Seat unavailable."));

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Seat unavailable.", result.getBody());
        verify(errorHandler, times(1)).handleError(ex);
    }

    /**
     * Test booking with generic validation exception (edge case).
     */
    @Test
    @DisplayName("bookFlight: should handle ValidationException")
    void testBookFlight_ValidationException() throws Exception {
        BookingRequest request = new BookingRequest();
        ValidationException ex = new ValidationException("Validation failed.");
        when(bookingService.createBooking(any(BookingRequest.class))).thenThrow(ex);
        when(errorHandler.handleError(ex)).thenReturn(ResponseEntity.badRequest().body("Validation failed."));

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Validation failed.", result.getBody());
        verify(errorHandler, times(1)).handleError(ex);
    }

    /**
     * Test booking with unexpected exception (error-handling scenario).
     */
    @Test
    @DisplayName("bookFlight: should handle unexpected Exception as ValidationException")
    void testBookFlight_UnexpectedException() throws Exception {
        BookingRequest request = new BookingRequest();
        Exception ex = new RuntimeException("DB down");
        ValidationException wrapped = new ValidationException("Internal server error.");
        when(bookingService.createBooking(any(BookingRequest.class))).thenThrow(ex);
        when(errorHandler.handleError(any(ValidationException.class))).thenReturn(ResponseEntity.status(400).body("Internal server error."));

        ResponseEntity<?> result = bookingController.bookFlight(request);
        assertEquals(400, result.getStatusCodeValue());
        assertEquals("Internal server error.", result.getBody());
        verify(errorHandler, times(1)).handleError(any(ValidationException.class));
    }
}
