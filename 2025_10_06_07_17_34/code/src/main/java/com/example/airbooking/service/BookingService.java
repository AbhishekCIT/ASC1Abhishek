package com.example.airbooking.service;

import com.example.airbooking.model.Booking;
import com.example.airbooking.model.Passenger;
import com.example.airbooking.model.Payment;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.PassengerRepository;
import com.example.airbooking.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service for booking flights and managing bookings.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;

    /**
     * Books a flight, processes payment, and sends confirmation email.
     * @param flightId Flight ID
     * @param passengerDetails List of passenger details
     * @param paymentInfo Payment information
     * @return Booking confirmation
     */
    @Transactional
    public Booking bookFlight(String flightId, List<Passenger> passengerDetails, Payment paymentInfo) {
        // Validate passenger details
        if (passengerDetails == null || passengerDetails.isEmpty()) {
            throw new IllegalArgumentException("Passenger details are invalid.");
        }
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setFlightId(flightId);
        booking.setStatus("PENDING");
        booking.setBookingDate(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        // Save passengers
        for (Passenger p : passengerDetails) {
            p.setBookingId(booking.getBookingId());
            passengerRepository.save(p);
        }
        // Process payment
        paymentInfo.setBookingId(booking.getBookingId());
        Payment processedPayment = paymentService.processPayment(booking.getBookingId(), paymentInfo);
        paymentRepository.save(processedPayment);
        // Update booking status
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendConfirmationEmail(booking.getBookingId(), passengerDetails.get(0).getEmail());
        return booking;
    }

    /**
     * Retrieves booking details by booking ID.
     * @param bookingId Booking ID
     * @return Booking
     */
    public Booking getBooking(String bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found."));
    }
}