EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track the status of my flight, So that I can stay informed about delays or changes.

User Story Description: This user story enables travelers to view real-time flight status updates, including delays, gate changes, and cancellations, through the application. The feature should provide push notifications and easy access to flight status from the user's booking history.

Acceptance Criteria:
1. User can view current status of booked flights from their dashboard.
2. System displays real-time updates on delays, gate changes, and cancellations.
3. User receives push/email notifications for any status changes.

Validations:
1. Only users with a valid booking can access flight status.
2. Flight status data must be updated at least every 5 minutes.
3. Notifications must be sent within 1 minute of status change.

Business Logic: The system should poll airline status APIs at regular intervals, match updates to user bookings, and trigger notifications for relevant changes. Status updates should be logged for audit purposes.

Technical Context: Integrate with airline status APIs (REST/JSON), use a notification service (e.g., Firebase, SendGrid), and update frontend in real time using websockets or polling. Ensure secure access to user data.

Non-Functional Requirements: 
- Status updates must be reflected within 1 minute of change.
- System must handle 5,000 concurrent notification events.
- All user data must be encrypted.
- System must be monitored for notification delivery failures.