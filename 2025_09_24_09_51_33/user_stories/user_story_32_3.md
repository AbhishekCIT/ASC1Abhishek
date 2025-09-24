EPIC Number: 32
User Story Number: 3
User Story Title: As an admin, I want to monitor and audit all scheduled report activities, so that I can ensure compliance and troubleshoot delivery issues.

User Story Description: This feature provides an audit trail and monitoring dashboard for all scheduled report activities, including creation, modification, delivery status, and errors. Admins can filter by user, report, date, and status to investigate issues or ensure compliance with organizational policies.

Acceptance Criteria:
1. Admins can view a log of all scheduled report activities.
2. Logs include details such as user, report, action (create/edit/delete), timestamp, delivery status, and error messages.
3. Admins can filter and export logs for compliance or troubleshooting.
4. System generates alerts for failed report deliveries.

Validations:
1. Only admins can access the audit and monitoring dashboard.
2. All actions (create/edit/delete/delivery) are logged with accurate timestamps and user IDs.
3. Exported logs match the displayed data.

Business Logic:
- Log every schedule-related action and delivery attempt in an audit table.
- Provide API endpoints for querying and filtering logs.
- Generate alerts for failed deliveries and notify admins.

Technical Context:
- Backend: .NET Core API for log management.
- Frontend: Angular dashboard for viewing/filtering logs.
- Database: SQL Server audit tables.
- Security: Admin-only access, encrypted log storage.

Non-Functional Requirements:
- Audit logs must be retained for at least 2 years.
- Dashboard must support filtering 10,000+ log entries efficiently.
- Alerts must be generated within 1 minute of a failed delivery.
- Export to CSV or Excel supported for compliance.
