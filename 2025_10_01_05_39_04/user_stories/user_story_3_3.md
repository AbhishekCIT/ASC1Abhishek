EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to track my checked baggage, so that I can be assured of its location and status during my journey.

User Story Description: This feature allows passengers to monitor the status and location of their checked baggage throughout the travel process. Passengers receive updates at key points such as check-in, loading, transfer, and arrival, and can report issues if baggage is delayed or lost.

Acceptance Criteria:
1. Passengers can view the status of their checked baggage in real-time.
2. Notifications are sent at key baggage handling points (check-in, loading, transfer, arrival).
3. Passengers can report lost or delayed baggage directly from the application.

Validations:
1. Only valid baggage tag numbers are accepted for tracking.
2. Status updates are based on real-time data from baggage handling systems.
3. Reports for lost/delayed baggage require valid booking and contact information.

Business Logic:
- Integrate with airline and airport baggage tracking systems.
- Match baggage tags to passenger bookings.
- Trigger notifications at each handling point.
- Enable reporting workflow for lost/delayed baggage.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure Functions for integration.
- APIs: Baggage tracking API, Notification service API.
- Data formats: JSON for API communication.
- Security: HTTPS, authentication for accessing baggage status.

Non-Functional Requirements:
- Real-time updates with <1 minute latency.
- 99.9% availability.
- Notification delivery within 30 seconds of status change.
- Scalable to handle peak travel periods.
- Monitoring for failed or delayed updates.