package com.example.airbooking.service;

import com.example.airbooking.model.BookingRequest;
import com.example.airbooking.model.BookingResponse;
import com.example.airbooking.entity.Booking;
import com.example.airbooking.entity.Flight;
import com.example.airbooking.entity.User;
import com.example.airbooking.repository.BookingRepository;
import com.example.airbooking.repository.FlightRepository;
import com.example.airbooking.repository.UserRepository;
import com.example.airbooking.exception.BookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for managing booking creation and status.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Create a booking for a user and flight.
     * @param request the booking request
     * @return booking response
     */
    public BookingResponse createBooking(BookingRequest request) {
        // Validate input
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new BookingException("Flight not found"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new BookingException("User not found"));
        // Create booking entity
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setUser(user);
        booking.setStatus("PENDING");
        booking.setPassengerDetails(request.getPassengerDetails());
        booking.setCreatedAt(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        // Return response
        return new BookingResponse(booking.getBookingId(), booking.getStatus());
    }
}
