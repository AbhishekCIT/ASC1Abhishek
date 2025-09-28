package com.example.airbooking.service;

import com.example.airbooking.model.SeatMapResponse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for handling seat map and seat availability.
 */
@Service
public class SeatSelectionService {
    /**
     * Get seat map for a flight.
     * @param flightId Flight ID
     * @return SeatMapResponse
     */
    public SeatMapResponse getSeatMap(String flightId) {
        // TODO: Integrate with airline APIs for real-time seat map
        // For demonstration, return mock data
        SeatMapResponse response = new SeatMapResponse();
        response.setFlightId(flightId);
        List<SeatMapResponse.SeatStatus> seats = new ArrayList<>();
        seats.add(new SeatMapResponse.SeatStatus("12A", true));
        seats.add(new SeatMapResponse.SeatStatus("12B", false));
        response.setSeats(seats);
        return response;
    }
}
