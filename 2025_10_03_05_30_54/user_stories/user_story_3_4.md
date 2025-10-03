EPIC Number: 3
User Story Number: 4
User Story Title: As a traveler, I want to receive notifications about my flight status, so that I stay informed about any changes or updates to my journey.

User Story Description: This user story covers the ability for the system to send timely notifications to users regarding their flight status, including reminders, gate changes, delays, or cancellations. Notifications should be delivered via email, SMS, or in-app alerts based on user preferences.

Acceptance Criteria:
1. Users can opt-in for notifications via email, SMS, or app.
2. System sends reminders for upcoming flights (e.g., 24 hours, 2 hours before departure).
3. Users receive real-time alerts for gate changes, delays, or cancellations.
4. Notification history is available in the user’s account.
5. Users can manage notification preferences.

Validations:
1. Valid contact information required for notifications.
2. Notifications sent only for active bookings.
3. Duplicate notifications are prevented.

Business Logic:
- Monitor flight status via airline API/webhooks.
- Trigger notifications based on status changes or scheduled reminders.
- Respect user notification preferences.
- Pseudocode:
  FOR each active booking
    IF flight status changes OR reminder due THEN
      IF user opted in THEN
        SEND notification (email/SMS/app)

Technical Context:
- Technology stack: Node.js backend, Azure Functions, Azure Notification Hubs
- Integration with airline APIs for real-time status
- Email/SMS gateway integration (SendGrid, Twilio)
- Data format: JSON
- Security: OAuth2, HTTPS, opt-in consent

Non-Functional Requirements:
- Notifications delivered within 1 minute of event
- 99.9% uptime for notification service
- Data encrypted in transit
- Scalable to handle 100,000 notifications/day
- Monitoring for delivery failures and retries