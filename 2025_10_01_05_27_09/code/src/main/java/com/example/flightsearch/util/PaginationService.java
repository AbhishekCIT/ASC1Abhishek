package com.example.flightsearch.util;

import com.example.flightsearch.entity.Flight;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;

/**
 * Utility service for paginating flight results.
 */
@Component
public class PaginationService {

    /**
     * Returns a paginated sublist of flights.
     * @param flights Full list of flights
     * @param page Page number (1-based)
     * @param size Page size
     * @return Paginated list of flights
     */
    public List<Flight> paginateResults(List<Flight> flights, int page, int size) {
        if (flights == null || flights.isEmpty()) return Collections.emptyList();
        int fromIndex = Math.max((page - 1) * size, 0);
        int toIndex = Math.min(fromIndex + size, flights.size());
        if (fromIndex >= flights.size()) return Collections.emptyList();
        return flights.subList(fromIndex, toIndex);
    }

    /**
     * Calculates total number of pages for the given list size and page size.
     * @param totalItems Total number of items
     * @param pageSize Page size
     * @return Total pages
     */
    public int getTotalPages(int totalItems, int pageSize) {
        if (pageSize <= 0) return 1;
        return (int) Math.ceil((double) totalItems / pageSize);
    }
}
