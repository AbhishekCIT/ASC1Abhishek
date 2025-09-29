EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to check in for my flight online, So that I can save time at the airport and select my preferred seat.

User Story Description: This feature enables passengers to check in for their flights via the web or mobile app. Users can select or change seats, enter required travel information, and receive a digital boarding pass. The system should support check-in within the allowed time window before departure.

Acceptance Criteria:
1. Passengers can access online check-in within 24 hours of departure.
2. Users can select or change available seats.
3. Boarding passes are generated and sent via email or available for download.
4. System validates travel documents if required.

Validations:
1. Check-in is only allowed within the airline’s specified window (e.g., 24-2 hours before departure).
2. Seat selection is restricted to available seats only.
3. Required passenger information must be complete and valid.

Business Logic: 
- Validate booking reference and passenger identity.
- Display available seats and update seat map in real-time.
- Generate digital boarding pass with QR code.
- Update passenger status to "checked-in" in the system.

Technical Context:
- Technology stack: ReactJS frontend, Node.js backend, PostgreSQL database.
- Integration with airline systems for seat maps and check-in status.
- RESTful APIs for check-in operations.
- Data formats: JSON for API communication, PDF for boarding passes.
- Security: HTTPS, user authentication, data privacy compliance.

Non-Functional Requirements:
- Check-in process must complete within 3 seconds.
- System must support 500+ concurrent check-ins.
- 99.9% uptime.
- Digital boarding pass must be scannable at airport gates.
- Monitoring and alerting for failed check-ins.