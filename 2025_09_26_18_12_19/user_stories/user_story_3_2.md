EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the status of my booked flight, So that I can stay updated about delays or cancellations.

User Story Description: This feature enables passengers to view real-time status updates for their booked flights, including departure/arrival times, gate information, and notifications about delays or cancellations.

Acceptance Criteria:
1. Users can view flight status from their booking dashboard.
2. Users receive notifications for delays, cancellations, or gate changes.
3. Flight status is updated in real-time.

Validations:
1. Only users with a valid booking can access flight status.
2. Flight status data must be refreshed every minute.
3. Notification preferences must be configurable by the user.

Business Logic: The system should poll the airline's API for flight status updates and push notifications to users based on their preferences. Updates are displayed on the user's dashboard.

Technical Context: Technology stack: ReactJS frontend, NodeJS backend, PostgreSQL database. Integration with airline APIs for status updates. Push notification service (e.g., Firebase Cloud Messaging). Data exchanged in JSON format. Security via HTTPS and JWT authentication.

Non-Functional Requirements: Status updates latency < 1 minute. 99.9% uptime. Secure data handling. Scalable notification delivery. Monitoring via Azure Application Insights.