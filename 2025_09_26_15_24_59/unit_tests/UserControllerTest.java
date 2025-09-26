package com.airtransport.controller;

import com.airtransport.model.*;
import com.airtransport.service.BookingService;
import com.airtransport.service.FlightSearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for UserController.
 */
class UserControllerTest {
    @Mock
    private FlightSearchService flightSearchService;
    @Mock
    private BookingService bookingService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test searchFlights returns expected response for valid input.
     */
    @Test
    @DisplayName("searchFlights returns expected response for valid input")
    void testSearchFlights_Valid() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate("2099-12-31");
        request.setPassengers(2);
        FlightSearchResponse mockResponse = new FlightSearchResponse(List.of(new FlightInfo("F123", "Delta", "10:00", "13:00", 350.0)));
        when(flightSearchService.searchFlights(any())).thenReturn(mockResponse);
        ResponseEntity<FlightSearchResponse> response = userController.searchFlights(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test bookFlight returns expected response for valid input.
     */
    @Test
    @DisplayName("bookFlight returns expected response for valid input")
    void testBookFlight_Valid() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        request.setUserId("U456");
        request.setPassengerDetails(List.of(new PassengerDetail("John Doe", "P1234567")));
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("4111111111111111");
        paymentInfo.setExpiry("12/30");
        paymentInfo.setCvv("123");
        paymentInfo.setCardholderName("John Doe");
        request.setPaymentInfo(paymentInfo);
        BookingResponse mockResponse = BookingResponse.success("BR789", "CONFIRMED", null);
        when(bookingService.bookFlight(any())).thenReturn(mockResponse);
        ResponseEntity<BookingResponse> response = userController.bookFlight(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
    }

    /**
     * Test searchFlights handles service exception (edge case).
     */
    @Test
    @DisplayName("searchFlights throws exception from service")
    void testSearchFlights_ServiceException() {
        FlightSearchRequest request = new FlightSearchRequest();
        request.setOrigin("JFK");
        request.setDestination("LAX");
        request.setDate("2099-12-31");
        request.setPassengers(2);
        when(flightSearchService.searchFlights(any())).thenThrow(new RuntimeException("Service error"));
        assertThrows(RuntimeException.class, () -> userController.searchFlights(request));
    }

    /**
     * Test bookFlight handles service exception (edge case).
     */
    @Test
    @DisplayName("bookFlight throws exception from service")
    void testBookFlight_ServiceException() {
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        request.setUserId("U456");
        request.setPassengerDetails(List.of(new PassengerDetail("John Doe", "P1234567")));
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("4111111111111111");
        paymentInfo.setExpiry("12/30");
        paymentInfo.setCvv("123");
        paymentInfo.setCardholderName("John Doe");
        request.setPaymentInfo(paymentInfo);
        when(bookingService.bookFlight(any())).thenThrow(new RuntimeException("Service error"));
        assertThrows(RuntimeException.class, () -> userController.bookFlight(request));
    }
}
