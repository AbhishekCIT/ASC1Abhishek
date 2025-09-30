package com.airtransport.model;

/**
 * Data Transfer Object for Passenger.
 */
public class PassengerDTO {
    private String passengerId;
    private String name;
    private String email;

    // Getters and Setters
    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
