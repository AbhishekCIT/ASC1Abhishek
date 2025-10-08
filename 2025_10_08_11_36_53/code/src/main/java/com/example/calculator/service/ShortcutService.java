package com.example.calculator.service;

import com.example.calculator.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for Keyboard Shortcut feature logging
 */
@Service
public class ShortcutService {
    @Autowired
    private Logger logger;

    /**
     * Logs usage of keyboard shortcut feature
     */
    public void logShortcutUsage() {
        logger.logUsage("shortcut");
    }
}
