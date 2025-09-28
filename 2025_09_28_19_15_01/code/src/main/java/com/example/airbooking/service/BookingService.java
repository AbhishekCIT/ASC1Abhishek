package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.entity.*;
import com.example.airbooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for managing the booking lifecycle.
 */
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private EmailService emailService;

    /**
     * Create a new booking for a flight.
     * @param request BookingRequest
     * @return BookingResponse
     */
    public BookingResponse createBooking(BookingRequest request) {
        // Validate passenger details, seat selection, email format, etc.
        // Save booking and passenger details
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setFlightId(request.getFlightId());
        booking.setStatus("PENDING");
        bookingRepository.save(booking);

        for (PassengerRequest p : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setPassengerId(UUID.randomUUID().toString());
            passenger.setBookingId(booking.getBookingId());
            passenger.setName(p.getName());
            passenger.setEmail(p.getEmail());
            passenger.setSeat(p.getSeat());
            passengerRepository.save(passenger);
        }
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setStatus("PENDING");
        response.seteTicket(null);
        return response;
    }

    /**
     * Get booking confirmation and e-ticket.
     * @param bookingId Booking ID
     * @return BookingResponse
     */
    public BookingResponse getBookingConfirmation(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setStatus(booking.getStatus());
        response.seteTicket("url-to-pdf"); // TODO: Generate and return actual e-ticket URL
        return response;
    }
}
