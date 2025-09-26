package com.example.flightbooking.controller;

import com.example.flightbooking.model.*;
import com.example.flightbooking.service.BookingManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingManagementController.
 */
class BookingManagementControllerTest {

    @Mock
    private BookingManagementService bookingManagementService;

    @InjectMocks
    private BookingManagementController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test viewBookings - normal scenario")
    void testViewBookings_Success() {
        // Arrange
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();
        List<Booking> bookings = Arrays.asList(booking1, booking2);
        when(bookingManagementService.viewBookings(eq(1L), anyString())).thenReturn(bookings);

        // Act
        ResponseEntity<?> response = controller.viewBookings(1L, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof ViewBookingsResponse);
        ViewBookingsResponse resp = (ViewBookingsResponse) response.getBody();
        assertEquals(2, resp.getBookings().size());
    }

    @Test
    @DisplayName("Test viewBookings - unauthorized access")
    void testViewBookings_Unauthorized() {
        // Arrange
        when(bookingManagementService.viewBookings(eq(2L), anyString())).thenThrow(new RuntimeException("Unauthorized"));

        // Act
        ResponseEntity<?> response = controller.viewBookings(2L, "invalid token");

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized", response.getBody());
    }

    @Test
    @DisplayName("Test modifyBooking - normal scenario")
    void testModifyBooking_Success() {
        // Arrange
        BookingModificationRequest request = new BookingModificationRequest();
        BookingModificationResult result = new BookingModificationResult();
        when(bookingManagementService.modifyBooking(eq("REF123"), eq(request), anyString())).thenReturn(result);

        // Act
        ResponseEntity<?> response = controller.modifyBooking("REF123", request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(result, response.getBody());
    }

    @Test
    @DisplayName("Test modifyBooking - error scenario")
    void testModifyBooking_Error() {
        // Arrange
        BookingModificationRequest request = new BookingModificationRequest();
        when(bookingManagementService.modifyBooking(eq("REF999"), eq(request), anyString()))
                .thenThrow(new IllegalArgumentException("Modification not allowed"));

        // Act
        ResponseEntity<?> response = controller.modifyBooking("REF999", request, "Bearer token");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Modification not allowed", response.getBody());
    }

    @Test
    @DisplayName("Test cancelBooking - normal scenario")
    void testCancelBooking_Success() {
        // Arrange
        BookingCancellationResult result = new BookingCancellationResult();
        when(bookingManagementService.cancelBooking(eq("REF456"), anyString())).thenReturn(result);

        // Act
        ResponseEntity<?> response = controller.cancelBooking("REF456", "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(result, response.getBody());
    }

    @Test
    @DisplayName("Test cancelBooking - error scenario")
    void testCancelBooking_Error() {
        // Arrange
        when(bookingManagementService.cancelBooking(eq("REF000"), anyString()))
                .thenThrow(new IllegalStateException("Cancellation not allowed"));

        // Act
        ResponseEntity<?> response = controller.cancelBooking("REF000", "Bearer token");

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Cancellation not allowed", response.getBody());
    }

    @Test
    @DisplayName("Test viewBookings - empty bookings list")
    void testViewBookings_EmptyList() {
        // Arrange
        when(bookingManagementService.viewBookings(eq(3L), anyString())).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> response = controller.viewBookings(3L, "Bearer token");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        ViewBookingsResponse resp = (ViewBookingsResponse) response.getBody();
        assertNotNull(resp);
        assertTrue(resp.getBookings().isEmpty());
    }
}
