package com.example.airlinebooking.controller;

import com.example.airlinebooking.entity.Flight;
import com.example.airlinebooking.model.FlightSearchRequest;
import com.example.airlinebooking.model.FlightSearchResponse;
import com.example.airlinebooking.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for flight search APIs.
 */
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    /**
     * Searches for flights by origin, destination, and date.
     * @param request search request
     * @return list of available flights
     */
    @GetMapping("/search")
    public ResponseEntity<FlightSearchResponse> searchFlights(@Valid @RequestBody FlightSearchRequest request) {
        List<Flight> flights = flightService.searchFlights(request.getOrigin(), request.getDestination(), request.getDate());
        FlightSearchResponse response = new FlightSearchResponse();
        response.setFlights(flights.stream().map(f -> {
            FlightSearchResponse.FlightInfo fi = new FlightSearchResponse.FlightInfo();
            fi.setFlightId(f.getId());
            fi.setOrigin(f.getOrigin());
            fi.setDestination(f.getDestination());
            fi.setPrice(f.getPrice());
            fi.setSeatsAvailable(f.getSeatsAvailable());
            return fi;
        }).collect(Collectors.toList()));
        return ResponseEntity.ok(response);
    }
}
