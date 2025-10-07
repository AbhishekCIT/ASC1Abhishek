package com.example.calculator.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class for logging each calculation operation.
 */
@Entity
@Table(name = "CALCULATION_LOG")
public class CalculationLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double number1;

    @Column(nullable = false)
    private double number2;

    @Column(nullable = false)
    private String operation;

    private Double result;
    private String error;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CalculationLog() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
