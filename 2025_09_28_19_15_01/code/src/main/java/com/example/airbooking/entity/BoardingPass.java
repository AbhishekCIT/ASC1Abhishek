package com.example.airbooking.entity;

import javax.persistence.*;

/**
 * Entity representing a boarding pass.
 */
@Entity
@Table(name = "boardingpass")
public class BoardingPass {
    @Id
    private String boardingPassId;
    private String checkInId;
    private String pdfUrl;

    public String getBoardingPassId() { return boardingPassId; }
    public void setBoardingPassId(String boardingPassId) { this.boardingPassId = boardingPassId; }
    public String getCheckInId() { return checkInId; }
    public void setCheckInId(String checkInId) { this.checkInId = checkInId; }
    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
}
