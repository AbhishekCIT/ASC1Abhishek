package com.airtransport.service;

import com.airtransport.model.FlightOption;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service to apply filters and sorting to flight search results.
 */
@Service
public class SearchFilterService {
    /**
     * Applies user preferences (filters/sorting) to the list of flight options.
     * @param results List of FlightOption
     * @param preferences Map of filter/sort preferences
     * @return Filtered and sorted list
     */
    public List<FlightOption> applyFilters(List<FlightOption> results, Map<String, Object> preferences) {
        if (preferences == null || results == null) return results;
        List<FlightOption> filtered = results;
        // Filter by stops
        if (preferences.containsKey("stops")) {
            Integer stops = (Integer) preferences.get("stops");
            filtered = filtered.stream()
                    .filter(f -> Objects.equals(f.getStops(), stops))
                    .collect(Collectors.toList());
        }
        // Filter by airline
        if (preferences.containsKey("airline")) {
            String airline = preferences.get("airline").toString();
            filtered = filtered.stream()
                    .filter(f -> airline.equalsIgnoreCase(f.getAirline()))
                    .collect(Collectors.toList());
        }
        // Sorting
        if (preferences.containsKey("sortBy")) {
            String sortBy = preferences.get("sortBy").toString();
            switch (sortBy) {
                case "price":
                    filtered = filtered.stream().sorted(Comparator.comparing(FlightOption::getPrice)).collect(Collectors.toList());
                    break;
                case "duration":
                    filtered = filtered.stream().sorted(Comparator.comparing(f -> java.time.Duration.between(f.getDeparture(), f.getArrival()))).collect(Collectors.toList());
                    break;
                case "airline":
                    filtered = filtered.stream().sorted(Comparator.comparing(FlightOption::getAirline)).collect(Collectors.toList());
                    break;
                case "stops":
                    filtered = filtered.stream().sorted(Comparator.comparing(FlightOption::getStops)).collect(Collectors.toList());
                    break;
                default:
                    break;
            }
        }
        return filtered;
    }
}
