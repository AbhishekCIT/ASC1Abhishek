package com.example.booking.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Model representing a booking request.
 */
public class BookingRequest {
    @NotBlank(message = "Flight ID is required")
    private String flightId;
    @NotEmpty(message = "Passenger details are required")
    private List<PassengerDetails> passengerDetails;
    private List<String> seatSelection;
    @NotNull(message = "Payment info is required")
    private PaymentInfo paymentInfo;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }

    public List<PassengerDetails> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDetails> passengerDetails) { this.passengerDetails = passengerDetails; }

    public List<String> getSeatSelection() { return seatSelection; }
    public void setSeatSelection(List<String> seatSelection) { this.seatSelection = seatSelection; }

    public PaymentInfo getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(PaymentInfo paymentInfo) { this.paymentInfo = paymentInfo; }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "flightId='" + flightId + '\'' +
                ", passengerDetails=" + passengerDetails +
                ", seatSelection=" + seatSelection +
                ", paymentInfo=" + paymentInfo +
                '}';
    }
}
