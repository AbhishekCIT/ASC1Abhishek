EPIC Number: 3
User Story Number: 4
User Story Title: As a traveler, I want to track the real-time status of my flight, So that I can plan my arrival at the airport accordingly

User Story Description: This feature allows users to view the real-time status of their flights, including departure/arrival times, delays, gate information, and cancellations. Users can search by flight number or booking reference and receive notifications for any changes.

Acceptance Criteria:
1. Users can search for flight status using flight number or booking reference.
2. System displays up-to-date flight status, including delays, gate changes, and cancellations.
3. Users can opt-in for real-time notifications (SMS/email/push).
4. Flight status updates are refreshed automatically every 60 seconds.

Validations:
1. Only valid flight numbers or booking references are accepted.
2. Notifications are sent only to verified contact details.
3. System handles flights operated by partner airlines.

Business Logic:
- Integrate with airline and airport APIs for real-time status updates.
- Map flight status codes to user-friendly messages.
- Trigger notifications based on status changes.

Technical Context:
- Technology stack: React.js (frontend), Node.js/Express (backend), PostgreSQL (database).
- Integration with airline/airport APIs (REST/JSON, XML).
- Data formats: JSON/XML for API communication.
- Security: Secure handling of user contact details and notifications.

Non-Functional Requirements:
- Flight status must be updated within 30 seconds of change.
- System must be available 99.9% of the time.
- Scalable to handle 10,000 concurrent status queries.
- Monitor API latency and notification delivery success.