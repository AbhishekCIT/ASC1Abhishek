EPIC Number: 32
User Story Number: 3
User Story Title: As a report recipient, I want to receive notifications about the status of scheduled reports, so that I am aware of successful deliveries or failures.

User Story Description: The system should notify users via email and/or in-app notifications when a scheduled report is delivered, and also alert them if a report generation or delivery fails, including the reason for failure.

Acceptance Criteria:
1. Users receive an email and/or in-app notification when a scheduled report is delivered.
2. Users receive a failure notification if report generation or delivery fails, with a clear error message.
3. Notifications include report name, scheduled time, and status.
4. Users can configure their notification preferences.

Validations:
1. Only intended recipients receive notifications.
2. Notifications are sent within 2 minutes of report generation/delivery.
3. Failure notifications include actionable information.

Business Logic:
- After report generation, trigger notification workflow.
- On failure, capture error details and notify both the owner and recipients.
- Allow users to opt in/out of certain notifications.

Technical Context:
- SendGrid for email, Angular for in-app notifications.
- .NET Core backend for notification logic.
- Secure storage of notification preferences in Azure SQL.

Non-Functional Requirements:
- Notifications must be delivered within 2 minutes of event.
- System must handle notification spikes (1000+ concurrent events).
- All notifications must be logged for audit.
- High reliability and security for notification delivery.
