EPIC Number: 32
User Story Number: 2
User Story Title: As a business user, I want to view, edit, or delete my scheduled reports, so that I can manage my automated reporting preferences.

User Story Description: This feature allows users to see a list of all their scheduled reports, with details such as report name, schedule frequency, recipients, and next run time. Users can edit the schedule parameters or delete schedules that are no longer needed.

Acceptance Criteria:
1. Users can view a list of all their scheduled reports.
2. Users can edit the frequency, recipients, or report parameters of an existing schedule.
3. Users can delete any schedule they own.
4. System prevents editing or deleting schedules owned by other users unless the user is an admin.

Validations:
1. Only valid changes are saved (e.g., valid email addresses, valid frequencies).
2. Deleting a schedule removes all future report deliveries for that schedule.
3. Editing a schedule updates the next run time appropriately.

Business Logic:
- Fetch all schedules for the logged-in user from the database.
- On edit, validate new parameters and update the schedule record.
- On delete, remove the schedule and cancel any queued jobs.
- Admins can manage all users’ schedules.

Technical Context:
- Angular frontend for schedule management UI.
- .NET Core API endpoints for CRUD operations on schedules.
- SQL Server for storing schedule metadata.
- Security: Only authenticated users can access their schedules; admins have elevated permissions.

Non-Functional Requirements:
- UI must load the list of schedules within 2 seconds.
- All changes must be reflected in the backend within 1 minute.
- Audit trail for all edits and deletions.
- System must handle at least 500 concurrent users managing schedules.
