package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.PaymentCallbackRequest;
import com.example.airbooking.model.Seat;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.util.EmailService;
import com.example.airbooking.util.GDSClient;
import com.example.airbooking.util.PaymentService;
import com.example.airbooking.util.SeatLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for booking flights, seat locking, payment, and email confirmation
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private GDSClient gdsClient;
    @Autowired
    private SeatLockService seatLockService;

    /**
     * Book a flight: lock seat, process payment, confirm booking, send email
     */
    public Booking bookFlight(Booking booking) {
        // Validate email
        if (!isValidEmail(booking.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }
        // Lock seats
        List<String> seatNos = booking.getSeats();
        boolean locked = seatLockService.lockSeats(booking.getFlightId(), seatNos);
        if (!locked) {
            throw new IllegalStateException("Seat already booked");
        }
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(booking.getPaymentInfo());
        if (!paymentSuccess) {
            throw new IllegalStateException("Payment could not be processed");
        }
        // Confirm booking in GDS
        String bookingRef = gdsClient.confirmBooking(booking);
        booking.setBookingRef(bookingRef);
        booking.setStatus("CONFIRMED");
        booking.setBookingTime(LocalDateTime.now());
        // Save booking
        bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendConfirmationEmail(booking);
        return booking;
    }

    /**
     * Handle payment callback from gateway
     */
    public String handlePaymentCallback(PaymentCallbackRequest request) {
        // Find booking by reference
        Booking booking = bookingRepository.findByBookingRef(request.getBookingRef());
        if (booking == null) {
            return "BOOKING_NOT_FOUND";
        }
        if ("SUCCESS".equals(request.getStatus())) {
            booking.setStatus("CONFIRMED");
            bookingRepository.save(booking);
            emailService.sendConfirmationEmail(booking);
            return "CONFIRMED";
        } else {
            booking.setStatus("PAYMENT_FAILED");
            bookingRepository.save(booking);
            return "FAILED";
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
