EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track the status of my flight, So that I can stay informed about delays or changes.

User Story Description: This user story covers the ability for users to check real-time status updates for their booked flights, including delays, gate changes, and cancellations. Notifications should be sent proactively to users in case of any changes.

Acceptance Criteria:
1. Users can view real-time flight status from their booking dashboard.
2. Users receive notifications for any changes in flight schedule or gate.
3. Status updates are accurate and timely.

Validations:
1. Only users with a valid booking can access flight status.
2. Notification delivery must be confirmed (email/SMS/push).
3. Flight status data must be synchronized with airline systems.

Business Logic: The system must poll airline APIs for status updates and push notifications to users. If multiple users are booked on the same flight, all must be notified simultaneously. Status updates must be logged for audit purposes.

Technical Context: Integrate with airline status APIs, use a notification service (e.g., Twilio, Firebase), and ensure secure user authentication. Data must be encrypted in transit and at rest.

Non-Functional Requirements: Status updates must be reflected within 1 minute of change. Notifications must be delivered to 99% of users within 2 minutes. System must be resilient to API failures and provide fallback messaging.