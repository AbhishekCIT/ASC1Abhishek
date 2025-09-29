package com.airtransport.util;

import com.airtransport.model.FlightSearchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Service for logging search queries and application events.
 */
@Component
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    /**
     * Logs the flight search query for analytics and audit.
     * @param request Flight search request
     */
    public void logSearchQuery(FlightSearchRequest request) {
        logger.info("Flight search requested: userId={}, source={}, destination={}, date={}, passengerCount={}",
                request.getUserId(), request.getSource(), request.getDestination(), request.getDate(), request.getPassengerCount());
    }

    /**
     * Logs errors at ERROR level.
     * @param message Error message
     * @param ex Exception
     */
    public void logError(String message, Exception ex) {
        logger.error(message, ex);
    }
}
