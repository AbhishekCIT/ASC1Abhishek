EPIC Number: 32
User Story Number: 2
User Story Title: As a business user, I want to manage (view, edit, delete) my scheduled reports, so that I can keep my reporting schedules up to date and relevant.

User Story Description: This feature enables users to access a dashboard or list of all their scheduled reports, with options to view details, modify scheduling parameters, or delete schedules that are no longer needed. The interface should provide clear feedback on the status of each schedule and allow bulk actions where appropriate.

Acceptance Criteria:
1. Users can view a list of all their scheduled reports.
2. Users can edit the frequency, recipients, or delivery method of existing schedules.
3. Users can delete one or more schedules.
4. System confirms changes and updates schedules accordingly.

Validations:
1. Only the creator or authorized users can edit or delete a schedule.
2. Edits must not create conflicting or duplicate schedules.
3. Deletion must require confirmation to prevent accidental removal.

Business Logic:
- Retrieve all schedules associated with the user from the database.
- On edit, validate new parameters and update the schedule.
- On delete, remove the schedule and cancel any pending jobs.
- Log all changes for audit purposes.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database.
- RESTful APIs for schedule management.
- Security: Role-based access, audit logging.

Non-Functional Requirements:
- UI must load all schedules within 2 seconds for up to 100 schedules.
- All changes must be reflected in real time.
- Audit logs must be immutable and retained for at least 1 year.