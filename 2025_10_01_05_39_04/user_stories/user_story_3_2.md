EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the status of my flight, so that I can stay informed about any delays or changes.

User Story Description: This feature enables passengers to check real-time flight status updates, including delays, gate changes, cancellations, and estimated arrival/departure times. Notifications should be sent for any significant changes to the flight schedule.

Acceptance Criteria:
1. Passengers can search for their flight by flight number or booking reference.
2. Real-time status updates are displayed for the selected flight.
3. Passengers receive notifications (email/SMS/push) for any changes to their flight.

Validations:
1. Only valid flight numbers or booking references are accepted.
2. Notifications are sent only to passengers with valid contact details.
3. Status updates are refreshed at least every 2 minutes.

Business Logic:
- Retrieve flight status from airline data sources or APIs.
- Match passenger bookings to flight status records.
- Trigger notifications on status changes (delay, gate change, cancellation).

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure Functions for scheduled updates.
- APIs: Airline flight status API, Notification service API.
- Data formats: JSON for API communication.
- Security: HTTPS, authentication for accessing booking details.

Non-Functional Requirements:
- System must support real-time updates with <1 minute latency.
- 99.9% availability.
- Notifications must be delivered within 30 seconds of a status change.
- System must be scalable for peak travel periods.
- Monitoring for failed or delayed notifications.