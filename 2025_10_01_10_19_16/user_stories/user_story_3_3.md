EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to track the status of my flights and receive notifications, so that I can stay informed about delays, cancellations, or gate changes.

User Story Description: This user story covers the ability for users to check real-time flight status and receive timely notifications (email, SMS, or in-app) about any changes to their booked flights.

Acceptance Criteria:
1. Users can view real-time status of their booked flights (on-time, delayed, cancelled, gate info).
2. Users receive notifications for any status changes to their flights.
3. Users can configure notification preferences (email, SMS, in-app).

Validations:
1. Only users with a valid booking can subscribe to flight status notifications.
2. Notification delivery is confirmed and logged.
3. Flight status data is updated at least every 5 minutes.

Business Logic:
- Integration with airline or airport APIs for real-time status.
- Notification engine triggers alerts based on status changes.
- User preferences determine notification channels.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure SQL Database.
- Integration with third-party flight status APIs.
- Notification service (e.g., Twilio for SMS, SendGrid for email).
- Data formats: JSON for API communication.
- Security: HTTPS, user authentication via OAuth2.

Non-Functional Requirements:
- Notification delivery within 1 minute of status change.
- System should scale to handle spikes in notifications.
- Availability: 99.9% uptime.
- Monitoring and alerting for failed notifications.
