EPIC Number: 3
User Story Number: 5
User Story Title: As a passenger, I want to receive real-time flight status notifications, so that I am informed about delays, gate changes, or cancellations.

User Story Description: This feature enables users to opt-in for real-time notifications regarding their booked flights. Notifications can be sent via email, SMS, or in-app alerts, ensuring passengers are promptly informed of any changes to their flight status.

Acceptance Criteria:
1. Users can subscribe to notifications for their booked flights.
2. The system sends notifications for delays, gate changes, or cancellations.
3. Users can choose their preferred notification channel (email, SMS, app).

Validations:
1. Only valid contact information is accepted for notifications.
2. Notifications are sent within 1 minute of status change.
3. Users can unsubscribe from notifications at any time.

Business Logic: The system integrates with airline APIs to monitor flight status in real-time. When a status change is detected, notifications are triggered and sent to all subscribed users for that flight.

Technical Context: Frontend in React, backend in Node.js/Express. Integration with airline status APIs. Notification service integration (e.g., Twilio for SMS, SendGrid for email). Data in JSON format. Secure handling of user contact data.

Non-Functional Requirements: Notifications delivered within 1 minute of event. System available 99.9%. Compliance with GDPR for user data. Scalable to handle notifications for thousands of flights. Analytics on notification delivery and user engagement. Monitoring for failed notifications.