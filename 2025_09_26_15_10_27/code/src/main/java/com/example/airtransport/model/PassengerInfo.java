package com.example.airtransport.model;

/**
 * Model representing passenger information.
 */
public class PassengerInfo {
    private String name;
    private String email;
    private String phone;
    // Add more fields as needed (passport, etc.)

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
