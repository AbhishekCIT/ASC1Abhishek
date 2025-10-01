EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the real-time status of my flight, So that I can stay informed about delays or gate changes.

User Story Description: This user story enables passengers to view real-time updates on their flight status, including departure and arrival times, delays, gate assignments, and cancellations. Notifications should be sent to users for any changes affecting their flight.

Acceptance Criteria:
1. Passengers can enter flight number or booking reference to view status.
2. Real-time updates are displayed for departure, arrival, and gate information.
3. Passengers receive notifications for any changes or delays.

Validations:
1. Flight number or booking reference must be valid.
2. Status updates must be accurate and timely.
3. Notifications must be sent to the correct user account or contact information.

Business Logic: The system should poll airline APIs for real-time flight data, match user bookings to flight numbers, and trigger notifications on status changes.

Technical Context: Integration with airline data providers via REST APIs, backend in Node.js, frontend in React, push notifications via Firebase, and secure handling of user data.

Non-Functional Requirements: Updates must be reflected within 1 minute of change, notifications must be delivered within 30 seconds, system must be available 24/7, and all data transmissions must be encrypted.