package com.airtransport.service;

import com.airtransport.model.Booking;
import com.airtransport.model.Flight;
import com.airtransport.repository.BookingRepository;
import com.airtransport.repository.FlightRepository;
import com.airtransport.util.PricingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Service for booking creation, seat reservation, and status updates.
 */
@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PricingUtil pricingUtil;

    /**
     * Create a booking for a given flight and user.
     * @param flightId Flight ID
     * @param userId User ID
     * @param passengerInfo Passenger info map
     * @return Booking object
     */
    public Booking createBooking(String flightId, String userId, Map<String, Object> passengerInfo) {
        // Validate and reserve seat
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("Invalid flight selection."));
        if (flight.getSeatsAvailable() <= 0) {
            throw new IllegalArgumentException("Selected seat no longer available");
        }
        // Reserve seat
        flight.setSeatsAvailable(flight.getSeatsAvailable() - 1);
        flightRepository.save(flight);
        // Calculate price
        double price = pricingUtil.calculatePrice(flightId);
        // Generate ticket number
        String ticketNumber = "T" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId("B" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        booking.setUserId(userId);
        booking.setFlightId(flightId);
        booking.setStatus("PENDING_PAYMENT");
        booking.setTicketNumber(ticketNumber);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPassengerInfo(passengerInfo);
        booking.setPrice(price);
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Update booking status.
     * @param booking Booking object
     */
    public void updateBookingStatus(Booking booking) {
        bookingRepository.save(booking);
    }
}
