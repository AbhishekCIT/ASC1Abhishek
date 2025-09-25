EPIC Number: 32
User Story Number: 3
User Story Title: As a business user, I want to receive notifications if a scheduled report fails or is missed, so that I can take corrective action promptly.

User Story Description: This feature ensures that users are notified via email and/or in-app notification if a scheduled report fails to generate or deliver, or if the schedule is missed due to system issues. The notification should include the reason for failure and suggested next steps.

Acceptance Criteria:
1. Users receive email and in-app notifications for failed or missed scheduled reports.
2. Notifications include clear information about the report, schedule, and failure reason.
3. Users can view a history of failed/missed schedules in the application.
4. System retries failed report generations up to a configurable limit.

Validations:
1. Notifications are only sent to users associated with the failed schedule.
2. Notification content must be clear, actionable, and free of sensitive information.
3. Retry logic must not exceed the configured retry limit.

Business Logic:
- Monitor all scheduled report executions for failures or missed triggers.
- On failure/miss, generate a notification event and send to the user(s).
- Include failure reason and recommended actions in the notification.
- Implement retry logic for failed executions, with exponential backoff.
- Log all notification events for audit and troubleshooting.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database, Azure Functions for monitoring, SendGrid for email, SignalR for in-app notifications.
- APIs: RESTful endpoints for notification history and retry management.
- Security: Notifications must not expose sensitive data; access controlled by user roles.

Non-Functional Requirements:
- Performance: Notifications must be sent within 1 minute of failure detection.
- Reliability: Notification and retry mechanisms must be highly available (99.9%).
- Security: Notification channels must be secure and authenticated.
- Monitoring: All notification events must be logged and monitored for SLA compliance.
