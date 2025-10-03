package com.airtransport.dto;

import java.time.LocalDate;

/**
 * DTO for passenger details in booking request.
 */
public class PassengerDTO {
    private String name;
    private LocalDate dob;
    private String email;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
