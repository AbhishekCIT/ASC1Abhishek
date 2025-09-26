EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to check in online for my flight, So that I can save time at the airport and select my preferred seat

User Story Description: This feature allows users to complete online check-in for their booked flights. Users can enter booking details, select or confirm seats, and receive a digital boarding pass. The system should validate eligibility for check-in and provide real-time updates on check-in status.

Acceptance Criteria:
1. Users can retrieve their booking using reference number and last name.
2. System displays eligible flights for online check-in.
3. Users can select or confirm seats and enter required travel information.
4. System generates and delivers a digital boarding pass (PDF/QR code).
5. Check-in status is updated in the booking system.

Validations:
1. Check-in is only allowed within the airline's check-in window (e.g., 24-2 hours before departure).
2. All required travel documents must be provided.
3. Seat selection must not conflict with airline restrictions (e.g., exit row eligibility).
4. Only confirmed bookings are eligible for check-in.

Business Logic:
- Validate check-in window based on flight departure time.
- Update seat map in real-time to reflect selections.
- Generate unique digital boarding pass with QR code/barcode.
- Update booking status to 'Checked-In' upon completion.

Technical Context:
- Technology stack: React.js (frontend), Node.js/Express (backend), PostgreSQL (database).
- Integration with airline DCS (Departure Control System) via REST/SOAP APIs.
- Data formats: JSON for API communication, PDF for boarding pass.
- Security: Secure boarding pass generation and delivery.

Non-Functional Requirements:
- Check-in process must complete within 3 seconds.
- System must be available 99.9% of the time.
- Boarding pass must be accessible on mobile and desktop.
- Scalable to handle 2,000 concurrent check-ins.
- Monitor check-in failures and boarding pass delivery issues.