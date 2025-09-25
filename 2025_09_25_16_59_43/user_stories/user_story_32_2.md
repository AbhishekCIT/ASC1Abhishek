EPIC Number: 32
User Story Number: 2
User Story Title: As a report recipient, I want to receive notifications for scheduled report deliveries and errors, so that I am informed about successful and failed report deliveries.

User Story Description: This feature ensures that users receive notifications when a scheduled report is delivered successfully or if there is a failure in report generation or delivery. Notifications should be clear and provide actionable information for users to address any issues.

Acceptance Criteria:
1. Users receive an email notification upon successful report delivery.
2. Users receive an error notification if report generation or delivery fails.
3. Notifications include details of the report, schedule, and error (if any).
4. Users can view notification history in the application.

Validations:
1. Email notifications are sent only to valid recipients.
2. Error notifications include relevant error codes and troubleshooting steps.
3. Notification history is retained for at least 90 days.

Business Logic:
- After each scheduled report job, the system checks the result and sends notifications accordingly.
- Success notifications include report name, delivery time, and recipient list.
- Error notifications include the error type, affected report, and suggested actions.
- Notification history is stored in the database and accessible to users.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database.
- Frameworks: Hangfire for job scheduling, SendGrid for email notifications.
- API: RESTful endpoints for notification history retrieval.
- Data formats: JSON for API communication, HTML for email content.
- Security: Notifications only accessible by authorized users.

Non-Functional Requirements:
- Performance: Notifications sent within 1 minute of report delivery or failure.
- Availability: 99.9% uptime for notification service.
- Security: Notification content encrypted in transit.
- Scalability: Support up to 10,000 notifications per day.
- Monitoring: Application Insights for notification delivery and error tracking.