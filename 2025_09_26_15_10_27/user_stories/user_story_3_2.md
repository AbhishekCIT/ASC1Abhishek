EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track the status of my flight, So that I can stay informed about delays, cancellations, or gate changes.

User Story Description: This user story covers the ability for users to check real-time status updates for their booked flights, including departure/arrival times, delays, cancellations, and gate information. The feature should provide notifications for any changes affecting the user's journey.

Acceptance Criteria:
1. Users can enter their booking reference or flight number to retrieve status.
2. Real-time updates are displayed for departure/arrival times and gate information.
3. Users receive notifications (email/SMS/in-app) for any changes or disruptions.

Validations:
1. Flight number or booking reference must be valid and exist in the system.
2. Notification preferences must be set by the user.
3. Updates must be fetched from reliable, real-time sources.

Business Logic:
- Query flight status from airline or airport APIs.
- Match user bookings to flight status updates.
- Trigger notifications based on status changes.

Technical Context:
- Technology stack: ReactJS frontend, Node.js backend, Azure SQL Database.
- Integration with airline/airport real-time status APIs.
- Notification service integration (e.g., Twilio for SMS, SendGrid for email).
- Data in JSON format over HTTPS.
- User authentication required for accessing booking-linked updates.

Non-Functional Requirements:
- Status updates must be reflected within 1 minute of change.
- Notifications must be delivered within 30 seconds of status change.
- System must support high availability and redundancy for notification services.
- All data transmissions must be encrypted.
- Monitoring for failed notification deliveries.
