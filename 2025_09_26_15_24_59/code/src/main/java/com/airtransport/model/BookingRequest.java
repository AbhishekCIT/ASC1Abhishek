package com.airtransport.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

/**
 * Request model for booking a flight.
 */
@Data
public class BookingRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotEmpty(message = "Passenger details are required")
    @Valid
    private List<PassengerDetail> passengerDetails;

    @NotNull(message = "Payment info is required")
    @Valid
    private PaymentInfo paymentInfo;

    // Utility to convert to Booking entity (for persistence)
    public com.airtransport.entity.Booking toBookingEntity(String bookingRef, java.time.LocalDateTime bookingDate) {
        com.airtransport.entity.Booking booking = new com.airtransport.entity.Booking();
        booking.setBookingRef(bookingRef);
        booking.setUserId(userId);
        booking.setFlightId(flightId);
        booking.setBookingDate(bookingDate);
        booking.setStatus("CONFIRMED");
        return booking;
    }

    // Utility to convert to Passenger entities (for persistence)
    public List<com.airtransport.entity.Passenger> toPassengerEntities(String bookingRef) {
        return passengerDetails.stream().map(pd -> {
            com.airtransport.entity.Passenger p = new com.airtransport.entity.Passenger();
            p.setBookingRef(bookingRef);
            p.setName(pd.getName());
            p.setPassportNo(pd.getPassportNo());
            return p;
        }).toList();
    }
}
