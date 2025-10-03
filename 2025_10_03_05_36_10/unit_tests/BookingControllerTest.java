package com.airtransport.controller;

import com.airtransport.model.Booking;
import com.airtransport.model.Passenger;
import com.airtransport.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for BookingController.
 * Covers all endpoints and scenarios including normal, edge, and error cases.
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
     * Test createBooking with valid input.
     * Purpose: Ensure booking is created successfully.
     */
    @Test
    void testCreateBooking_Success() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("flightId", 1);
        List<Map<String, String>> passengerDetails = new ArrayList<>();
        Map<String, String> pd = new HashMap<>();
        pd.put("name", "John Doe");
        pd.put("passportNumber", "A1234567");
        passengerDetails.add(pd);
        requestBody.put("passengerDetails", passengerDetails);
        requestBody.put("seats", List.of("1A"));

        Booking mockBooking = new Booking();
        when(bookingService.createBooking(anyInt(), anyList(), anyList())).thenReturn(mockBooking);

        Booking result = bookingController.createBooking(requestBody);
        assertNotNull(result);
        verify(bookingService, times(1)).createBooking(anyInt(), anyList(), anyList());
    }

    /**
     * Test createBooking with missing flightId (edge case).
     * Purpose: Should throw NullPointerException or IllegalArgumentException.
     */
    @Test
    void testCreateBooking_MissingFlightId() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("passengerDetails", new ArrayList<>());
        requestBody.put("seats", List.of("1A"));
        assertThrows(NullPointerException.class, () -> bookingController.createBooking(requestBody));
    }

    /**
     * Test getBookingConfirmation with valid bookingId.
     * Purpose: Should return confirmed booking.
     */
    @Test
    void testGetBookingConfirmation_Success() {
        String bookingId = "booking123";
        Booking mockBooking = new Booking();
        when(bookingService.confirmBooking(bookingId)).thenReturn(mockBooking);
        Booking result = bookingController.getBookingConfirmation(bookingId);
        assertNotNull(result);
        verify(bookingService, times(1)).confirmBooking(bookingId);
    }

    /**
     * Test getBookingConfirmation with invalid bookingId (error case).
     * Purpose: Should throw exception from service.
     */
    @Test
    void testGetBookingConfirmation_InvalidBookingId() {
        String bookingId = "invalid";
        when(bookingService.confirmBooking(bookingId)).thenThrow(new IllegalArgumentException("Booking not found"));
        assertThrows(IllegalArgumentException.class, () -> bookingController.getBookingConfirmation(bookingId));
    }

    /**
     * Test getBookingDetails with valid bookingId.
     * Purpose: Should return booking details.
     */
    @Test
    void testGetBookingDetails_Success() {
        String bookingId = "booking123";
        Booking mockBooking = new Booking();
        when(bookingService.getBookingDetails(bookingId)).thenReturn(mockBooking);
        Booking result = bookingController.getBookingDetails(bookingId);
        assertNotNull(result);
        verify(bookingService, times(1)).getBookingDetails(bookingId);
    }

    /**
     * Test getBookingDetails with invalid bookingId (error case).
     * Purpose: Should throw exception from service.
     */
    @Test
    void testGetBookingDetails_InvalidBookingId() {
        String bookingId = "invalid";
        when(bookingService.getBookingDetails(bookingId)).thenThrow(new IllegalArgumentException("Booking not found"));
        assertThrows(IllegalArgumentException.class, () -> bookingController.getBookingDetails(bookingId));
    }
}
