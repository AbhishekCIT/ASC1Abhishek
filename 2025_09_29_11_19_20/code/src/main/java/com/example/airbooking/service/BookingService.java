package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service for managing bookings and seat reservations.
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final SeatRepository seatRepository;
    private final PaymentService paymentService;
    private final EmailService emailService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                         FlightRepository flightRepository,
                         PassengerRepository passengerRepository,
                         SeatRepository seatRepository,
                         PaymentService paymentService,
                         EmailService emailService) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.seatRepository = seatRepository;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    /**
     * Creates a booking, reserves a seat, and processes payment.
     * @param flightId flight ID
     * @param passengerDetails passenger details
     * @param seatNumber seat number
     * @param paymentInfo payment info
     * @return booking confirmation or error
     */
    @Transactional
    public BookingResponse createBooking(Long flightId, Passenger passengerDetails, String seatNumber, PaymentInfo paymentInfo) {
        // Validate passenger details
        if (passengerDetails.getName() == null || passengerDetails.getEmail() == null) {
            throw new IllegalArgumentException("Missing passenger details");
        }
        // Validate flight and seat
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (!flightOpt.isPresent()) {
            throw new IllegalArgumentException("Flight unavailable");
        }
        Flight flight = flightOpt.get();
        Optional<Seat> seatOpt = seatRepository.findByFlightAndSeatNumber(flight, seatNumber);
        if (!seatOpt.isPresent() || !seatOpt.get().getIsAvailable()) {
            throw new IllegalArgumentException("Seat unavailable");
        }
        Seat seat = seatOpt.get();
        // Save passenger
        Passenger passenger = passengerRepository.save(passengerDetails);
        // Create booking
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setPassenger(passenger);
        booking.setSeat(seatNumber);
        booking.setStatus("PENDING");
        booking.setCreatedAt(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        // Reserve seat
        seat.setIsAvailable(false);
        seatRepository.save(seat);
        // Process payment
        Payment payment = paymentService.processPayment(paymentInfo, booking, flight.getPrice());
        if (!"CONFIRMED".equals(payment.getStatus())) {
            // Release seat and mark booking as failed
            seat.setIsAvailable(true);
            seatRepository.save(seat);
            booking.setStatus("FAILED");
            bookingRepository.save(booking);
            return new BookingResponse(null, "FAILED", false, "Payment failed");
        }
        // Mark booking as confirmed
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        // Send confirmation email
        emailService.sendBookingConfirmation(passenger.getEmail(), booking);
        return new BookingResponse(booking.getId(), "CONFIRMED", true, null);
    }
}
