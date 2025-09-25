EPIC Number: 32
User Story Number: 4
User Story Title: As an auditor, I want to access logs of all scheduled report activities, so that I can ensure compliance and traceability of automated report scheduling.

User Story Description: This feature will provide authorized auditors with access to detailed logs of all actions related to scheduled reports, including creation, modification, deletion, and delivery attempts. The logs should be searchable, filterable, and exportable for compliance and review purposes.

Acceptance Criteria:
1. Auditors can view a complete log of scheduled report activities.
2. Logs include timestamps, user IDs, action types, and status.
3. Logs are searchable and filterable by date, user, action, and status.
4. Logs can be exported in CSV or PDF format.
5. Access to logs is restricted to authorized personnel.

Validations:
1. Only users with auditor or admin roles can access logs.
2. All actions related to scheduling are logged in real-time.
3. Exported logs match the data displayed in the UI.

Business Logic:
- Every action on scheduled reports triggers a log entry with metadata.
- Logs are stored securely and retained per compliance requirements.
- Export functionality generates a snapshot of filtered logs.

Technical Context:
- Technology Stack: .NET Core backend, Angular frontend
- Database: SQL Server with audit log tables
- APIs: RESTful endpoints for log retrieval and export
- Data Formats: JSON for API, CSV/PDF for export
- Security: Role-based access, encryption at rest

Non-Functional Requirements:
- Performance: Log retrieval for 1,000,000+ records within 5 seconds.
- Availability: 99.9% uptime for audit log service.
- Security: Immutable logs, access logging for audit views.
- Scalability: Support for high-volume log generation and queries.
- Monitoring: Alerts for unauthorized access attempts.
