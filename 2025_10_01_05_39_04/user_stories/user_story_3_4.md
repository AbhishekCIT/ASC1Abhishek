EPIC Number: 3
User Story Number: 4
User Story Title: As an airline staff member, I want to manage and update flight schedules, so that passengers and systems have access to accurate and up-to-date information.

User Story Description: This feature enables authorized airline staff to create, edit, and cancel flight schedules, including departure/arrival times, gates, and aircraft assignments. Updates should be reflected in real-time for passengers and all dependent systems.

Acceptance Criteria:
1. Staff can create new flight schedules with all required details.
2. Staff can edit existing schedules and update times, gates, or aircraft.
3. Cancellations and changes are immediately reflected for passengers and integrated systems.
4. Audit logs are maintained for all changes.

Validations:
1. Only authorized staff can access and modify schedules.
2. All required fields must be completed before saving changes.
3. No overlapping schedules for the same aircraft.

Business Logic:
- Validate input for schedule creation and updates.
- Check for conflicts (e.g., aircraft or gate double-booking).
- Update all dependent systems and notify affected passengers.
- Maintain audit logs for compliance.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure SQL Database.
- APIs: Internal scheduling API, Notification service API.
- Data formats: JSON for API communication.
- Security: Role-based access control, HTTPS, audit logging.

Non-Functional Requirements:
- Changes must be reflected in all systems within 1 minute.
- 99.99% availability for staff portal.
- Audit logs must be immutable and retained for 7 years.
- Scalable for multiple airlines and thousands of flights.