package com.airtransport.repository;

import com.airtransport.model.Booking;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRepository.
 */
@DataJpaTest
class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    /**
     * Test saving and retrieving a Booking entity.
     */
    @Test
    void testSaveAndFindBooking() {
        Booking booking = new Booking();
        booking.setBookingId("B123");
        booking.setUserId("U456");
        booking.setFlightId("F789");
        booking.setStatus("CONFIRMED");
        booking.setTicketNumber("T0001");
        bookingRepository.save(booking);

        Booking found = bookingRepository.findById("B123").orElse(null);
        assertNotNull(found);
        assertEquals("U456", found.getUserId());
        assertEquals("F789", found.getFlightId());
        assertEquals("CONFIRMED", found.getStatus());
        assertEquals("T0001", found.getTicketNumber());
    }
}
