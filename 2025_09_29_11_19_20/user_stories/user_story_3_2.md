EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track my flight status in real-time, so that I can stay informed about delays or changes.

User Story Description: This feature enables passengers to check the current status of their booked flights, including departure and arrival times, delays, gate changes, and cancellations. Notifications should be sent for any changes affecting the passenger's itinerary.

Acceptance Criteria:
1. Passengers can enter their flight number or booking reference to view status.
2. System displays real-time updates from airline data sources.
3. Notifications are sent via email/SMS/app push for any status changes.

Validations:
1. Validate flight number or booking reference input.
2. Ensure data is updated in real-time from airline APIs.
3. Confirm notifications are sent for all relevant status changes.

Business Logic: The system queries airline APIs for live flight status, maps updates to user bookings, and triggers notifications for relevant events (delays, gate changes, cancellations).

Technical Context: Integration with airline status APIs, backend in Node.js, frontend in React, notification service (e.g., Twilio for SMS, SendGrid for email), secure data handling.

Non-Functional Requirements: Status updates must be reflected within 1 minute of change, notifications delivered within 30 seconds, system must support 99.9% uptime, and ensure data privacy for user information.