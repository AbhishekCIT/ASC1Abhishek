EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to check in online, so that I can save time at the airport and select my preferred seat.

User Story Description: This feature enables passengers to check in online before their flight, select or change seats, and receive a digital boarding pass. The system should validate eligibility for online check-in and update the check-in status in the system.

Acceptance Criteria:
1. Passengers can access online check-in within the allowed time window.
2. System verifies booking reference and eligibility.
3. Passengers can select or change seats during check-in.
4. Digital boarding pass is generated and sent to the passenger.

Validations:
1. Check-in is only available within 24-2 hours before departure.
2. Booking reference must be valid and match passenger details.
3. Seat selection must reflect real-time availability.

Business Logic:
- Validate check-in window and booking status.
- Update seat assignment and check-in status in the database.
- Generate and deliver digital boarding pass (PDF/QR code).

Technical Context:
- Technology stack: React (frontend), Node.js/Express (backend), PostgreSQL (database)
- Integration with airline seat map APIs
- RESTful APIs for check-in and seat selection
- Data format: JSON, PDF
- Security: Authentication, HTTPS, data privacy compliance

Non-Functional Requirements:
- Online check-in must be available 24/7 except during scheduled maintenance.
- Boarding pass generation should take less than 2 seconds.
- System must support at least 5,000 concurrent check-ins.
- All check-in and seat selection events should be logged for audit.
- 99.9% uptime for check-in functionality.