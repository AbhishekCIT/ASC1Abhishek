package com.airtransport.repository;

import com.airtransport.entity.BookingEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for BookingRepository.
 * Covers CRUD operations, edge cases, and error scenarios.
 */
@DataJpaTest
class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Test saving and retrieving a booking (normal case).
     */
    @Test
    void testSaveAndFindById_Normal() {
        BookingEntity booking = new BookingEntity();
        booking.setBookingId("B123");
        booking.setUserId("U456");
        booking.setFlightId("F789");
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());
        bookingRepository.save(booking);
        Optional<BookingEntity> found = bookingRepository.findById("B123");
        assertTrue(found.isPresent());
        assertEquals("U456", found.get().getUserId());
    }

    /**
     * Test finding a non-existent booking (edge case).
     */
    @Test
    void testFindById_NotFound() {
        Optional<BookingEntity> found = bookingRepository.findById("NON_EXISTENT");
        assertFalse(found.isPresent());
    }

    /**
     * Test deleting a booking (normal case).
     */
    @Test
    void testDeleteById_Normal() {
        BookingEntity booking = new BookingEntity();
        booking.setBookingId("B456");
        bookingRepository.save(booking);
        bookingRepository.deleteById("B456");
        Optional<BookingEntity> found = bookingRepository.findById("B456");
        assertFalse(found.isPresent());
    }

    /**
     * Test saving booking with null ID (error scenario).
     */
    @Test
    void testSave_NullId() {
        BookingEntity booking = new BookingEntity();
        booking.setBookingId(null);
        assertThrows(Exception.class, () -> bookingRepository.save(booking));
    }
}
