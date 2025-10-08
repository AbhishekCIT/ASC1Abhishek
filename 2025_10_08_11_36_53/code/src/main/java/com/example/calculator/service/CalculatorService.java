package com.example.calculator.service;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.util.ArithmeticUtils;
import com.example.calculator.util.InputValidator;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.OperationNotSupportedException;
import com.example.calculator.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for Calculator business logic
 */
@Service
public class CalculatorService {

    @Autowired
    private ArithmeticUtils arithmeticUtils;

    @Autowired
    private InputValidator inputValidator;

    @Autowired
    private Logger logger;

    /**
     * Performs calculation based on the request
     * @param request CalculationRequest
     * @return CalculationResponse
     */
    public CalculationResponse calculate(CalculationRequest request) {
        CalculationResponse response = new CalculationResponse();
        try {
            // Validate inputs
            double num1 = inputValidator.validateNumber(request.getNum1(), "num1");
            double num2 = inputValidator.validateNumber(request.getNum2(), "num2");
            String operation = inputValidator.validateOperation(request.getOperation());

            double result;
            switch (operation) {
                case "add":
                    result = arithmeticUtils.add(num1, num2);
                    break;
                case "subtract":
                    result = arithmeticUtils.subtract(num1, num2);
                    break;
                case "multiply":
                    result = arithmeticUtils.multiply(num1, num2);
                    break;
                case "divide":
                    if (num2 == 0) {
                        logger.logError("Cannot divide by zero");
                        throw new DivisionByZeroException("Cannot divide by zero");
                    }
                    result = arithmeticUtils.divide(num1, num2);
                    break;
                default:
                    logger.logError("Invalid operation: " + operation);
                    throw new OperationNotSupportedException("Invalid operation");
            }
            response.setResult(result);
            logger.logUsage(operation);
        } catch (InvalidInputException | DivisionByZeroException | OperationNotSupportedException ex) {
            response.setError(ex.getMessage());
            logger.logError(ex.getMessage());
        }
        return response;
    }
}
