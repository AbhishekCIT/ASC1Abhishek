package com.example.airtransport.repository;

import com.example.airtransport.model.FlightSearchCriteria;
import com.example.airtransport.model.Flight;
import java.util.List;

/**
 * Repository for accessing flight inventory.
 */
public interface FlightRepository {
    /**
     * Finds flights matching the given criteria.
     * @param criteria Search criteria
     * @return List of flights
     */
    List<Flight> findFlights(FlightSearchCriteria criteria);
}
