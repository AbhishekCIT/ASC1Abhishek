package com.example.airbooking.service;

import com.example.airbooking.model.Flight;
import com.example.airbooking.model.Seat;
import com.example.airbooking.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to check real-time seat availability.
 */
@Service
public class SeatAvailabilityService {
    private final SeatRepository seatRepository;

    @Autowired
    public SeatAvailabilityService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /**
     * Checks if a seat is available for a given flight.
     * @param flight Flight entity
     * @param seatNumber Seat number
     * @return true if available, false otherwise
     */
    public boolean isSeatAvailable(Flight flight, String seatNumber) {
        Optional<Seat> seatOpt = seatRepository.findByFlightAndSeatNumber(flight, seatNumber);
        return seatOpt.map(Seat::getIsAvailable).orElse(false);
    }

    /**
     * Marks a seat as unavailable (booked).
     * @param seat Seat entity
     */
    public void markSeatUnavailable(Seat seat) {
        seat.setIsAvailable(false);
        seatRepository.save(seat);
    }
}
