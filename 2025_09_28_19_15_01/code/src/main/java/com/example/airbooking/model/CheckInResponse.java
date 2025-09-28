package com.example.airbooking.model;

/**
 * Response model for online check-in.
 */
public class CheckInResponse {
    private String checkInId;
    private String status;
    private String boardingPass;

    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getBoardingPass() { return boardingPass; }
    public void setBoardingPass(String boardingPass) { this.boardingPass = boardingPass; }
}
