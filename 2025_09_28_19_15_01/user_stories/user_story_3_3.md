EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to check in online before my flight, So that I can save time at the airport and select my preferred seat.

User Story Description: This feature enables passengers to complete check-in online, select seats, download or print boarding passes, and add baggage if required. The system should support check-in for multiple airlines and handle special requests (e.g., meal preferences, assistance needs).

Acceptance Criteria:
1. Users can check in online within the allowed time window.
2. Users can select available seats and add baggage.
3. Boarding passes are generated and available for download/print.
4. Special requests can be added during check-in.

Validations:
1. Check-in is only allowed within the airline's specified window.
2. Seat selection must reflect real-time availability.
3. Boarding pass must include all required details (name, flight, seat, etc.).

Business Logic:
- Check-in window is determined by airline rules (e.g., 24-48 hours before departure).
- Seat map is updated in real time as seats are selected.
- Boarding pass is generated upon successful check-in and sent to the user.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure SQL Database.
- Integration with airline APIs for check-in and seat selection.
- Boarding pass generation in PDF format.
- Secure endpoints, GDPR compliance for personal data.

Non-Functional Requirements:
- System should support 2,000 concurrent check-ins.
- Boarding pass generation within 5 seconds.
- 99.9% uptime.
- Monitoring for failed check-in attempts.