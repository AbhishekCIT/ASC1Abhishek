package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.BookingRepository;
import com.example.flightbooking.repository.FlightRepository;
import com.example.flightbooking.repository.UserRepository;
import com.example.flightbooking.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service for managing flight bookings.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationUtil validationUtil;

    /**
     * Books a flight and returns booking details.
     * @param request Booking request
     * @param token User authentication token
     * @return Booking object
     */
    @Transactional
    public Booking bookFlight(BookingRequest request, String token) {
        // Authenticate user
        User user = userService.authenticate(token);
        // Validate flight availability
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(() -> new RuntimeException("FlightNotAvailableException: Selected flight not available"));
        // Generate unique booking reference
        String bookingReference = "BR" + UUID.randomUUID().toString().replace("-", "").substring(0,8).toUpperCase();
        // Create booking entity
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingReference(bookingReference);
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("CONFIRMED");
        booking.setPassengerDetails(request.getPassengerDetails());
        // Save booking
        return bookingRepository.save(booking);
    }
}
