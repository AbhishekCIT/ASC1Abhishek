package com.example.airbooking.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Request model for online check-in.
 */
public class CheckInRequest {
    @NotBlank(message = "Booking ID is required")
    private String bookingId;
    @NotNull(message = "Passengers are required")
    private List<PassengerCheckIn> passengers;

    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public List<PassengerCheckIn> getPassengers() { return passengers; }
    public void setPassengers(List<PassengerCheckIn> passengers) { this.passengers = passengers; }

    public static class PassengerCheckIn {
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Seat is required")
        private String seat;
        private int baggage;
        private List<String> specialRequests;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getSeat() { return seat; }
        public void setSeat(String seat) { this.seat = seat; }
        public int getBaggage() { return baggage; }
        public void setBaggage(int baggage) { this.baggage = baggage; }
        public List<String> getSpecialRequests() { return specialRequests; }
        public void setSpecialRequests(List<String> specialRequests) { this.specialRequests = specialRequests; }
    }
}
