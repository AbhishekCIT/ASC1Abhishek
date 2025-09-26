EPIC Number: 3
User Story Number: 4
User Story Title: As an airline administrator, I want to manage flight inventory and schedules, So that I can ensure accurate and up-to-date flight information is available to travelers.

User Story Description: Provide airline administrators with tools to add, update, or remove flights, set schedules, and manage seat inventory. The system should validate changes, synchronize with external airline systems, and notify travelers of any schedule changes.

Acceptance Criteria:
1. Administrators can add new flights and set schedules.
2. Administrators can update or cancel existing flights.
3. Changes are reflected in traveler search and booking results.
4. Notifications are sent to affected travelers.

Validations:
1. Flight numbers must be unique and valid.
2. Schedule changes cannot conflict with existing bookings.
3. Inventory updates must not exceed aircraft capacity.

Business Logic:
- Validate flight details before saving.
- Synchronize changes with external airline systems.
- Trigger notifications for schedule changes or cancellations.
- Update seat inventory in real-time.

Technical Context:
- Technology stack: .NET Core backend, React frontend.
- Integration with airline management APIs.
- Secure authentication for admin users.
- Data stored in Azure SQL Database.

Non-Functional Requirements:
- Admin portal available 24/7.
- Audit logging for all changes.
- Secure access with role-based permissions.
- Real-time sync with traveler-facing systems.
- Monitoring for failed updates or sync errors.
