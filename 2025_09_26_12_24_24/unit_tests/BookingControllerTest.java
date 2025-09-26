package com.example.airtransport.controller;

import com.example.airtransport.model.Booking;
import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.PassengerDetail;
import com.example.airtransport.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
     * Test booking a flight with valid input.
     */
    @Test
    @DisplayName("bookFlight returns Booking confirmation for valid request")
    void testBookFlight_ValidRequest_ReturnsBooking() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        PassengerDetail passenger = new PassengerDetail();
        passenger.setName("John Doe");
        passenger.setAge(30);
        request.setPassengerDetails(Collections.singletonList(passenger));
        request.setPaymentInfo(new Object());

        Booking booking = new Booking();
        booking.setBookingReference("BR12345678");
        booking.setFlightId("F123");
        booking.setPassengerDetails(Collections.singletonList(passenger));
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());

        when(bookingService.createBooking(any(BookingRequest.class))).thenReturn(booking);

        ResponseEntity<Booking> response = bookingController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("BR12345678", response.getBody().getBookingReference());
        verify(bookingService, times(1)).createBooking(any(BookingRequest.class));
    }

    /**
     * Test booking a flight with missing passenger details (edge case).
     */
    @Test
    @DisplayName("bookFlight throws exception for missing passenger details")
    void testBookFlight_MissingPassengerDetails_ThrowsException() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        request.setPassengerDetails(Collections.emptyList());
        request.setPaymentInfo(new Object());

        when(bookingService.createBooking(any(BookingRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid passenger details"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bookingController.bookFlight(request);
        });
        assertEquals("Invalid passenger details", exception.getMessage());
        verify(bookingService, times(1)).createBooking(any(BookingRequest.class));
    }

    /**
     * Test retrieving booking info with a valid reference.
     */
    @Test
    @DisplayName("getBookingInfo returns Booking for valid reference")
    void testGetBookingInfo_ValidReference_ReturnsBooking() {
        String reference = "BR12345678";
        Booking booking = new Booking();
        booking.setBookingReference(reference);
        booking.setFlightId("F123");
        booking.setPassengerDetails(Arrays.asList(new PassengerDetail()));
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());

        when(bookingService.getBookingByReference(reference)).thenReturn(booking);

        ResponseEntity<Booking> response = bookingController.getBookingInfo(reference);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(reference, response.getBody().getBookingReference());
        verify(bookingService, times(1)).getBookingByReference(reference);
    }

    /**
     * Test retrieving booking info with an invalid reference (not found).
     */
    @Test
    @DisplayName("getBookingInfo throws exception for invalid reference")
    void testGetBookingInfo_InvalidReference_ThrowsException() {
        String reference = "INVALID_REF";
        when(bookingService.getBookingByReference(reference))
                .thenThrow(new RuntimeException("Booking reference not found"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookingController.getBookingInfo(reference);
        });
        assertEquals("Booking reference not found", exception.getMessage());
        verify(bookingService, times(1)).getBookingByReference(reference);
    }
}
