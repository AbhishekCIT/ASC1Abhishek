package com.airline.booking.service;

import com.airline.booking.model.Flight;
import com.airline.booking.model.Seat;
import com.airline.booking.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for seat management and reservation.
 */
@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    /**
     * Get available seats for a flight.
     * @param flight Flight
     * @return list of available seats
     */
    public List<Seat> getAvailableSeats(Flight flight) {
        return seatRepository.findByFlight(flight).stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    /**
     * Reserve a seat for a booking.
     * @param flight Flight
     * @param seatNumber Seat number
     * @return reserved Seat
     */
    @Transactional
    public Seat reserveSeat(Flight flight, String seatNumber) {
        Seat seat = seatRepository.findByFlightAndSeatNumber(flight, seatNumber)
                .orElseThrow(() -> new RuntimeException("Selected seat is not available"));
        if (!seat.isAvailable()) {
            throw new RuntimeException("Selected seat is not available");
        }
        seat.setAvailable(false);
        return seatRepository.save(seat);
    }
}
