package com.example.calculator.service;

import com.example.calculator.dto.CalculationHistoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Service to manage calculation history in memory (max 10 items).
 */
@Service
public class CalculationHistoryService {
    private static final Logger logger = LoggerFactory.getLogger(CalculationHistoryService.class);
    private static final int MAX_HISTORY = 10;
    private final LinkedList<CalculationHistoryItem> history = new LinkedList<>();

    /**
     * Adds a calculation to the history, removing oldest if limit exceeded.
     */
    public synchronized void add(CalculationHistoryItem item) {
        // Prevent duplicate entries
        if (!history.isEmpty() && history.getLast().equals(item)) {
            return;
        }
        if (history.size() >= MAX_HISTORY) {
            history.removeFirst();
        }
        history.add(item);
        logger.info("Calculation added to history: {}", item);
    }

    /**
     * Returns all history items (most recent last).
     */
    public synchronized List<CalculationHistoryItem> getAll() {
        return new LinkedList<>(history);
    }

    /**
     * Clears the calculation history.
     */
    public synchronized void clear() {
        history.clear();
        logger.info("Calculation history cleared.");
    }
}
