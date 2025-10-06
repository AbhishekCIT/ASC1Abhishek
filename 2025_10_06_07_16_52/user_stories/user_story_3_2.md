EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track my flight status in real-time, So that I can stay updated on any delays or changes.

User Story Description: This feature enables users to view the real-time status of their booked flights, including departure and arrival times, delays, gate changes, and cancellations. Notifications should be sent for any significant changes.

Acceptance Criteria:
1. Users can enter their flight number to see current status.
2. Users receive push/email notifications for delays or gate changes.
3. Flight status updates every 2 minutes.

Validations:
1. Flight number must be valid and exist in the system.
2. Notifications must be sent only to users with valid bookings.
3. Data must be accurate and timely.

Business Logic:
- Poll airline status APIs at regular intervals.
- Match flight numbers to user bookings.
- Trigger notifications on status change events.

Technical Context:
- Stack: React Native mobile app, Node.js backend, Firebase for notifications.
- Airline status API integration (REST/JSON).
- Secure user authentication (OAuth2).

Non-Functional Requirements:
- Notification delivery within 1 minute of status change.
- 99.5% notification reliability.
- Data privacy for user flight information.
- Mobile app battery usage optimized for polling.
