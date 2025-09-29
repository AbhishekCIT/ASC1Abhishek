package com.example.calculator.service;

import com.example.calculator.model.CalculationRequest;
import com.example.calculator.model.CalculationResponse;
import com.example.calculator.util.InputValidatorUtil;
import com.example.calculator.entity.Calculation;
import com.example.calculator.repository.CalculationRepository;
import com.example.calculator.exception.InvalidInputException;
import com.example.calculator.exception.DivisionByZeroException;
import com.example.calculator.exception.OperationNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

/**
 * Service to perform arithmetic operations and validations
 */
@Service
public class CalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    @Autowired
    private CalculationRepository calculationRepository;

    /**
     * Main method to handle calculation logic
     * @param request CalculationRequest
     * @return CalculationResponse
     */
    public CalculationResponse calculate(CalculationRequest request) {
        // Input validation
        InputValidatorUtil.validateNumeric(request.getNum1(), "num1");
        InputValidatorUtil.validateNumeric(request.getNum2(), "num2");

        BigDecimal num1 = new BigDecimal(request.getNum1());
        BigDecimal num2 = new BigDecimal(request.getNum2());
        String operation = request.getOperation();

        BigDecimal result = null;
        String error = null;

        try {
            switch (operation.toLowerCase()) {
                case "add":
                    result = add(num1, num2);
                    break;
                case "subtract":
                    result = subtract(num1, num2);
                    break;
                case "multiply":
                    result = multiply(num1, num2);
                    break;
                case "divide":
                    result = divide(num1, num2);
                    break;
                default:
                    throw new OperationNotSupportedException("Invalid operation requested");
            }
        } catch (DivisionByZeroException | InvalidInputException | OperationNotSupportedException ex) {
            error = ex.getMessage();
            logger.warn("Calculation error: {}", error);
        } catch (Exception ex) {
            error = "Internal server error";
            logger.error("Unexpected error: {}", ex.getMessage());
        }

        // Round result to two decimal places if not null
        if (result != null) {
            result = result.setScale(2, RoundingMode.HALF_UP);
        }

        // Persist calculation event
        Calculation calculation = new Calculation();
        calculation.setNum1(num1);
        calculation.setNum2(num2);
        calculation.setOperation(operation);
        calculation.setResult(result);
        calculation.setError(error);
        calculation.setTimestamp(LocalDateTime.now());
        calculationRepository.save(calculation);

        return new CalculationResponse(result, error);
    }

    /**
     * Addition operation
     */
    public BigDecimal add(BigDecimal num1, BigDecimal num2) {
        return num1.add(num2);
    }

    /**
     * Subtraction operation
     */
    public BigDecimal subtract(BigDecimal num1, BigDecimal num2) {
        return num1.subtract(num2);
    }

    /**
     * Multiplication operation
     */
    public BigDecimal multiply(BigDecimal num1, BigDecimal num2) {
        return num1.multiply(num2);
    }

    /**
     * Division operation
     */
    public BigDecimal divide(BigDecimal num1, BigDecimal num2) {
        if (num2.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException("Division by zero is not allowed.");
        }
        return num1.divide(num2, 10, RoundingMode.HALF_UP); // intermediate scale for accuracy
    }
}
