EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to check real-time flight status and receive notifications, So that I stay informed about delays, gate changes, or cancellations.

User Story Description: This feature allows users to view the current status of their flights, including departure/arrival times, delays, and gate information. Users can opt-in for push/email/SMS notifications for any changes.

Acceptance Criteria:
1. Users can search for and view real-time status of any flight.
2. Users can subscribe to notifications for specific flights.
3. Users receive timely alerts for delays, cancellations, or gate changes.

Validations:
1. Flight number and date must be valid.
2. Notification preferences must be confirmed by the user.
3. Notifications are sent only for subscribed flights.

Business Logic: The system polls airline data feeds for status updates and triggers notifications to users based on their preferences. All notifications are logged for audit.

Technical Context: Integration with airline real-time data APIs. Notification service supports email, SMS, and push (using services like Twilio, Firebase). User preferences are stored securely.

Non-Functional Requirements: Status updates must be reflected within 2 minutes of airline data change. Notifications must be delivered within 30 seconds of an update. System must support high concurrency during peak travel times.