EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to receive real-time notifications about my flight status, So that I can stay informed about delays, gate changes, or cancellations.

User Story Description: This user story enables the application to send timely notifications to travelers regarding any updates to their booked flights, such as delays, gate changes, or cancellations. Notifications should be sent via email, SMS, and in-app messages.

Acceptance Criteria:
1. Users receive notifications for any changes to their flight status.
2. Notifications are sent via email, SMS, and in-app alerts.
3. Users can configure their notification preferences.

Validations:
1. Notification content must be accurate and up-to-date.
2. Users must be able to opt in/out of specific notification channels.
3. Notifications should not be sent for unchanged flights.

Business Logic:
- Monitor airline APIs for real-time flight status updates.
- Trigger notification workflow upon detecting a change.
- Respect user notification preferences stored in the profile.
- Log all notifications sent for auditing.

Technical Context:
- Technology stack: Node.js backend, Azure Notification Hubs, Twilio (SMS), SendGrid (email).
- Integration with airline APIs for status updates.
- Data format: JSON for internal communication.
- Security: Secure storage of user contact information.

Non-Functional Requirements:
- Notifications should be delivered within 1 minute of status change.
- 99% delivery rate for notifications.
- High availability for notification service.
- Monitoring and alerting for failed notifications.