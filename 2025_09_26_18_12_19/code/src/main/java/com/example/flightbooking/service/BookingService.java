package com.example.flightbooking.service;

import com.example.flightbooking.model.*;
import com.example.flightbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * Service for managing bookings.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;

    /**
     * Create a new booking record.
     * @param request BookingRequest
     * @param paymentResult PaymentResult
     * @return Booking
     */
    public Booking createBooking(BookingRequest request, PaymentResult paymentResult) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found."));
        // Decrement flight availability
        flight.setAvailability(flight.getAvailability() - 1);
        flightRepository.save(flight);
        Booking booking = new Booking();
        booking.setBookingId(BookingIdUtil.generateId());
        booking.setUserId(request.getUserId());
        booking.setFlightId(request.getFlightId());
        booking.setStatus("CONFIRMED");
        booking.setBookedAt(LocalDateTime.now());
        bookingRepository.save(booking);
        return booking;
    }
}
