EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to check the real-time status of my flight, So that I can stay informed about delays, gate changes, or cancellations.

User Story Description: This user story enables users to view the current status of their booked flights, including departure/arrival times, gate information, and notifications about delays or cancellations. The system should provide timely updates and allow users to subscribe to alerts.

Acceptance Criteria:
1. Users can search for their flight by booking reference or flight number.
2. Real-time status is displayed, including gate, terminal, and estimated times.
3. Users can opt-in to receive SMS/email notifications for status changes.

Validations:
1. Flight number or booking reference must be valid.
2. Notifications are sent only to opted-in users.
3. Status updates must be accurate and timely.

Business Logic:
- Integrate with airline or airport real-time data feeds (API polling or webhooks).
- Match user bookings to flight status data.
- Trigger notifications upon status changes.

Technical Context:
- Real-time data integration using RESTful APIs or WebSockets.
- Notification service for SMS/email (using providers like Twilio or SendGrid).
- Secure user data handling and preferences management.

Non-Functional Requirements:
- Status updates must reflect within 2 minutes of change.
- Notification delivery success rate must be 99% or higher.
- System must scale to handle peak travel periods.
- All user preferences and data must be encrypted.
