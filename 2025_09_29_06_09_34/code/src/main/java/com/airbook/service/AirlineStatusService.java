package com.airbook.service;

import org.springframework.stereotype.Service;

/**
 * Service for integrating with airline status feeds
 */
@Service
public class AirlineStatusService {
    /**
     * DTO for flight status update
     */
    public static class FlightStatusUpdate {
        private String userId;
        private String flightId;
        private String status;
        private String details;
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
        public String getFlightId() { return flightId; }
        public void setFlightId(String flightId) { this.flightId = flightId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}
