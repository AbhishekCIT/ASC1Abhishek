package com.example.airlinebooking.repository;

import com.example.airlinebooking.model.Booking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * JUnit tests for BookingRepository interface.
 * Focuses on custom method and repository contract.
 */
class BookingRepositoryTest {
    @Test
    @DisplayName("findByBookingRef: returns booking when found")
    void testFindByBookingRefFound() {
        BookingRepository repo = mock(BookingRepository.class);
        Booking booking = new Booking();
        booking.setBookingRef("BR123");
        when(repo.findByBookingRef("BR123")).thenReturn(Optional.of(booking));
        Optional<Booking> result = repo.findByBookingRef("BR123");
        assertTrue(result.isPresent());
        assertEquals("BR123", result.get().getBookingRef());
    }

    @Test
    @DisplayName("findByBookingRef: returns empty when not found")
    void testFindByBookingRefNotFound() {
        BookingRepository repo = mock(BookingRepository.class);
        when(repo.findByBookingRef("NOT_FOUND")).thenReturn(Optional.empty());
        Optional<Booking> result = repo.findByBookingRef("NOT_FOUND");
        assertFalse(result.isPresent());
    }
}
