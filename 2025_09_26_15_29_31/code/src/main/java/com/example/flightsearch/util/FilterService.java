package com.example.flightsearch.util;

import com.example.flightsearch.model.Flight;
import com.example.flightsearch.model.FlightSearchFilters;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Utility service to apply user-selected filters to flight results.
 */
@Component
public class FilterService {
    /**
     * Applies filters to the list of flights.
     * @param flights List of flights
     * @param filters User-selected filters
     * @return Filtered list of flights
     */
    public List<Flight> applyFilters(List<Flight> flights, FlightSearchFilters filters) {
        if (filters == null) return flights;
        return flights.stream()
                .filter(f -> filters.getAirlines() == null || filters.getAirlines().isEmpty() || filters.getAirlines().contains(f.getAirline()))
                .filter(f -> filters.getPriceRange() == null ||
                        (f.getPrice() >= filters.getPriceRange()[0] && f.getPrice() <= filters.getPriceRange()[1]))
                .filter(f -> filters.getStops() == null || Objects.equals(f.getStops(), filters.getStops()))
                .collect(Collectors.toList());
    }
}
