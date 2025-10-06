EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the status of my flight, So that I can stay informed about any delays or changes.

User Story Description: This user story enables passengers to check real-time flight status updates, including departure/arrival times, delays, gate changes, and cancellations. Users should be able to search for their flight using flight number or booking reference and receive timely notifications about changes.

Acceptance Criteria:
1. Users can search for flight status using flight number or booking reference.
2. The system displays up-to-date information on departure/arrival times, gate, and status (on time, delayed, cancelled).
3. Users can opt-in to receive notifications (email/SMS/push) for status changes.

Validations:
1. Flight number or booking reference must be valid and exist in the system.
2. Notifications must be sent only to verified contact details.

Business Logic: The system will integrate with airline or airport APIs to fetch real-time flight status. Notification logic will trigger alerts to users upon status change events.

Technical Context: The feature will use REST APIs to fetch flight status from external sources. Notification services will be integrated for email/SMS/push. The frontend will be built in React, backend in Node.js, and notifications managed via third-party services (e.g., Twilio, SendGrid).

Non-Functional Requirements: Flight status updates must be reflected within 1 minute of change. Notification delivery must be 99% reliable. The system must handle up to 10,000 concurrent status queries and notifications per hour.