package com.airtransport.model;

import com.airtransport.entity.Booking;
import com.airtransport.entity.Passenger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookingRequest model.
 */
class BookingRequestTest {
    /**
     * Test getters, setters, and utility methods for BookingRequest.
     */
    @Test
    @DisplayName("BookingRequest getters, setters, and utility methods work as expected")
    void testBookingRequest() {
        BookingRequest request = new BookingRequest();
        String flightId = "F123";
        String userId = "U456";
        PassengerDetail passengerDetail = new PassengerDetail("John Doe", "P1234567");
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCardNumber("4111111111111111");
        paymentInfo.setExpiry("12/30");
        paymentInfo.setCvv("123");
        paymentInfo.setCardholderName("John Doe");
        request.setFlightId(flightId);
        request.setUserId(userId);
        request.setPassengerDetails(List.of(passengerDetail));
        request.setPaymentInfo(paymentInfo);

        assertEquals(flightId, request.getFlightId());
        assertEquals(userId, request.getUserId());
        assertEquals(1, request.getPassengerDetails().size());
        assertEquals(paymentInfo, request.getPaymentInfo());

        // Test toBookingEntity utility
        String bookingRef = "BR123456";
        LocalDateTime bookingDate = LocalDateTime.now();
        Booking booking = request.toBookingEntity(bookingRef, bookingDate);
        assertEquals(bookingRef, booking.getBookingRef());
        assertEquals(userId, booking.getUserId());
        assertEquals(flightId, booking.getFlightId());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals("CONFIRMED", booking.getStatus());

        // Test toPassengerEntities utility
        List<Passenger> passengers = request.toPassengerEntities(bookingRef);
        assertEquals(1, passengers.size());
        Passenger p = passengers.get(0);
        assertEquals(bookingRef, p.getBookingRef());
        assertEquals(passengerDetail.getName(), p.getName());
        assertEquals(passengerDetail.getPassportNo(), p.getPassportNo());
    }

    /**
     * Test default values for BookingRequest.
     */
    @Test
    @DisplayName("BookingRequest default values are null")
    void testBookingRequestDefaults() {
        BookingRequest request = new BookingRequest();
        assertNull(request.getFlightId());
        assertNull(request.getUserId());
        assertNull(request.getPassengerDetails());
        assertNull(request.getPaymentInfo());
    }
}
