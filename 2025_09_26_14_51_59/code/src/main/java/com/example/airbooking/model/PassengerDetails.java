package com.example.airbooking.model;

/**
 * Model for passenger details in booking requests.
 */
public class PassengerDetails {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    // Add more fields as needed

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isValid() {
        return userId != null && name != null && !name.isEmpty() && email != null && !email.isEmpty();
    }
}
