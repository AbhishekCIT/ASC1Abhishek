package com.airline.flightbooking.service;

import com.airline.flightbooking.exception.ValidationException;
import com.airline.flightbooking.model.*;
import com.airline.flightbooking.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Service for booking creation, validation, and confirmation.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;
    private final AuditLogService auditLogService;

    /**
     * Create a new booking for a flight and passenger.
     * @param flightId flight to book
     * @param passenger passenger details
     * @return created Booking
     */
    @Transactional
    public Booking createBooking(String flightId, Passenger passenger) {
        // Validate passenger
        if (passenger.getName() == null || passenger.getName().trim().isEmpty()) {
            throw new ValidationException("Passenger name required");
        }
        if (passenger.getId() == null || passenger.getId().trim().isEmpty()) {
            throw new ValidationException("Invalid passenger ID");
        }
        // Save passenger if not exists
        passengerRepository.save(passenger);
        // Validate flight
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ValidationException("Flight not found"));
        // Create booking
        Booking booking = Booking.builder()
                .bookingId(UUID.randomUUID().toString())
                .status("PENDING")
                .flight(flight)
                .passenger(passenger)
                .bookingDate(LocalDate.now())
                .build();
        bookingRepository.save(booking);
        auditLogService.logEvent("BOOKING_CREATED", "Booking created for passenger: " + passenger.getName());
        return booking;
    }

    /**
     * Confirm a booking after successful payment.
     * @param bookingId booking to confirm
     * @return confirmed Booking
     */
    @Transactional
    public Booking confirmBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ValidationException("Booking not found"));
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        auditLogService.logEvent("BOOKING_CONFIRMED", "Booking confirmed: " + bookingId);
        return booking;
    }
}
