package com.example.airlinebooking.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Response model for flight search API.
 */
public class FlightSearchResponse {
    private List<FlightInfo> flights;

    public List<FlightInfo> getFlights() { return flights; }
    public void setFlights(List<FlightInfo> flights) { this.flights = flights; }

    /**
     * Model for flight information in search response.
     */
    public static class FlightInfo {
        private Integer flightId;
        private String origin;
        private String destination;
        private BigDecimal price;
        private Integer seatsAvailable;

        public Integer getFlightId() { return flightId; }
        public void setFlightId(Integer flightId) { this.flightId = flightId; }
        public String getOrigin() { return origin; }
        public void setOrigin(String origin) { this.origin = origin; }
        public String getDestination() { return destination; }
        public void setDestination(String destination) { this.destination = destination; }
        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }
        public Integer getSeatsAvailable() { return seatsAvailable; }
        public void setSeatsAvailable(Integer seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    }
}
