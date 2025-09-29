package com.airbook.service;

import com.airbook.model.Booking;
import com.airbook.model.Flight;
import com.airbook.repository.BookingRepository;
import com.airbook.repository.FlightRepository;
import com.airbook.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service for booking creation, confirmation, and notification
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private NotificationService notificationService;

    /**
     * Create a new booking and send e-ticket notification
     * @param booking Booking request
     * @return Confirmed booking
     */
    public Booking createBooking(Booking booking) {
        // Validate flight availability
        Flight flight = flightRepository.findById(booking.getFlightId());
        if (flight == null) {
            throw new IllegalArgumentException("Flight not found or unavailable");
        }
        // Generate booking ID and e-ticket
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setStatus("CONFIRMED");
        booking.setETicket("ETK" + UUID.randomUUID().toString().substring(0, 8));
        bookingRepository.save(booking);
        // Send e-ticket notification
        notificationService.sendETicket(booking);
        return booking;
    }

    /**
     * Get booking confirmation by booking ID
     * @param bookingId Booking ID
     * @return Booking confirmation
     */
    public Booking getBookingConfirmation(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking ID not found");
        }
        return booking;
    }
}
