package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.PaymentCallbackRequest;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.util.EmailService;
import com.example.airbooking.util.GDSClient;
import com.example.airbooking.util.PaymentService;
import com.example.airbooking.util.SeatLockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private PaymentService paymentService;
    @Mock
    private EmailService emailService;
    @Mock
    private GDSClient gdsClient;
    @Mock
    private SeatLockService seatLockService;
    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("bookFlight - success scenario")
    void testBookFlightSuccess() {
        Booking booking = new Booking();
        booking.setEmail("test@example.com");
        booking.setFlightId(1L);
        booking.setSeats(Arrays.asList("12A"));
        booking.setPaymentInfo(new Object());
        when(seatLockService.lockSeats(eq(1L), anyList())).thenReturn(true);
        when(paymentService.processPayment(any())).thenReturn(true);
        when(gdsClient.confirmBooking(any(Booking.class))).thenReturn("ABC123");
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));

        Booking result = bookingService.bookFlight(booking);
        assertEquals("ABC123", result.getBookingRef());
        assertEquals("CONFIRMED", result.getStatus());
        assertNotNull(result.getBookingTime());
        verify(emailService, times(1)).sendConfirmationEmail(any(Booking.class));
    }

    @Test
    @DisplayName("bookFlight - invalid email")
    void testBookFlightInvalidEmail() {
        Booking booking = new Booking();
        booking.setEmail("invalid-email");
        booking.setFlightId(1L);
        booking.setSeats(Arrays.asList("12A"));
        booking.setPaymentInfo(new Object());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> bookingService.bookFlight(booking));
        assertEquals("Invalid email address", ex.getMessage());
    }

    @Test
    @DisplayName("bookFlight - seat already booked")
    void testBookFlightSeatAlreadyBooked() {
        Booking booking = new Booking();
        booking.setEmail("test@example.com");
        booking.setFlightId(1L);
        booking.setSeats(Arrays.asList("12A"));
        booking.setPaymentInfo(new Object());
        when(seatLockService.lockSeats(eq(1L), anyList())).thenReturn(false);
        Exception ex = assertThrows(IllegalStateException.class, () -> bookingService.bookFlight(booking));
        assertEquals("Seat already booked", ex.getMessage());
    }

    @Test
    @DisplayName("bookFlight - payment failure")
    void testBookFlightPaymentFailure() {
        Booking booking = new Booking();
        booking.setEmail("test@example.com");
        booking.setFlightId(1L);
        booking.setSeats(Arrays.asList("12A"));
        booking.setPaymentInfo(new Object());
        when(seatLockService.lockSeats(eq(1L), anyList())).thenReturn(true);
        when(paymentService.processPayment(any())).thenReturn(false);
        Exception ex = assertThrows(IllegalStateException.class, () -> bookingService.bookFlight(booking));
        assertEquals("Payment could not be processed", ex.getMessage());
    }

    @Test
    @DisplayName("handlePaymentCallback - booking not found")
    void testHandlePaymentCallbackBookingNotFound() {
        PaymentCallbackRequest req = new PaymentCallbackRequest();
        req.setBookingRef("NOTFOUND");
        req.setStatus("SUCCESS");
        when(bookingRepository.findByBookingRef("NOTFOUND")).thenReturn(null);
        String result = bookingService.handlePaymentCallback(req);
        assertEquals("BOOKING_NOT_FOUND", result);
    }

    @Test
    @DisplayName("handlePaymentCallback - payment success")
    void testHandlePaymentCallbackSuccess() {
        PaymentCallbackRequest req = new PaymentCallbackRequest();
        req.setBookingRef("ABC123");
        req.setStatus("SUCCESS");
        Booking booking = new Booking();
        booking.setBookingRef("ABC123");
        booking.setStatus("PENDING");
        when(bookingRepository.findByBookingRef("ABC123")).thenReturn(booking);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));
        String result = bookingService.handlePaymentCallback(req);
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals("CONFIRMED", result);
        verify(emailService, times(1)).sendConfirmationEmail(any(Booking.class));
    }

    @Test
    @DisplayName("handlePaymentCallback - payment failed")
    void testHandlePaymentCallbackFailed() {
        PaymentCallbackRequest req = new PaymentCallbackRequest();
        req.setBookingRef("ABC123");
        req.setStatus("FAILED");
        Booking booking = new Booking();
        booking.setBookingRef("ABC123");
        booking.setStatus("PENDING");
        when(bookingRepository.findByBookingRef("ABC123")).thenReturn(booking);
        when(bookingRepository.save(any(Booking.class))).thenAnswer(i -> i.getArgument(0));
        String result = bookingService.handlePaymentCallback(req);
        assertEquals("PAYMENT_FAILED", booking.getStatus());
        assertEquals("FAILED", result);
        verify(emailService, never()).sendConfirmationEmail(any(Booking.class));
    }
}
