package com.example.airbooking.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class SeatLockServiceTest {
    private SeatLockService seatLockService;

    @BeforeEach
    void setUp() {
        seatLockService = new SeatLockService();
    }

    @Test
    @DisplayName("lockSeats - normal scenario")
    void testLockSeatsNormal() {
        boolean result = seatLockService.lockSeats(1L, Arrays.asList("12A", "12B"));
        assertTrue(result);
    }

    @Test
    @DisplayName("lockSeats - double booking scenario")
    void testLockSeatsDoubleBooking() {
        assertTrue(seatLockService.lockSeats(1L, Collections.singletonList("12A")));
        // Try to lock the same seat again
        assertFalse(seatLockService.lockSeats(1L, Collections.singletonList("12A")));
    }

    @Test
    @DisplayName("lockSeats - empty seat list")
    void testLockSeatsEmptyList() {
        assertTrue(seatLockService.lockSeats(1L, Collections.emptyList()));
    }

    @Test
    @DisplayName("lockSeats - different flights, same seat number")
    void testLockSeatsDifferentFlights() {
        assertTrue(seatLockService.lockSeats(1L, Collections.singletonList("12A")));
        // Should succeed for a different flight
        assertTrue(seatLockService.lockSeats(2L, Collections.singletonList("12A")));
    }
}
