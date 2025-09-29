package com.airtransport.filter;

import com.airtransport.model.Flight;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility for filtering flight search results based on user preferences.
 */
@Component
public class FlightResultFilter {
    /**
     * Applies filters (airline, maxPrice, duration) to the list of flights.
     * @param flights List of flights
     * @param filters Map of filter criteria
     * @return Filtered list of flights
     */
    public List<Flight> applyFilters(List<Flight> flights, Map<String, Object> filters) {
        if (filters == null || filters.isEmpty()) {
            return flights;
        }
        return flights.stream()
                .filter(flight -> {
                    // Filter by airline
                    if (filters.containsKey("airline")) {
                        String airline = (String) filters.get("airline");
                        if (!flight.getAirline().equalsIgnoreCase(airline)) {
                            return false;
                        }
                    }
                    // Filter by maxPrice
                    if (filters.containsKey("maxPrice")) {
                        Number maxPrice = (Number) filters.get("maxPrice");
                        if (flight.getPrice() > maxPrice.doubleValue()) {
                            return false;
                        }
                    }
                    // Additional filters (e.g., duration) can be added here
                    return true;
                })
                .collect(Collectors.toList());
    }
}
