package com.example.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for flight details in search response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private String flightId;
    private double price;
    private String departure;
    private String arrival;
}
