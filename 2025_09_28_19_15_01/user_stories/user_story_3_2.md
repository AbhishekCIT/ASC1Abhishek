EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to check the real-time status of my flight, So that I can stay informed about delays or gate changes.

User Story Description: This feature allows passengers to track their flight’s real-time status, including departure/arrival times, delays, gate assignments, and cancellations. The system should provide timely notifications and updates to keep passengers informed.

Acceptance Criteria:
1. Users can search for flight status by flight number or booking reference.
2. Real-time updates are displayed for departure, arrival, gate, and delays.
3. Users can opt-in for push/email notifications about status changes.

Validations:
1. Flight number or booking reference must be valid.
2. Notifications must be sent only to opted-in users.
3. Data must be refreshed every 2 minutes.

Business Logic:
- Integrate with airline and airport data feeds for live status updates.
- Notification logic triggers alerts for significant changes.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure Functions for scheduled updates.
- Integration with airline/airport APIs (REST/JSON).
- Secure endpoints, GDPR compliance for notifications.

Non-Functional Requirements:
- System should support 5,000 concurrent users.
- Notification delivery within 30 seconds of status change.
- 99.9% uptime.
- Monitoring and alerting for data feed failures.