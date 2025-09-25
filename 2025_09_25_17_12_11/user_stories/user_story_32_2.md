EPIC Number: 32
User Story Number: 2
User Story Title: As a business user, I want to manage my scheduled reports, so that I can update or remove schedules as my needs change.

User Story Description: This feature enables users to view all their scheduled reports, edit the schedule or recipients, and delete schedules that are no longer needed. The user interface should provide a list of all scheduled reports with options to modify or cancel them.

Acceptance Criteria:
1. Users can see a list of all their scheduled reports with details (report name, frequency, next run, recipients).
2. Users can edit the schedule (frequency, time) or recipient list for any scheduled report.
3. Users can delete any scheduled report.
4. System updates or removes the schedule in real time upon user action.
5. Users receive confirmation/notification for successful updates or deletions.

Validations:
1. Only the user who created a schedule can edit or delete it.
2. Edited schedules must meet all scheduling and recipient validation rules.
3. Deleted schedules are removed from the system and will not trigger future report generations.

Business Logic:
- Display all schedules associated with the logged-in user.
- Allow editing of schedule parameters and recipient lists.
- On deletion, remove the schedule and cancel any pending report generations.
- Log all changes for audit purposes.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database.
- APIs: RESTful endpoints for listing, updating, and deleting schedules.
- Security: OAuth2 authentication, user-level access control.

Non-Functional Requirements:
- Performance: Schedule changes should reflect in the system within 2 seconds.
- Security: Only authorized users can modify or delete their schedules.
- Auditability: All changes must be logged with user, timestamp, and action details.
- Usability: UI must be intuitive and responsive for managing schedules.
