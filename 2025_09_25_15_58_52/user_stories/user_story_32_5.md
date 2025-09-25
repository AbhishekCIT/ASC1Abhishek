EPIC Number: 32
User Story Number: 5
User Story Title: As an administrator, I want to monitor and manage all scheduled report jobs, so that I can ensure system reliability and support users effectively.

User Story Description: This feature enables administrators to view, pause, resume, or force-run any scheduled report job in the system. Admins can see job status, next run time, last execution, and error logs. This helps in troubleshooting and maintaining system health.

Acceptance Criteria:
1. Admins can view a dashboard of all scheduled report jobs with key details.
2. Admins can pause, resume, or force-run any job.
3. Admins can view detailed logs and error messages for each job.
4. Actions taken by admins are logged for audit purposes.

Validations:
1. Only users with admin privileges can access this functionality.
2. All actions must be logged with timestamp and admin identity.
3. Paused jobs must not execute until resumed.

Business Logic:
- Admin actions update job status in the scheduler and database.
- Forced runs trigger immediate report generation and delivery.
- All admin actions are recorded in audit logs for compliance.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database, Azure Functions.
- Admin API endpoints for job management.
- Security: Strict role-based access, multi-factor authentication for admin actions.

Non-Functional Requirements:
- Performance: Admin dashboard must load within 2 seconds for up to 10,000 jobs.
- Security: All admin actions are auditable and protected.
- Availability: Admin functions available 24/7.
- Monitoring: Alerts for failed or overdue jobs, and unauthorized admin access attempts.