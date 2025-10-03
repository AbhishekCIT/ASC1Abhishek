package com.example.airtransport.repository;

import com.example.airtransport.model.BookingRequest;
import com.example.airtransport.model.BookingResponse;

/**
 * Repository for accessing booking records.
 */
public interface BookingRepository {
    /**
     * Saves a booking and returns the confirmation response.
     * @param request Booking request
     * @return Booking confirmation response
     */
    BookingResponse saveBooking(BookingRequest request);
}
