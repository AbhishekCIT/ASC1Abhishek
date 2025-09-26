package com.example.flightsearch.util;

import com.example.flightsearch.model.FlightSearchRequest;
import com.example.flightsearch.model.FlightSearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Utility for logging search requests and responses.
 */
@Component
public class LoggingUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    /**
     * Log the search request and response.
     * @param request Search request
     * @param response Search response
     */
    public void logSearch(FlightSearchRequest request, List<FlightSearchResponse> response) {
        logger.info("Flight search request: {}", request);
        logger.info("Flight search response: {} results", response != null ? response.size() : 0);
    }
}
