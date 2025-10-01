package com.example.flightsearch.service;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import com.example.flightsearch.entity.Flight;
import com.example.flightsearch.repository.FlightRepository;
import com.example.flightsearch.util.FilterService;
import com.example.flightsearch.util.PaginationService;
import com.example.flightsearch.util.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for flight search business logic.
 */
@Service
public class FlightSearchService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FilterService filterService;

    @Autowired
    private PaginationService paginationService;

    @Autowired
    private ValidationService validationService;

    /**
     * Finds flights based on the search request.
     * @param request Flight search parameters
     * @return Paginated and filtered flight search response
     */
    public FlightSearchResponse findFlights(FlightSearchRequest request) {
        // Validate search parameters
        validationService.validateSearchParams(request);

        // Query all flights matching origin, destination, and date
        List<Flight> flights = flightRepository.findByOriginAndDestinationAndDate(
                request.getOrigin(),
                request.getDestination(),
                request.getDate()
        );

        // Apply filters (price, airline, duration)
        flights = filterService.applyFilters(flights, request.getFilters());

        // Paginate results
        List<Flight> paginatedFlights = paginationService.paginateResults(flights, request.getPage(), request.getSize());

        // Build response
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(paginatedFlights.stream().map(Flight::toFlightDTO).collect(Collectors.toList()));
        response.setTotalPages(paginationService.getTotalPages(flights.size(), request.getSize()));
        response.setCurrentPage(request.getPage());
        return response;
    }
}
