package com.airtransport.booking.model;

import lombok.Data;

@Data
public class FlightSearchResponse {
    private String flightId;
    private String airline;
    private String departure;
    private String arrival;
    private Double price;
}
