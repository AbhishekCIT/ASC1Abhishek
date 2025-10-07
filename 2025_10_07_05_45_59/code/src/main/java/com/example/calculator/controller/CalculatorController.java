package com.example.calculator.controller;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.entity.CalculationLog;
import com.example.calculator.repository.CalculationLogRepository;
import com.example.calculator.service.ArithmeticService;
import com.example.calculator.service.ErrorHandlingService;
import com.example.calculator.service.InputValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * REST controller for calculation requests.
 */
@RestController
@RequestMapping("/api/v1")
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private ArithmeticService arithmeticService;
    @Autowired
    private InputValidationService inputValidationService;
    @Autowired
    private ErrorHandlingService errorHandlingService;
    @Autowired
    private CalculationLogRepository calculationLogRepository;

    /**
     * Endpoint to perform a calculation operation.
     * @param request CalculationRequest containing number1, number2, and operation
     * @return CalculationResponse with result or error
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        logger.info("Received calculation request: {}", request);
        CalculationResponse response = new CalculationResponse();
        CalculationLog log = new CalculationLog();
        log.setNumber1(request.getNumber1());
        log.setNumber2(request.getNumber2());
        log.setOperation(request.getOperation());
        log.setTimestamp(LocalDateTime.now());

        // Validate input
        if (!inputValidationService.validateNumbers(request.getNumber1(), request.getNumber2())) {
            String error = errorHandlingService.handleInvalidInput();
            logger.warn("Invalid input: {}", request);
            response.setError(error);
            log.setError(error);
            calculationLogRepository.save(log);
            return ResponseEntity.badRequest().body(response);
        }

        double result = 0.0;
        try {
            switch (request.getOperation().toLowerCase()) {
                case "add":
                    result = arithmeticService.add(request.getNumber1(), request.getNumber2());
                    response.setResult(result);
                    log.setResult(result);
                    break;
                case "subtract":
                    result = arithmeticService.subtract(request.getNumber1(), request.getNumber2());
                    response.setResult(result);
                    log.setResult(result);
                    break;
                case "multiply":
                    result = arithmeticService.multiply(request.getNumber1(), request.getNumber2());
                    response.setResult(result);
                    log.setResult(result);
                    break;
                case "divide":
                    try {
                        result = arithmeticService.divide(request.getNumber1(), request.getNumber2());
                        response.setResult(result);
                        log.setResult(result);
                    } catch (ArithmeticException ex) {
                        String error = errorHandlingService.handleDivisionByZero();
                        logger.warn("Division by zero: {}", request);
                        response.setError(error);
                        log.setError(error);
                        calculationLogRepository.save(log);
                        return ResponseEntity.badRequest().body(response);
                    }
                    break;
                default:
                    String error = errorHandlingService.handleOperationNotSupported();
                    logger.warn("Operation not supported: {}", request);
                    response.setError(error);
                    log.setError(error);
                    calculationLogRepository.save(log);
                    return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception ex) {
            logger.error("Unexpected error during calculation", ex);
            response.setError("Unexpected error occurred.");
            log.setError("Unexpected error occurred.");
            calculationLogRepository.save(log);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        calculationLogRepository.save(log);
        logger.info("Calculation result: {}", response);
        return ResponseEntity.ok(response);
    }
}
