package com.example.airbooking.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * Request model for creating a booking.
 */
public class BookingRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;
    @NotNull(message = "Passengers are required")
    private List<PassengerRequest> passengers;
    @NotNull(message = "Payment details are required")
    private PaymentRequest payment;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public List<PassengerRequest> getPassengers() { return passengers; }
    public void setPassengers(List<PassengerRequest> passengers) { this.passengers = passengers; }
    public PaymentRequest getPayment() { return payment; }
    public void setPayment(PaymentRequest payment) { this.payment = payment; }
}

/**
 * Request model for a passenger in a booking.
 */
class PassengerRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Seat is required")
    private String seat;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSeat() { return seat; }
    public void setSeat(String seat) { this.seat = seat; }
}
