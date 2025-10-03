package com.example.airbooking.service;

import com.example.airbooking.entity.*;
import com.example.airbooking.exception.BookingNotFoundException;
import com.example.airbooking.exception.SeatUnavailableException;
import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.model.BookingResponse;
import com.example.airbooking.model.BookingConfirmResponse;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.UserRepository;
import com.example.airbooking.util.AirlineApiClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for managing bookings.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final AirlineApiClientUtil airlineApiClientUtil;

    /**
     * Create a booking for a flight.
     */
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new SeatUnavailableException("Flight not found or unavailable"));
        if (flight.getSeatsAvailable() <= 0) {
            throw new SeatUnavailableException("Seat no longer available");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BookingNotFoundException("User not found"));
        // Reserve seat via airline API (stubbed)
        boolean reserved = airlineApiClientUtil.reserveSeat(flight.getId());
        if (!reserved) {
            throw new SeatUnavailableException("Seat reservation failed");
        }
        // Decrement seat count
        flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
        flightRepository.save(flight);
        String confirmationNumber = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Booking booking = Booking.builder()
                .user(user)
                .flight(flight)
                .status("DRAFT")
                .confirmationNumber(confirmationNumber)
                .bookedAt(LocalDateTime.now())
                .build();
        booking = bookingRepository.save(booking);
        return BookingResponse.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus())
                .amount(flight.getPrice())
                .confirmationNumber(booking.getConfirmationNumber())
                .build();
    }

    /**
     * Confirm a booking by ID.
     */
    @Transactional(readOnly = true)
    public BookingConfirmResponse confirmBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found"));
        return BookingConfirmResponse.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus())
                .confirmationNumber(booking.getConfirmationNumber())
                .build();
    }
}
