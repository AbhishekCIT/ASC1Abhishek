package com.example.airbooking.model;

import java.util.List;

/**
 * Response model for flight search results.
 */
public class FlightSearchResponse {
    private List<FlightInfo> flights;

    public FlightSearchResponse(List<FlightInfo> flights) {
        this.flights = flights;
    }
    public FlightSearchResponse() {}

    public List<FlightInfo> getFlights() { return flights; }
    public void setFlights(List<FlightInfo> flights) { this.flights = flights; }

    /**
     * Inner class representing flight information.
     */
    public static class FlightInfo {
        private String airline;
        private String time;
        private double price;
        private String flightId;

        public FlightInfo(String airline, String time, double price, String flightId) {
            this.airline = airline;
            this.time = time;
            this.price = price;
            this.flightId = flightId;
        }
        public FlightInfo() {}
        public String getAirline() { return airline; }
        public void setAirline(String airline) { this.airline = airline; }
        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public String getFlightId() { return flightId; }
        public void setFlightId(String flightId) { this.flightId = flightId; }
    }
}
