package com.airline.booking.service;

import com.airline.booking.model.*;
import com.airline.booking.repository.BookingRepository;
import com.airline.booking.repository.FlightRepository;
import com.airline.booking.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service for booking management and seat allocation.
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final SeatService seatService;

    /**
     * Create a booking draft (reserves seat, validates input).
     * @param flightId flight ID
     * @param passengerName passenger name
     * @param passengerContact passenger contact
     * @param seatNumber seat number
     * @return Booking
     */
    @Transactional
    public Booking createBooking(String flightId, String passengerName, String passengerContact, String seatNumber) {
        if (StringUtils.isBlank(passengerName)) {
            throw new RuntimeException("Name is required");
        }
        if (StringUtils.isBlank(passengerContact)) {
            throw new RuntimeException("Contact information is required");
        }
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Invalid flight or no seats available"));
        Seat seat = seatService.reserveSeat(flight, seatNumber);
        Passenger passenger = new Passenger();
        passenger.setName(passengerName);
        passenger.setContact(passengerContact);
        passenger = passengerRepository.save(passenger);
        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setFlight(flight);
        booking.setSeat(seat);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("DRAFT");
        return bookingRepository.save(booking);
    }

    /**
     * Confirm a booking after successful payment.
     * @param booking Booking
     * @return Booking
     */
    @Transactional
    public Booking confirmBooking(Booking booking) {
        booking.setStatus("CONFIRMED");
        return bookingRepository.save(booking);
    }
}
