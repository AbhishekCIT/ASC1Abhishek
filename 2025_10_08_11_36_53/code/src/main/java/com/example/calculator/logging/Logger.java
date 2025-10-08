package com.example.calculator.logging;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger as Slf4jLogger;

/**
 * Logger component for usage and error logging
 */
@Component
public class Logger {
    private static final Slf4jLogger logger = LoggerFactory.getLogger(Logger.class);

    /**
     * Logs error messages
     * @param message error message
     */
    public void logError(String message) {
        logger.error(message);
    }

    /**
     * Logs usage of operations
     * @param operation operation name
     */
    public void logUsage(String operation) {
        logger.info("Operation used: {}", operation);
    }
}
