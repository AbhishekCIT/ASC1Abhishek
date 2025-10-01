package com.example.flightsearch.util;

import com.example.flightsearch.entity.Flight;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility service for applying filters to flight results.
 */
@Component
public class FilterService {

    /**
     * Applies filters and sorting to the list of flights.
     * @param flights List of flights to filter
     * @param filters Map of filters (e.g., price, airline, duration)
     * @return Filtered and sorted list of flights
     */
    public List<Flight> applyFilters(List<Flight> flights, Map<String, String> filters) {
        if (filters == null) return flights;
        List<Flight> filtered = flights;
        // Filter by airline
        if (filters.containsKey("airline")) {
            String airline = filters.get("airline");
            filtered = filtered.stream()
                    .filter(f -> f.getAirline().equalsIgnoreCase(airline))
                    .collect(Collectors.toList());
        }
        // Sort by price
        if (filters.containsKey("price")) {
            String priceOrder = filters.get("price");
            if ("asc".equalsIgnoreCase(priceOrder)) {
                filtered = filtered.stream()
                        .sorted(Comparator.comparing(Flight::getPrice))
                        .collect(Collectors.toList());
            } else if ("desc".equalsIgnoreCase(priceOrder)) {
                filtered = filtered.stream()
                        .sorted(Comparator.comparing(Flight::getPrice).reversed())
                        .collect(Collectors.toList());
            }
        }
        // Sort by duration
        if (filters.containsKey("duration")) {
            String durationOrder = filters.get("duration");
            if ("shortest".equalsIgnoreCase(durationOrder)) {
                filtered = filtered.stream()
                        .sorted(Comparator.comparing(Flight::getDurationMinutes))
                        .collect(Collectors.toList());
            } else if ("longest".equalsIgnoreCase(durationOrder)) {
                filtered = filtered.stream()
                        .sorted(Comparator.comparing(Flight::getDurationMinutes).reversed())
                        .collect(Collectors.toList());
            }
        }
        return filtered;
    }
}
