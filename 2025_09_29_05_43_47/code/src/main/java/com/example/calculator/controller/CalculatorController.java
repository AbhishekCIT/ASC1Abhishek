package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.service.CalculatorService;
import com.example.calculator.service.CalculatorStateService;
import com.example.calculator.service.KeyboardListener;
import com.example.calculator.service.AccessibilityService;
import com.example.calculator.service.LayoutService;
import com.example.calculator.service.KeyboardNavigationService;
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
    private KeyboardListener keyboardListener;

    @Autowired
    private AccessibilityService accessibilityService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private KeyboardNavigationService keyboardNavigationService;

    @Autowired
    private ErrorHandler errorHandler;

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

    @PostMapping("/keyboard")
    public ResponseEntity<Map<String, Object>> processKeyboardInput(@RequestBody Map<String, String> payload) {
        logger.debug("Received keyboard input: {}", payload);
        Map<String, Object> response = new HashMap<>();
        try {
            String key = payload.get("key");
            String status = keyboardListener.onKeyPress(key);
            response.put("status", status);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Keyboard input error: {}", e.getMessage());
            response.put("status", "ignored");
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    /**
     * Exposes GET /api/ui to simulate UI render event
     */
    @GetMapping("/ui")
    public ResponseEntity<Map<String, Object>> renderUI() {
        logger.info("UI render requested");
        Map<String, Object> response = new HashMap<>();
        try {
            layoutService.applyResponsiveLayout();
            accessibilityService.applyAccessibility();
            keyboardNavigationService.enableNavigation();
            response.put("status", "rendered");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("UI render error: {}", e.getMessage());
            response.put("status", "failed");
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }

    /**
     * Exposes GET /api/accessibility to check accessibility compliance
     */
    @GetMapping("/accessibility")
    public ResponseEntity<Map<String, Object>> accessibilityCheck() {
        logger.info("Accessibility check requested");
        Map<String, Object> response = new HashMap<>();
        try {
            boolean passed = accessibilityService.checkCompliance();
            response.put("status", passed ? "passed" : "failed");
            if (!passed) {
                response.put("errors", accessibilityService.getAccessibilityErrors());
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Accessibility check error: {}", e.getMessage());
            response.put("status", "failed");
            response.put("error", e.getMessage());
            return ResponseEntity.ok(response);
        }
    }
}
