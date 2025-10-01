package com.airtransport.booking.service;

import com.airtransport.booking.entity.Booking;
import com.airtransport.booking.entity.Flight;
import com.airtransport.booking.entity.User;
import com.airtransport.booking.model.BookingRequest;
import com.airtransport.booking.repository.BookingRepository;
import com.airtransport.booking.repository.FlightRepository;
import com.airtransport.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    /**
     * Creates a new booking for a user and flight.
     * Throws exception if flight not available.
     */
    public Booking createBooking(String userEmail, BookingRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(userEmail);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Optional<Flight> flightOpt = flightRepository.findById(request.getFlightId());
        if (flightOpt.isEmpty()) {
            throw new IllegalArgumentException("Flight not available");
        }
        Flight flight = flightOpt.get();
        // Calculate total price (passengers * price)
        double totalPrice = flight.getPrice() * request.getPassengerDetails().size();
        Booking booking = Booking.builder()
                .id(UUID.randomUUID().toString())
                .user(userOpt.get())
                .flight(flight)
                .bookingDate(LocalDateTime.now())
                .status("PENDING")
                .totalPrice(totalPrice)
                .build();
        return bookingRepository.save(booking);
    }

    /**
     * Confirms a booking and returns the booking if found.
     */
    public Booking confirmBooking(String bookingRef) {
        return bookingRepository.findById(bookingRef)
                .orElseThrow(() -> new IllegalArgumentException("Booking reference invalid"));
    }
}
