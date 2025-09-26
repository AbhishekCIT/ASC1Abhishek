package com.airtransport.model;

import java.util.List;

/**
 * Model representing the validation response payload.
 */
public class ValidationResponse {
    private boolean valid;
    private List<String> errors;

    public ValidationResponse() {}

    public ValidationResponse(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }
}
