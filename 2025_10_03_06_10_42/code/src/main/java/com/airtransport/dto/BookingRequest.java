package com.airtransport.dto;

import java.util.List;
import java.util.Map;

/**
 * DTO for booking request payload.
 */
public class BookingRequest {
    private Long flightId;
    private List<PassengerDTO> passengerDetails;
    private List<String> seatSelection;
    private String paymentMethod;
    private Map<String, Object> paymentInfo;

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public List<PassengerDTO> getPassengerDetails() { return passengerDetails; }
    public void setPassengerDetails(List<PassengerDTO> passengerDetails) { this.passengerDetails = passengerDetails; }
    public List<String> getSeatSelection() { return seatSelection; }
    public void setSeatSelection(List<String> seatSelection) { this.seatSelection = seatSelection; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Map<String, Object> getPaymentInfo() { return paymentInfo; }
    public void setPaymentInfo(Map<String, Object> paymentInfo) { this.paymentInfo = paymentInfo; }
}
