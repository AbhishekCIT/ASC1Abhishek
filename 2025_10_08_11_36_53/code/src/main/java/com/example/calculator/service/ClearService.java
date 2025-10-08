package com.example.calculator.service;

import com.example.calculator.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for Clear/Reset functionality
 */
@Service
public class ClearService {
    @Autowired
    private Logger logger;

    /**
     * Logs usage of clear/reset feature
     */
    public void logClearUsage() {
        logger.logUsage("clear");
    }
}
