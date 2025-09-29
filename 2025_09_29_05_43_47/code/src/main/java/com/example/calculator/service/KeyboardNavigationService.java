package com.example.calculator.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service to enable keyboard navigation for UI accessibility
 */
@Service
public class KeyboardNavigationService {
    private static final Logger logger = LoggerFactory.getLogger(KeyboardNavigationService.class);

    /**
     * Enables keyboard navigation
     */
    public void enableNavigation() {
        // Simulate enabling keyboard navigation
        logger.info("Keyboard navigation enabled for UI elements");
    }
}
