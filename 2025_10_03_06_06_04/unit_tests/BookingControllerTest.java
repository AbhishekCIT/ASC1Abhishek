package com.example.airtransport.controller;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;
import com.example.airtransport.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
 * Covers normal, edge, and error scenarios for booking creation and confirmation.
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

    @Nested
    @DisplayName("bookFlight() tests")
    class BookFlightTests {
        @Test
        @DisplayName("Should return BookingResponse when booking is successful")
        void testBookFlightSuccess() {
            // Arrange
            BookingRequest request = new BookingRequest();
            request.setFlightId("F123");
            request.setUserId("U1");
            request.setPaymentInfo(null); // Assume validation elsewhere
            BookingResponse expected = BookingResponse.builder()
                    .bookingId("B456").status("CONFIRMED").emailSent(true).build();
            when(bookingService.createBooking(any())).thenReturn(expected);

            // Act
            ResponseEntity<BookingResponse> response = bookingController.bookFlight(request);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expected, response.getBody());
        }

        @Test
        @DisplayName("Should handle null BookingRequest (edge case)")
        void testBookFlightNullRequest() {
            // Purpose: Should throw exception if request is null (validation handled by framework)
            assertThrows(NullPointerException.class, () -> bookingController.bookFlight(null));
        }
    }

    @Nested
    @DisplayName("confirmBooking() tests")
    class ConfirmBookingTests {
        @Test
        @DisplayName("Should return BookingResponse when confirmation is successful")
        void testConfirmBookingSuccess() {
            // Arrange
            String bookingId = "B456";
            BookingResponse expected = BookingResponse.builder()
                    .bookingId(bookingId).status("CONFIRMED").emailSent(true).build();
            when(bookingService.confirmBooking(bookingId)).thenReturn(expected);

            // Act
            ResponseEntity<BookingResponse> response = bookingController.confirmBooking(bookingId);

            // Assert
            assertNotNull(response);
            assertEquals(200, response.getStatusCodeValue());
            assertEquals(expected, response.getBody());
        }

        @Test
        @DisplayName("Should handle invalid bookingId (error case)")
        void testConfirmBookingInvalidId() {
            // Arrange
            String invalidId = "INVALID";
            when(bookingService.confirmBooking(invalidId)).thenThrow(new RuntimeException("Booking not found"));

            // Act & Assert
            assertThrows(RuntimeException.class, () -> bookingController.confirmBooking(invalidId));
        }
    }
}
