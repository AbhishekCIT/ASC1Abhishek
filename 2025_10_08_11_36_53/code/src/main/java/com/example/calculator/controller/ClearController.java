package com.example.calculator.controller;

import com.example.calculator.service.ClearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Clear/Reset functionality
 */
@RestController
@RequestMapping("/api")
public class ClearController {

    @Autowired
    private ClearService clearService;

    /**
     * Exposes the /clear endpoint to log usage of clear/reset
     * @return Success message
     */
    @PostMapping("/clear")
    public ResponseEntity<?> clear() {
        clearService.logClearUsage();
        return ResponseEntity.ok("Calculator cleared successfully.");
    }
}
