package com.example.calculator.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing calculator state reset events
 */
@Entity
@Table(name = "calculation_state")
public class CalculationState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String inputs;

    @Column
    private String result;

    @Column
    private String errors;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public CalculationState() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
