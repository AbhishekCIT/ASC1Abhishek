EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the status of my flight, so that I can stay informed about delays or cancellations.

User Story Description: This feature enables passengers to view real-time updates about their booked flights, including departure and arrival times, gate information, delays, and cancellations. Notifications should be sent via email or SMS for any changes.

Acceptance Criteria:
1. Passengers can view live status of their booked flights.
2. Passengers receive notifications for any changes in flight status.
3. Flight status is updated in real-time from airline data sources.

Validations:
1. Validate passenger booking reference before showing status.
2. Validate airline data feed for accuracy and timeliness.
3. Validate notification delivery to passenger’s contact details.

Business Logic: The system should poll airline APIs for flight status every 5 minutes and update the passenger’s dashboard. If a delay or cancellation is detected, trigger notification workflow.

Technical Context: Tech stack: .NET Core backend, React frontend, REST API integration with airline data providers, WebSocket for real-time updates, SMS/email notification service, secure endpoints.

Non-Functional Requirements: Real-time updates with <1 minute latency, 99.9% uptime, secure data transmission, scalable to support 10,000 simultaneous users, monitoring of API health and notification delivery.