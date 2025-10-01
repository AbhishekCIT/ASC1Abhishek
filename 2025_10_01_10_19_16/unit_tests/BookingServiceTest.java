package com.airtransport.service;

import com.airtransport.model.Booking;
import com.airtransport.model.Flight;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.FlightRepository;
import com.airtransport.util.PricingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    private PricingUtil pricingUtil;
    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test successful booking creation scenario.
     */
    @Test
    void testCreateBooking_Success() {
        Flight flight = new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.00, 5);
        when(flightRepository.findById(anyString())).thenReturn(Optional.of(flight));
        when(pricingUtil.calculatePrice(anyString())).thenReturn(400.00);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));
        when(flightRepository.save(any(Flight.class))).thenAnswer(i -> i.getArgument(0));

        Map<String, Object> passengerInfo = new HashMap<>();
        Booking booking = bookingService.createBooking("F123", "U456", passengerInfo);
        assertNotNull(booking.getBookingId());
        assertEquals("U456", booking.getUserId());
        assertEquals("F123", booking.getFlightId());
        assertEquals("PENDING_PAYMENT", booking.getStatus());
        assertNotNull(booking.getTicketNumber());
        assertNotNull(booking.getBookingDate());
        assertEquals(passengerInfo, booking.getPassengerInfo());
        assertEquals(400.00, booking.getPrice());
        assertEquals(4, flight.getSeatsAvailable());
    }

    /**
     * Test booking creation with no available seats (edge case).
     */
    @Test
    void testCreateBooking_NoSeatsAvailable() {
        Flight flight = new Flight("F123", "Delta", "10:00", "16:00", "JFK", "LAX", 350.00, 0);
        when(flightRepository.findById(anyString())).thenReturn(Optional.of(flight));
        Map<String, Object> passengerInfo = new HashMap<>();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                bookingService.createBooking("F123", "U456", passengerInfo));
        assertEquals("Selected seat no longer available", ex.getMessage());
    }

    /**
     * Test booking creation with invalid flight (error scenario).
     */
    @Test
    void testCreateBooking_InvalidFlight() {
        when(flightRepository.findById(anyString())).thenReturn(Optional.empty());
        Map<String, Object> passengerInfo = new HashMap<>();
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                bookingService.createBooking("INVALID", "U456", passengerInfo));
        assertEquals("Invalid flight selection.", ex.getMessage());
    }

    /**
     * Test updateBookingStatus saves booking.
     */
    @Test
    void testUpdateBookingStatus() {
        Booking booking = new Booking();
        booking.setBookingId("B789");
        booking.setStatus("CONFIRMED");
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        bookingService.updateBookingStatus(booking);
        verify(bookingRepository, times(1)).save(booking);
    }
}
