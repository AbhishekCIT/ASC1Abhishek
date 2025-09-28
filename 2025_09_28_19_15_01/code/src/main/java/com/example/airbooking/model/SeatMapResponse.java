package com.example.airbooking.model;

import java.util.List;

/**
 * Response model for seat map API.
 */
public class SeatMapResponse {
    private String flightId;
    private List<SeatStatus> seats;

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public List<SeatStatus> getSeats() { return seats; }
    public void setSeats(List<SeatStatus> seats) { this.seats = seats; }

    public static class SeatStatus {
        private String seat;
        private boolean available;
        public SeatStatus() {}
        public SeatStatus(String seat, boolean available) {
            this.seat = seat;
            this.available = available;
        }
        public String getSeat() { return seat; }
        public void setSeat(String seat) { this.seat = seat; }
        public boolean isAvailable() { return available; }
        public void setAvailable(boolean available) { this.available = available; }
    }
}
