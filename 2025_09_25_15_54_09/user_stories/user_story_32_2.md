EPIC Number: 32
User Story Number: 2
User Story Title: As a business user, I want to edit or delete existing report schedules, so that I can update or remove schedules as my reporting needs change.

User Story Description: This feature allows users to view, modify, or delete their existing automated report schedules. Users should be able to change the frequency, time, report selection, or delivery method, as well as remove schedules that are no longer needed. The system should update or remove the scheduled tasks accordingly.

Acceptance Criteria:
1. Users can view a list of their scheduled reports.
2. Users can edit the schedule details (frequency, time, report, delivery method).
3. Users can delete any existing schedule.
4. Changes are reflected immediately in the scheduling system.
5. Users receive confirmation notifications for edits and deletions.

Validations:
1. Only schedules owned by the user can be edited or deleted.
2. Edited schedules must meet all validation rules for new schedules.
3. Deletion must remove all future scheduled tasks for that report.

Business Logic:
- Fetch all schedules for the authenticated user.
- On edit, validate new parameters and update the schedule in the database and scheduler.
- On delete, remove the schedule from the database and cancel future triggers.
- Notify the user of successful changes or errors.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, SQL Server database.
- Scheduler: Quartz.NET or Azure Functions Timer Trigger.
- Security: Only the schedule owner or an admin can edit/delete schedules.

Non-Functional Requirements:
- Schedule changes must be reflected within 1 minute.
- Deletions must ensure no further reports are generated for that schedule.
- All changes and deletions must be logged for auditing.
- System must prevent race conditions during concurrent edits.