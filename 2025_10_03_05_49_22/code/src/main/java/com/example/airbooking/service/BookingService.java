package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.repository.*;
import com.example.airbooking.util.PaymentGatewayService;
import com.example.airbooking.util.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service for booking logic, including seat, payment, and notification.
 */
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;
    private final SeatAvailabilityService seatAvailabilityService;
    private final PaymentGatewayService paymentGatewayService;
    private final NotificationService notificationService;

    @Autowired
    public BookingService(BookingRepository bookingRepository,
                         PassengerRepository passengerRepository,
                         FlightRepository flightRepository,
                         SeatRepository seatRepository,
                         SeatAvailabilityService seatAvailabilityService,
                         PaymentGatewayService paymentGatewayService,
                         NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
        this.seatAvailabilityService = seatAvailabilityService;
        this.paymentGatewayService = paymentGatewayService;
        this.notificationService = notificationService;
    }

    /**
     * Books a ticket for a passenger with validations and payment processing.
     * @param flightId Flight ID
     * @param passengerInfo Passenger info
     * @param seatNumber Seat number
     * @param baggageCount Number of baggage
     * @param paymentInfo Payment info
     * @return Booking confirmation or error
     */
    @Transactional
    public Booking bookTicket(Long flightId, Passenger passengerInfo, String seatNumber, int baggageCount, PaymentInfo paymentInfo) throws Exception {
        // Validate passenger info
        if (passengerInfo == null || passengerInfo.getName() == null || passengerInfo.getContact() == null || passengerInfo.getPassport() == null) {
            throw new IllegalArgumentException("Invalid passenger information");
        }

        // Validate flight
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (!flightOpt.isPresent()) {
            throw new IllegalArgumentException("Flight not found or expired");
        }
        Flight flight = flightOpt.get();

        // Validate seat availability
        if (!seatAvailabilityService.isSeatAvailable(flight, seatNumber)) {
            throw new IllegalStateException("Seat not available");
        }
        Seat seat = seatRepository.findByFlightAndSeatNumber(flight, seatNumber).orElseThrow(() -> new IllegalStateException("Seat not found"));

        // Calculate total fare (simplified: basePrice + baggage fee)
        double totalFare = flight.getBasePrice() + baggageCount * 30.0; // Assume $30 per baggage

        // Process payment
        boolean paymentSuccess = paymentGatewayService.processPayment(paymentInfo, totalFare);
        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed. Booking not confirmed.");
        }

        // Save passenger if new
        Passenger passenger = passengerRepository.save(passengerInfo);

        // Mark seat as unavailable
        seatAvailabilityService.markSeatUnavailable(seat);

        // Save booking
        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setSeat(seat);
        booking.setTotalFare(totalFare);
        booking.setStatus("CONFIRMED");
        booking.setBookedAt(LocalDateTime.now());
        booking.setConfirmationCode(generateConfirmationCode());
        Booking savedBooking = bookingRepository.save(booking);

        // Send confirmation
        notificationService.sendBookingConfirmation(passenger.getContact(), savedBooking);

        return savedBooking;
    }

    private String generateConfirmationCode() {
        // Generate a random confirmation code (simple implementation)
        return "ABC" + System.currentTimeMillis() % 1000000;
    }
}
