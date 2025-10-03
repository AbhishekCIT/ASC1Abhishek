EPIC Number: 3
User Story Number: 5
User Story Title: As an airline administrator, I want to manage flight schedules, So that I can ensure accurate and up-to-date flight information is available to travelers.

User Story Description: This feature allows airline administrators to create, update, and delete flight schedules, including flight numbers, timings, aircraft types, and seat availability. Changes should be reflected in real-time for travelers searching for flights.

Acceptance Criteria:
1. Admin can create new flight schedules with all required details.
2. Admin can update existing flight schedules.
3. Admin can delete or deactivate flights as needed.
4. Changes are reflected immediately in the flight search results.
5. System logs all schedule changes for audit purposes.

Validations:
1. Flight numbers must be unique.
2. Schedule times must not overlap for the same aircraft.
3. All required fields must be completed before saving.

Business Logic:
- Validate input data for schedule creation and updates.
- Prevent scheduling conflicts for aircraft.
- Update inventory and notify affected users if changes impact existing bookings.

Technical Context:
- Technology stack: React (admin frontend), Node.js/Express (backend), PostgreSQL (database).
- APIs: REST API for schedule management.
- Data formats: JSON for API requests/responses.
- Security: Role-based access control, HTTPS, audit logging.

Non-Functional Requirements:
- Schedule updates should propagate to search within 1 minute.
- System should support at least 100 concurrent admin users.
- All schedule changes must be logged for compliance.
- 99.9% uptime for schedule management functionality.
