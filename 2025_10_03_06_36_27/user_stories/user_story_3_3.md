EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to check the real-time status of my flight, So that I can stay informed about any delays, gate changes, or cancellations.

User Story Description: This user story covers the ability for users to enter their flight details or booking reference and receive up-to-date information about flight status, including departure/arrival times, delays, gate assignments, and cancellations. The feature should provide notifications for any status changes.

Acceptance Criteria:
1. Users can search for flight status using flight number, route, or booking reference.
2. Users can view real-time updates on departure, arrival, gate, and delay information.
3. Users can opt-in for notifications (email/SMS/push) for status changes.
4. System displays clear messages for cancelled or rescheduled flights.

Validations:
1. Flight status data must be fetched from authoritative sources (airline or airport APIs).
2. Notifications are only sent to users who have opted in.
3. All displayed times must be in the user's selected time zone.

Business Logic:
- System must poll airline/airport APIs for the latest status updates.
- Notification engine must trigger alerts for any change in status fields.
- All status queries and notifications must be logged for support and audit.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js/Express (backend), PostgreSQL (database).
- Integrations: Airline/Airport APIs for status, Notification services (email/SMS/push).
- Data formats: JSON for API, HTTPS for secure communication.
- Security: User authentication for personalized notifications.

Non-Functional Requirements:
- Status updates should be reflected within 1 minute of changes from the source.
- System must support 10,000+ status queries per hour.
- High availability (99.99%) for status tracking service.
- All notification deliveries must be tracked and retried on failure.
