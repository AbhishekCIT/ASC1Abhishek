EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to receive real-time flight status updates and notifications, So that I am informed about delays, cancellations, or gate changes.

User Story Description: The application should provide travelers with up-to-date information about their flights, including departure/arrival times, delays, cancellations, and gate changes. Notifications should be sent via email, SMS, or in-app alerts based on user preference.

Acceptance Criteria:
1. Users receive notifications for any changes to their flight status.
2. Flight status is updated in real-time from airline data feeds.
3. Users can select notification preferences (email, SMS, app).

Validations:
1. Only subscribed users receive notifications.
2. Notification delivery is logged and confirmed.
3. Flight status data is refreshed every 5 minutes.

Business Logic:
- Poll airline APIs for flight status updates.
- Compare current status with previous to detect changes.
- Trigger notifications based on user preferences.
- Log notification delivery and handle failures.

Technical Context:
- Technology stack: .NET Core backend, React frontend.
- Airline status APIs (REST/JSON), SMS/email gateway integration.
- Secure HTTPS for all communications.
- Data stored in Azure SQL Database.

Non-Functional Requirements:
- Notification delivery within 1 minute of status change.
- 99.9% uptime for notification service.
- Data privacy for user contact information.
- Scalable to support global flight data feeds.
- Monitoring for missed or failed notifications.
