EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track the status of my flight, So that I can stay informed about delays or gate changes.

User Story Description: This user story enables travelers to view real-time status updates for their booked flights, including departure/arrival times, delays, gate assignments, and cancellations. The feature should provide timely notifications and easy access to flight status information.

Acceptance Criteria:
1. Users can enter their flight number or booking reference to view status.
2. Users receive real-time updates for delays, cancellations, or gate changes.
3. Users can opt-in for push/email/SMS notifications.
4. Status information is accurate and refreshed at least every 2 minutes.

Validations:
1. Validate flight number or booking reference before displaying status.
2. Ensure notifications are sent only to opted-in users.
3. Prevent outdated information from being displayed.

Business Logic: 
- Status data is fetched from airline or airport APIs.
- Notification logic checks for changes and triggers alerts to users.
- Data refresh logic ensures status is updated every 2 minutes.

Technical Context: 
- Technology stack: ReactJS frontend, Node.js backend, PostgreSQL database.
- APIs: Integration with airline/airport APIs for status updates.
- Data formats: JSON for API communication.
- Security: HTTPS, user authentication for accessing booking details.

Non-Functional Requirements:
- Status updates should be delivered within 1 minute of change.
- System must support 5,000 concurrent users tracking flights.
- 99.9% uptime for status tracking service.
- Compliance with GDPR for user data and notifications.
- Monitoring for notification delivery success rates.
