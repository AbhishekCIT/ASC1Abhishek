package com.example.airtransport.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response model for flight search API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightSearchResponse {
    private List<FlightDTO> flights;
}
