EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to track the status of my booked flight, So that I can plan my arrival at the airport accordingly.

User Story Description: This feature provides real-time updates on flight status, including delays, cancellations, and gate changes. Users can view this information via the web portal or receive notifications.

Acceptance Criteria:
1. Users can enter their flight number to view status.
2. System displays real-time updates (on-time, delayed, cancelled, gate info).
3. Users can opt-in for SMS/email notifications for status changes.

Validations:
1. Validate flight number format and existence.
2. Ensure notifications are sent only to verified contact details.
3. Display data sourced from authoritative airline systems.

Business Logic: The system fetches flight status from airline APIs, updates the user interface, and triggers notifications for subscribed users on status changes.

Technical Context: React frontend, Node.js backend, integration with airline status APIs (REST/JSON), notification service (email/SMS), secure data handling.

Non-Functional Requirements: Real-time update latency <1 minute, 99.9% notification delivery, secure API communication, scalable to support peak travel times.