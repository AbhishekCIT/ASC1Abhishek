package com.airtransport.dto;

import com.airtransport.model.FlightOption;
import java.util.List;

/**
 * DTO for search response payload.
 */
public class SearchResponse {
    private List<FlightOption> results;

    public List<FlightOption> getResults() {
        return results;
    }
    public void setResults(List<FlightOption> results) {
        this.results = results;
    }
}
