package com.example.airbooking.service;

import com.example.airbooking.model.*;
import com.example.airbooking.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for booking creation, seat reservation, and confirmation retrieval
 */
@Service
public class BookingService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailService emailService;

    /**
     * Create a new booking, reserve seat, and send confirmation
     * @param bookingRequest booking details
     * @return booking confirmation
     * @throws BookingOverbookedException if seat is unavailable
     * @throws PaymentAuthorizationException if payment fails
     */
    public BookingConfirmation createBooking(BookingRequest bookingRequest) {
        // TODO: Reserve seat via airline API, process payment atomically
        // On success, send confirmation email
        throw new BookingOverbookedException("Seat is no longer available.");
    }

    /**
     * Retrieve booking confirmation and itinerary
     * @param bookingId booking identifier
     * @return booking confirmation
     * @throws BookingNotFoundException if booking does not exist
     */
    public BookingConfirmation getBookingConfirmation(String bookingId) {
        // TODO: Fetch booking and itinerary from DB
        throw new BookingNotFoundException("Booking not found.");
    }
}
