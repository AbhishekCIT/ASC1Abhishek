EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to track my flight status in real time, So that I can stay informed about delays or gate changes.

User Story Description: This user story enables travelers to view up-to-date information about their booked flights, including departure/arrival times, delays, gate assignments, and cancellations, through the application interface.

Acceptance Criteria:
1. Users can view live status of their booked flights.
2. Users receive notifications for any changes in flight schedule or gate.
3. The system displays accurate and timely updates from airlines.

Validations:
1. Only users with a valid booking can access flight status.
2. Flight status data must be refreshed every 2 minutes.
3. Notification delivery must be confirmed.

Business Logic:
- Fetch flight status from airline APIs using booking reference.
- Push notifications for any status change (delay, gate change, cancellation).
- Display status on user dashboard.

Technical Context:
- Technology stack: .NET Core backend, React frontend, SQL Server database.
- APIs: Integration with airline status feeds (REST/SOAP).
- Data format: JSON/XML for status updates.
- Security: JWT authentication for user sessions.

Non-Functional Requirements:
- Real-time updates with < 2-minute latency.
- 99.9% notification delivery success rate.
- Secure storage of user contact information.
- System must handle 10,000 concurrent users tracking flights.
- Monitoring of API response times and notification logs.
