package com.example.airbooking.util;

import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utility for locking seats during booking/payment to prevent double-booking
 */
@Component
public class SeatLockService {
    private final Set<String> lockedSeats = new HashSet<>();

    /**
     * Lock seats for a flight
     * @param flightId flight identifier
     * @param seatNos seat numbers to lock
     * @return true if all seats locked successfully
     */
    public boolean lockSeats(Long flightId, List<String> seatNos) {
        for (String seatNo : seatNos) {
            String key = flightId + "-" + seatNo;
            if (lockedSeats.contains(key)) {
                return false; // Seat already locked
            }
        }
        for (String seatNo : seatNos) {
            String key = flightId + "-" + seatNo;
            lockedSeats.add(key);
        }
        return true;
    }
}
