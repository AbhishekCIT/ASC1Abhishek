package com.example.flightsearch.repository;

import com.example.flightsearch.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository for Flight entity.
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    /**
     * Find available flights matching the search criteria.
     * @param origin Origin airport code
     * @param destination Destination airport code
     * @param date Departure date
     * @param passengers Number of passengers
     * @param flightClass Flight class
     * @return List of available flights
     */
    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND FUNCTION('DATE', f.departureTime) = :date AND f.availableSeats >= :passengers AND f.flightClass = :flightClass")
    List<Flight> findAvailableFlights(@Param("origin") String origin,
                                      @Param("destination") String destination,
                                      @Param("date") LocalDate date,
                                      @Param("passengers") int passengers,
                                      @Param("flightClass") String flightClass);
}
