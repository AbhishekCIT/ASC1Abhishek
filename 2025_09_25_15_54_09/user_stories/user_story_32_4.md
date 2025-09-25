EPIC Number: 32
User Story Number: 4
User Story Title: As a business user, I want to pause and resume my scheduled reports, so that I can temporarily stop and restart automated report delivery as needed.

User Story Description: This feature allows users to pause active report schedules, preventing report generation and delivery until the schedule is resumed. Users can later resume the schedule, restoring the original automation without needing to recreate the schedule.

Acceptance Criteria:
1. Users can pause any active schedule from the dashboard or schedule details page.
2. Paused schedules do not trigger report generation or delivery.
3. Users can resume paused schedules, restoring their original configuration.
4. Users receive confirmation notifications for pause and resume actions.

Validations:
1. Only active schedules can be paused; only paused schedules can be resumed.
2. Pausing or resuming must update the schedule status in the system immediately.
3. System must prevent duplicate pause/resume actions.

Business Logic:
- Update the schedule status in the database to 'paused' or 'active'.
- Scheduler must skip triggers for paused schedules.
- On resume, scheduler reinstates triggers as per the original configuration.
- Notify the user of successful pause/resume.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, SQL Server database.
- Scheduler: Quartz.NET or Azure Functions Timer Trigger.
- Security: Only the schedule owner or admin can pause/resume schedules.

Non-Functional Requirements:
- Pause/resume actions must take effect within 30 seconds.
- All status changes must be logged for auditing.
- System must handle up to 500 concurrent pause/resume actions without performance degradation.