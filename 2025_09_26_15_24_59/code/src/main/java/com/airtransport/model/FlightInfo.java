package com.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model representing a flight's basic information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightInfo {
    private String flightId;
    private String airline;
    private String departure;
    private String arrival;
    private double price;
}
