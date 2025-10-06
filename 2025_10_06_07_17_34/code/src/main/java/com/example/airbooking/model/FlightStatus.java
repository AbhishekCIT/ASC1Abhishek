package com.example.airbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight_status")
public class FlightStatus {
    @Id
    private String statusId;
    private String flightNumber;
    private LocalDateTime updatedAt;
    private String status;
    private String gate;

    // Getters and Setters
    public String getStatusId() { return statusId; }
    public void setStatusId(String statusId) { this.statusId = statusId; }
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }
}