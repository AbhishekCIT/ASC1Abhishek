package com.example.calculator.dto;

import java.util.List;

/**
 * DTO for returning calculation history array.
 */
public class HistoryResponse {
    private List<CalculationHistoryItem> history;

    public HistoryResponse() {}

    public List<CalculationHistoryItem> getHistory() {
        return history;
    }

    public void setHistory(List<CalculationHistoryItem> history) {
        this.history = history;
    }
}
