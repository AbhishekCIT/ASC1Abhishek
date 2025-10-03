package com.example.airbooking.repository;

import com.example.airbooking.model.Booking;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookingRepository interface (mock-based).
 */
public class BookingRepositoryTest {
    /**
     * Test findByBookingRef returns booking when found.
     */
    @Test
    void testFindByBookingRef_Found() {
        BookingRepository repo = mock(BookingRepository.class);
        Booking booking = new Booking();
        when(repo.findByBookingRef("ABC123")).thenReturn(booking);
        assertEquals(booking, repo.findByBookingRef("ABC123"));
    }

    /**
     * Test findByBookingRef returns null when not found.
     */
    @Test
    void testFindByBookingRef_NotFound() {
        BookingRepository repo = mock(BookingRepository.class);
        when(repo.findByBookingRef("XYZ999")).thenReturn(null);
        assertNull(repo.findByBookingRef("XYZ999"));
    }
}
