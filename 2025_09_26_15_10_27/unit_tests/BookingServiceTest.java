package com.example.airtransport.service;

import com.example.airtransport.model.*;
import com.example.airtransport.repository.BookingRepository;
import com.example.airtransport.util.BookingReferenceGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingService.
 */
class BookingServiceTest {

    @Mock
    private FlightInventoryService flightInventoryService;
    @Mock
    private PaymentGatewayService paymentGatewayService;
    @Mock
    private BookingReferenceGenerator bookingReferenceGenerator;
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test successful booking returns BookingResponse with CONFIRMED status")
    void testBookFlight_Success() throws Exception {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        PassengerInfo passengerInfo = new PassengerInfo();
        passengerInfo.setName("John Doe");
        request.setPassengerInfo(passengerInfo);
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMethod("CARD");
        paymentInfo.setAmount(350.0);
        request.setPaymentInfo(paymentInfo);

        when(flightInventoryService.isSeatAvailable("F123")).thenReturn(true);
        Payment payment = new Payment();
        payment.setStatus("SUCCESS");
        payment.setAmount(350.0);
        when(paymentGatewayService.processPayment(any(PaymentInfo.class))).thenReturn(payment);
        when(bookingReferenceGenerator.generate()).thenReturn("BR123456");
        when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        BookingResponse response = bookingService.bookFlight(request);

        // Assert
        assertNotNull(response);
        assertEquals("BR123456", response.getBookingRef());
        assertEquals("CONFIRMED", response.getStatus());
        assertNotNull(response.getTicket());
        assertEquals("F123", response.getTicket().getFlightId());
        assertEquals(350.0, response.getTicket().getTotalPrice());
        assertEquals("CONFIRMED", response.getTicket().getStatus());
    }

    @Test
    @DisplayName("Test booking fails when seat is unavailable")
    void testBookFlight_SeatUnavailable() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        when(flightInventoryService.isSeatAvailable("F123")).thenReturn(false);

        // Act & Assert
        Exception ex = assertThrows(Exception.class, () -> bookingService.bookFlight(request));
        assertEquals("Seat unavailable", ex.getMessage());
        verify(paymentGatewayService, never()).processPayment(any());
        verify(bookingRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test booking fails when payment fails")
    void testBookFlight_PaymentFailed() {
        // Arrange
        BookingRequest request = new BookingRequest();
        request.setFlightId("F123");
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setMethod("CARD");
        paymentInfo.setAmount(350.0);
        request.setPaymentInfo(paymentInfo);
        when(flightInventoryService.isSeatAvailable("F123")).thenReturn(true);
        Payment payment = new Payment();
        payment.setStatus("FAILED");
        when(paymentGatewayService.processPayment(any(PaymentInfo.class))).thenReturn(payment);

        // Act & Assert
        Exception ex = assertThrows(Exception.class, () -> bookingService.bookFlight(request));
        assertEquals("Payment failed", ex.getMessage());
        verify(bookingRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test booking with null request throws NullPointerException")
    void testBookFlight_NullRequest() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> bookingService.bookFlight(null));
    }

    @Test
    @DisplayName("Test booking with null flightId throws NullPointerException")
    void testBookFlight_NullFlightId() {
        // Arrange
        BookingRequest request = new BookingRequest();
        // Act & Assert
        assertThrows(NullPointerException.class, () -> bookingService.bookFlight(request));
    }
}
