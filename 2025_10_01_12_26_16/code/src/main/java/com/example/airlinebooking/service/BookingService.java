package com.example.airlinebooking.service;

import com.example.airlinebooking.model.Booking;
import com.example.airlinebooking.model.Flight;
import com.example.airlinebooking.model.Passenger;
import com.example.airlinebooking.model.Payment;
import com.example.airlinebooking.repository.BookingRepository;
import com.example.airlinebooking.repository.FlightRepository;
import com.example.airlinebooking.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing bookings, seat allocation, and confirmation.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private NotificationService notificationService;

    /**
     * Books a ticket for a passenger on a flight.
     * @param flightId the flight ID
     * @param passengerId the passenger ID
     * @param flightClass class of travel
     * @param paymentInfo payment information
     * @return Booking entity
     */
    @Transactional
    public Booking bookTicket(String flightId, String passengerId, String flightClass, Object paymentInfo) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (!flightOpt.isPresent()) {
            throw new IllegalArgumentException("Flight not found");
        }
        Flight flight = flightOpt.get();
        if (flight.getSeatsAvailable() <= 0) {
            throw new RuntimeException("No seats available");
        }
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);
        if (!passengerOpt.isPresent()) {
            throw new IllegalArgumentException("Passenger not found");
        }
        Passenger passenger = passengerOpt.get();
        // For demo, assume fixed price and payment amount
        BigDecimal amount = new BigDecimal("250.00");
        Payment payment = paymentService.charge(amount, paymentInfo);
        if (!"SUCCESS".equals(payment.getStatus())) {
            throw new RuntimeException("Payment failed");
        }
        // Allocate seat
        flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
        flightRepository.save(flight);
        // Create booking
        Booking booking = new Booking();
        booking.setBookingRef(UUID.randomUUID().toString());
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        bookingRepository.save(booking);
        // Link payment to booking (if needed, set booking in payment)
        payment.setBooking(booking);
        // Send notification
        notificationService.sendConfirmation(passengerId, booking.getBookingRef());
        return booking;
    }

    /**
     * Retrieves booking confirmation details by booking reference.
     * @param bookingRef the booking reference
     * @return Booking entity
     */
    public Booking getBookingConfirmation(String bookingRef) {
        return bookingRepository.findByBookingRef(bookingRef)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking reference"));
    }
}
