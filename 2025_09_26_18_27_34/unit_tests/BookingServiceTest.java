package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.repository.UserRepository;
import com.example.flightbooking.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingService.
 */
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private FlightRepository flightRepository;
    @Mock
    private UserService userService;
    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test bookFlight - normal scenario")
    void testBookFlight_Success() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId(1L);
        User user = new User();
        Flight flight = new Flight();
        Booking booking = new Booking();
        booking.setBookingReference("BR12345678");
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setPassengerDetails(Collections.emptyList());
        when(userService.authenticate("Bearer token")).thenReturn(user);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Act
        Booking result = bookingService.bookFlight(request, "Bearer token");

        // Assert
        assertNotNull(result);
        assertEquals("CONFIRMED", result.getStatus());
        assertEquals(user, result.getUser());
        assertEquals(flight, result.getFlight());
        assertNotNull(result.getBookingReference());
    }

    @Test
    @DisplayName("Test bookFlight - flight not available")
    void testBookFlight_FlightNotAvailable() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId(2L);
        User user = new User();
        when(userService.authenticate("Bearer token")).thenReturn(user);
        when(flightRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                bookingService.bookFlight(request, "Bearer token")
        );
        assertTrue(ex.getMessage().contains("FlightNotAvailableException"));
    }

    @Test
    @DisplayName("Test bookFlight - authentication failure")
    void testBookFlight_AuthenticationFailure() {
        // Arrange
        BookingRequest request = new BookingRequest();
        when(userService.authenticate("bad token")).thenThrow(new RuntimeException("Authentication failed"));

        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                bookingService.bookFlight(request, "bad token")
        );
        assertEquals("Authentication failed", ex.getMessage());
    }

    @Test
    @DisplayName("Test bookFlight - edge case: null passenger details")
    void testBookFlight_NullPassengerDetails() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId(3L);
        request.setPassengerDetails(null);
        User user = new User();
        Flight flight = new Flight();
        when(userService.authenticate(anyString())).thenReturn(user);
        when(flightRepository.findById(3L)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Booking result = bookingService.bookFlight(request, "Bearer token");

        // Assert
        assertNotNull(result);
        assertNull(result.getPassengerDetails());
    }
}
