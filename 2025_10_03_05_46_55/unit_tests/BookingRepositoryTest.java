package com.asc.airbooking.repository;

import com.asc.airbooking.entity.Booking;
import com.asc.airbooking.entity.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRepository.
 * Covers normal, edge, and error scenarios for findByConfirmationId.
 */
@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Purpose: Test saving and finding a booking by confirmationId.
     */
    @Test
    void testFindByConfirmationId_Success() {
        Flight flight = new Flight("FL123", "Delta", "JFK", "LAX", LocalDateTime.now(), 320.00);
        Booking booking = new Booking("CONF123", flight, "John Doe", "john@example.com", LocalDateTime.now(), "success");
        bookingRepository.save(booking);
        Optional<Booking> found = bookingRepository.findByConfirmationId("CONF123");
        assertTrue(found.isPresent());
        assertEquals("CONF123", found.get().getConfirmationId());
    }

    /**
     * Purpose: Test finding a booking with non-existent confirmationId (should return empty Optional).
     */
    @Test
    void testFindByConfirmationId_NotFound() {
        Optional<Booking> found = bookingRepository.findByConfirmationId("NON_EXISTENT");
        assertFalse(found.isPresent());
    }

    /**
     * Purpose: Test edge case with null confirmationId (should return empty Optional).
     */
    @Test
    void testFindByConfirmationId_Null() {
        Optional<Booking> found = bookingRepository.findByConfirmationId(null);
        assertFalse(found.isPresent());
    }
}
