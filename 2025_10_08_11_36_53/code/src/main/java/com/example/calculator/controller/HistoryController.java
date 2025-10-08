package com.example.calculator.controller;

import com.example.calculator.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for History feature logging
 */
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    /**
     * Exposes the /log endpoint to log usage of history feature
     * @return Success message
     */
    @PostMapping("/log")
    public ResponseEntity<?> logHistoryUsage() {
        historyService.logHistoryUsage();
        return ResponseEntity.ok("History feature usage logged successfully.");
    }
}
