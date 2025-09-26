package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
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

    @Test
    @DisplayName("Test successful booking returns BookingResponse and 200 OK")
    void testBookFlight_Success() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        BookingResponse response = new BookingResponse();
        response.setBookingRef("BR123456");
        response.setStatus("CONFIRMED");
        when(bookingService.bookFlight(any(BookingRequest.class))).thenReturn(response);

        // Act
        ResponseEntity<?> result = bookingController.bookFlight(request);

        // Assert
        assertEquals(200, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof BookingResponse);
        BookingResponse body = (BookingResponse) result.getBody();
        assertEquals("BR123456", body.getBookingRef());
        assertEquals("CONFIRMED", body.getStatus());
    }

    @Test
    @DisplayName("Test booking failure returns error message and 400 Bad Request")
    void testBookFlight_Failure() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        when(bookingService.bookFlight(any(BookingRequest.class))).thenThrow(new Exception("Seat unavailable"));

        // Act
        ResponseEntity<?> result = bookingController.bookFlight(request);

        // Assert
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof String);
        assertTrue(((String) result.getBody()).contains("Seat unavailable"));
    }

    @Test
    @DisplayName("Test booking failure with payment error returns error message and 400 Bad Request")
    void testBookFlight_PaymentFailure() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        when(bookingService.bookFlight(any(BookingRequest.class))).thenThrow(new Exception("Payment failed"));

        // Act
        ResponseEntity<?> result = bookingController.bookFlight(request);

        // Assert
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof String);
        assertTrue(((String) result.getBody()).contains("Payment failed"));
    }

    // Additional edge case: null BookingRequest
    @Test
    @DisplayName("Test booking with null request throws exception and returns error")
    void testBookFlight_NullRequest() throws Exception {
        // Arrange
        when(bookingService.bookFlight(null)).thenThrow(new Exception("Invalid request"));

        // Act
        ResponseEntity<?> result = bookingController.bookFlight(null);

        // Assert
        assertEquals(400, result.getStatusCodeValue());
        assertTrue(result.getBody() instanceof String);
        assertTrue(((String) result.getBody()).contains("Invalid request"));
    }
}
