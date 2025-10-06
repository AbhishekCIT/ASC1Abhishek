EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track my flight status in real-time, So that I can stay updated about any delays or gate changes.

User Story Description: This user story enables passengers to check the real-time status of their booked flights, including departure/arrival times, delays, and gate information. The system should provide timely notifications for any changes.

Acceptance Criteria:
1. Passengers can enter their flight number to view current status.
2. Real-time updates are displayed for delays, cancellations, and gate changes.
3. Notifications are sent via email/SMS/app push for status changes.

Validations:
1. Only valid flight numbers are accepted.
2. Status updates must be accurate and timely.
3. Notifications are sent to the correct contact details.

Business Logic:
- System fetches real-time data from airline APIs.
- Notification engine triggers alerts based on status changes.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure Functions for scheduled data fetch.
- Integration with airline status APIs.
- Data format: JSON.
- Security: HTTPS, user authentication for personalized notifications.

Non-Functional Requirements:
- Real-time update latency < 1 minute.
- 99.9% uptime for status service.
- Scalable to support spikes in user queries during peak travel.
- Monitoring and alerting for API failures.
