package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.CalculatorStateService;
import com.example.calculator.exception.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller to handle calculator API requests
 */
@RestController
@RequestMapping("/api")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private CalculatorService calculatorService;

    @Autowired
    private CalculatorStateService calculatorStateService;

    @Autowired
    private ErrorHandler errorHandler;

    /**
     * Exposes POST /api/calculate for basic arithmetic operations
     * @param request CalculationRequest containing num1, num2, and operation
     * @return CalculationResponse with result or error
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        logger.info("Received calculation request: {}", request);
        try {
            CalculationResponse response = calculatorService.calculate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Calculation error: {}", e.getMessage());
            return ResponseEntity.ok(errorHandler.handleError(e));
        }
    }

    /**
     * Exposes POST /api/reset to clear calculator state
     * @return Map with status and error message (if any)
     */
    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> reset() {
        logger.info("Received reset request");
        Map<String, Object> response = new HashMap<>();
        try {
            calculatorStateService.resetState();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Reset error: {}", e.getMessage());
            response.put("status", "failure");
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
