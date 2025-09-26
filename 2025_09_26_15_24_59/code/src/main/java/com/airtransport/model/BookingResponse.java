package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response model for booking a flight.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private String bookingRef;
    private String status;
    private Object details;
    private String errorCode;
    private String message;

    public static BookingResponse success(String bookingRef, String status, Object details) {
        return new BookingResponse(bookingRef, status, details, null, null);
    }

    public static BookingResponse failure(String errorCode, String message) {
        return new BookingResponse(null, null, null, errorCode, message);
    }
}
