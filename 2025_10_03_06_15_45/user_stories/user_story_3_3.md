EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to track my checked-in baggage, So that I can be assured of its location and status during my journey.

User Story Description: This feature enables passengers to track the status and location of their checked-in baggage through the airline's digital platform. The system should provide updates at key points such as check-in, loading, transfer, and arrival.

Acceptance Criteria:
1. Users can enter their baggage tag number to view status.
2. System displays real-time baggage location updates.
3. Users receive notifications if baggage is delayed or rerouted.

Validations:
1. Validate baggage tag number format and association with the passenger.
2. Ensure updates are sourced from airport/airline baggage handling systems.
3. Only authorized users can access baggage information.

Business Logic: The system integrates with airport baggage handling systems to fetch and display baggage status, and triggers notifications for status changes or exceptions.

Technical Context: Mobile/web app frontend, backend integration with airport/airline baggage APIs (REST/JSON), secure authentication, encrypted data transfer.

Non-Functional Requirements: Real-time updates with <2 minute latency, 99.9% uptime, GDPR-compliant data handling, scalable to support peak travel periods.