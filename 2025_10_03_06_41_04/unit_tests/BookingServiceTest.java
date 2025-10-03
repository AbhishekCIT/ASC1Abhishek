package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingService.
 */
public class BookingServiceTest {
    @Mock
    private FlightInventoryService flightInventoryService;
    @Mock
    private PaymentGatewayService paymentGatewayService;
    @Mock
    private NotificationService notificationService;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PassengerRepository passengerRepository;
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test normal booking creation scenario.
     */
    @Test
    void testCreateBooking_Success() throws Exception {
        BookingRequest request = new BookingRequest();
        request.setFlightId(1L);
        Passenger p = new Passenger();
        request.setPassengerDetails(Arrays.asList(p));
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("1234567890123456");
        paymentInfo.setAmount(100.0);
        request.setPaymentInfo(paymentInfo);

        when(flightInventoryService.reserveSeats(1L, 1)).thenReturn(true);
        Payment payment = new Payment();
        payment.setPaymentStatus("SUCCESS");
        when(paymentGatewayService.processPayment(paymentInfo)).thenReturn(payment);
        Flight flight = new Flight();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArguments()[0]);
        when(passengerRepository.save(any(Passenger.class))).thenAnswer(i -> i.getArguments()[0]);
        when(paymentRepository.save(any(Payment.class))).thenAnswer(i -> i.getArguments()[0]);

        Booking booking = bookingService.createBooking(request);
        assertNotNull(booking);
        assertEquals(flight, booking.getFlight());
        assertEquals("CONFIRMED", booking.getStatus());
        verify(notificationService, times(1)).sendConfirmation(any(Booking.class));
    }

    /**
     * Test booking creation with unavailable flight (error scenario).
     */
    @Test
    void testCreateBooking_FlightUnavailable() {
        BookingRequest request = new BookingRequest();
        request.setFlightId(1L);
        request.setPassengerDetails(Arrays.asList(new Passenger()));
        when(flightInventoryService.reserveSeats(1L, 1)).thenReturn(false);
        Exception ex = assertThrows(Exception.class, () -> bookingService.createBooking(request));
        assertEquals("Flight not available", ex.getMessage());
    }

    /**
     * Test booking creation with failed payment (error scenario).
     */
    @Test
    void testCreateBooking_PaymentFailed() {
        BookingRequest request = new BookingRequest();
        request.setFlightId(1L);
        request.setPassengerDetails(Arrays.asList(new Passenger()));
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("123");
        request.setPaymentInfo(paymentInfo);
        when(flightInventoryService.reserveSeats(1L, 1)).thenReturn(true);
        Payment payment = new Payment();
        payment.setPaymentStatus("FAILED");
        when(paymentGatewayService.processPayment(paymentInfo)).thenReturn(payment);
        Exception ex = assertThrows(Exception.class, () -> bookingService.createBooking(request));
        assertEquals("Payment authorization failed", ex.getMessage());
    }
}
