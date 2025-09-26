package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.util.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Manages booking lifecycle including creation, retrieval, and confirmation.
 */
@Service
public class BookingService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Create a booking for a flight with passenger and payment details.
     */
    public BookingConfirmation createBooking(BookingRequest request) {
        // Validate passenger details
        if (request.getPassengerDetails() == null || !request.getPassengerDetails().isValid()) {
            throw new IllegalArgumentException("Passenger details are incomplete.");
        }
        // Check flight availability
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Selected flight is no longer available."));
        if (flight.getSeatsAvailable() <= 0) {
            throw new RuntimeException("Selected flight is no longer available.");
        }
        // Process payment
        PaymentResponse paymentResponse = paymentService.processPayment(request.getPaymentInfo());
        if (!"SUCCESS".equals(paymentResponse.getPaymentStatus())) {
            throw new RuntimeException("Payment could not be processed.");
        }
        // Save booking
        Booking booking = new Booking();
        booking.setFlightId(request.getFlightId());
        booking.setUserId(request.getPassengerDetails().getUserId());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        booking.setTicketNumber("TKT" + System.currentTimeMillis());
        bookingRepository.save(booking);
        // Send confirmation
        notificationService.sendConfirmation(booking, request.getPassengerDetails().getEmail());
        // Return confirmation
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getBookingId());
        confirmation.setStatus(booking.getStatus());
        confirmation.setTicket(booking.getTicketNumber());
        return confirmation;
    }

    /**
     * Get booking details by booking ID.
     */
    public BookingConfirmation getBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found."));
        BookingConfirmation confirmation = new BookingConfirmation();
        confirmation.setBookingId(booking.getBookingId());
        confirmation.setStatus(booking.getStatus());
        confirmation.setTicket(booking.getTicketNumber());
        return confirmation;
    }
}
