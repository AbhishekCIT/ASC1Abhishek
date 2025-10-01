EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to check in online for my flight, So that I can save time at the airport and choose my preferred seat

User Story Description: The application should allow users to check in online for their booked flight by entering booking reference and personal details. Users should be able to select available seats, receive a digital boarding pass, and get check-in confirmation.

Acceptance Criteria:
1. Users can enter booking reference and personal details to initiate check-in.
2. Users can select available seats during check-in.
3. Digital boarding pass is generated and sent to user via email/SMS.
4. Check-in confirmation is displayed and stored in user account.

Validations:
1. Booking reference must be valid and match a confirmed booking.
2. Check-in must be attempted within allowed time window (e.g., 24 hours before departure).
3. Seat selection must not exceed available seats.

Business Logic:
- Validate booking reference and passenger details.
- Update seat map and assign selected seat.
- Generate and send digital boarding pass.

Technical Context:
- Technology stack: ReactJS frontend, NodeJS backend, integration with airline check-in API.
- Data format: JSON for API requests/responses.
- Security: Secure transmission of personal and travel data, authentication required.

Non-Functional Requirements:
- Check-in process should complete within 3 seconds.
- System should be available 99.9% uptime.
- Secure handling of personal and travel data.
- Scalable to handle peak check-in periods.
- Monitoring for failed check-ins and error analytics.
