package com.airtransport.util;

import com.airtransport.model.FlightSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utility service for logging search queries and analytics.
 */
@Component
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    /**
     * Log search query for analytics and audit.
     * @param request Flight search request
     */
    public void logSearchQuery(FlightSearchRequest request) {
        // Log at INFO level
        logger.info("Search Query: Origin={}, Destination={}, DepartureDate={}, ReturnDate={}, Filters={}",
                request.getOrigin(), request.getDestination(), request.getDepartureDate(), request.getReturnDate(), request.getFilters());
    }
}
