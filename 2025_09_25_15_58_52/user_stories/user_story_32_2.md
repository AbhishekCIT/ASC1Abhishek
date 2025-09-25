EPIC Number: 32
User Story Number: 2
User Story Title: As a business user, I want to edit or cancel existing report schedules, so that I can manage my reporting needs as they change.

User Story Description: This feature allows users to view, modify, or delete their existing automated report schedules. Users can change the frequency, recipients, or report parameters, or cancel the schedule entirely.

Acceptance Criteria:
1. Users can view a list of all their scheduled reports.
2. Users can edit the frequency, recipients, and parameters of a scheduled report.
3. Users can cancel a scheduled report and receive confirmation.
4. Changes take effect immediately or from the next scheduled run.

Validations:
1. Only the owner or authorized users can edit/cancel a schedule.
2. Edited schedules must be validated for correct frequency and recipient format.
3. Cancelled schedules must not trigger further report generation.

Business Logic:
- When a user edits a schedule, the backend updates the corresponding job and notifies affected recipients if necessary.
- Cancelling a schedule removes the job from the scheduler and sends a cancellation confirmation.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database, Azure Functions for scheduling.
- API endpoints for editing and deleting schedules.
- Security: Role-based access, audit logs for changes and deletions.

Non-Functional Requirements:
- Performance: Edits and cancellations must be processed within 2 seconds.
- Security: Only authorized users can modify schedules.
- Auditability: All changes tracked in audit logs.
- Monitoring: Alerts for failed edits or cancellations.