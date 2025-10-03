package com.airtransport.service;

import com.airtransport.model.*;
import com.airtransport.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    /**
     * Reserve seats and create booking
     * @param flightId Flight ID
     * @param passengerDetails List of passenger details
     * @param seatNumbers List of seat numbers
     * @return Booking object
     */
    @Transactional
    public Booking createBooking(Integer flightId, List<Passenger> passengerDetails, List<String> seatNumbers) {
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        // Check seat availability
        List<Seat> seats = seatRepository.findAllById(seatNumbers);
        for (Seat seat : seats) {
            if (!seat.getIsAvailable()) {
                throw new IllegalStateException("Seat " + seat.getSeatNumber() + " is unavailable");
            }
        }
        // Reserve seats
        for (Seat seat : seats) {
            seat.setIsAvailable(false);
            seatRepository.save(seat);
        }
        // Create booking
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setStatus("PENDING_PAYMENT");
        booking.setBookingDate(LocalDateTime.now());
        booking.setFlight(flight);
        booking.setTotalAmount(flight.getPrice() * passengerDetails.size());
        booking.setPassengers(passengerDetails);
        for (Passenger p : passengerDetails) {
            p.setBooking(booking);
            passengerRepository.save(p);
        }
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Confirm booking after successful payment
     * @param bookingId Booking ID
     * @return Booking object
     */
    @Transactional
    public Booking confirmBooking(String bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        if (booking.getPayment() == null || !"SUCCESS".equals(booking.getPayment().getStatus())) {
            throw new IllegalStateException("Booking cannot be confirmed before payment");
        }
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
        return booking;
    }

    /**
     * Get booking details
     * @param bookingId Booking ID
     * @return Booking object
     */
    public Booking getBookingDetails(String bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }
}
