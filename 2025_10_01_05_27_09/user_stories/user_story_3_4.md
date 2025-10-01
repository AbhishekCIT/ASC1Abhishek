EPIC Number: 3
User Story Number: 4
User Story Title: As a traveler, I want to receive notifications about my flight status, So that I am informed of any changes or updates in real time

User Story Description: The application should send notifications to users regarding flight status changes, delays, gate changes, and cancellations. Notifications should be sent via email, SMS, and in-app alerts. Users should be able to manage notification preferences in their profile.

Acceptance Criteria:
1. Users receive notifications for flight status changes, delays, gate changes, and cancellations.
2. Notifications are sent via email, SMS, and in-app alerts.
3. Users can manage notification preferences in their profile.

Validations:
1. Notification preferences must be saved and applied correctly.
2. Email and phone number formats must be validated.
3. Notifications must be sent only for relevant flights.

Business Logic:
- Monitor flight status via airline API.
- Trigger notifications based on status changes.
- Respect user notification preferences.

Technical Context:
- Technology stack: ReactJS frontend, NodeJS backend, integration with airline status API, email/SMS gateway.
- Data format: JSON for API responses, SMTP/REST for notifications.
- Security: Secure handling of user contact data, opt-in/opt-out management.

Non-Functional Requirements:
- Notifications must be delivered within 1 minute of status change.
- System should be available 99.9% uptime.
- Scalable to handle thousands of notifications per minute.
- Monitoring and analytics for notification delivery success/failure.
