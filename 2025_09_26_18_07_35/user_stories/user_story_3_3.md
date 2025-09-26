EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to receive real-time flight status updates and notifications, So that I can stay informed about delays, gate changes, and cancellations.

User Story Description: This feature provides users with up-to-date information on their flights, including departure and arrival times, gate assignments, delays, and cancellations. Notifications should be sent via email, SMS, or app push notifications based on user preferences.

Acceptance Criteria:
1. Users can subscribe to flight status notifications.
2. Users receive timely updates for any changes in flight status.
3. Users can view real-time flight status within the application.

Validations:
1. Validate user contact information for notifications.
2. Validate subscription preferences.
3. Validate data received from airline APIs for accuracy.

Business Logic: The system should poll airline APIs for flight status updates, compare with stored flight data, and trigger notifications when changes occur. Notification preferences should be respected, and all sent notifications should be logged.

Technical Context: Technology stack includes React, Node.js, integration with airline APIs for real-time data, notification services (Twilio for SMS, Firebase for push), secure storage of user preferences, and HTTPS communication.

Non-Functional Requirements: System should deliver notifications within 1 minute of receiving updates, ensure high availability, encrypt user data, and provide monitoring and analytics on notification delivery.
