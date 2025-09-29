package com.airtransport.service;

import com.airtransport.model.Booking;
import com.airtransport.model.BookingRequest;
import com.airtransport.model.Flight;
import com.airtransport.model.Payment;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

/**
 * Service for handling booking logic.
 */
@Service
public class BookingService {
    @Autowired
    private AirlineIntegrationService airlineIntegrationService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Book a flight, validate, process payment, reserve seat, and send confirmation.
     * @param bookingRequest Booking request
     * @return Booking confirmation
     */
    @Transactional
    public Booking bookFlight(BookingRequest bookingRequest) {
        // Validate origin/destination
        if (!airlineIntegrationService.isValidAirport(bookingRequest.getOrigin()) ||
            !airlineIntegrationService.isValidAirport(bookingRequest.getDestination())) {
            throw new IllegalArgumentException("Invalid airport");
        }
        // Validate date
        if (LocalDate.parse(bookingRequest.getDate()).isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid travel date");
        }
        // Get flight info
        Flight flight = airlineIntegrationService.getFlightById(bookingRequest.getFlightId());
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found");
        }
        // Check seat availability
        if (flight.getSeatsAvailable() < 1) {
            throw new IllegalArgumentException("No seats available");
        }
        // Process payment
        Payment payment = paymentService.processPayment(bookingRequest.getPaymentInfo(), flight.getPrice());
        if (!"SUCCESS".equals(payment.getPaymentStatus())) {
            throw new IllegalArgumentException("Payment failed");
        }
        // Reserve seat
        airlineIntegrationService.reserveSeat(flight.getFlightId(), bookingRequest.getPassengerDetails());
        // Save booking
        Booking booking = new Booking();
        booking.setFlightId(flight.getFlightId());
        booking.setUserId(bookingRequest.getUserId());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDate.now());
        bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendBookingConfirmation(booking, bookingRequest.getPassengerDetails().getEmail());
        booking.setConfirmationEmailSent(true);
        return booking;
    }

    /**
     * Get booking details by bookingId.
     * @param bookingId Booking ID
     * @return Booking details
     */
    public Booking getBooking(String bookingId) {
        return bookingRepository.findByBookingId(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }
}
