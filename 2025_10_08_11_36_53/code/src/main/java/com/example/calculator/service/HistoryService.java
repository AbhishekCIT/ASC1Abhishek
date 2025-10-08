package com.example.calculator.service;

import com.example.calculator.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for History feature logging
 */
@Service
public class HistoryService {
    @Autowired
    private Logger logger;

    /**
     * Logs usage of history feature
     */
    public void logHistoryUsage() {
        logger.logUsage("history");
    }
}
