EPIC Number: 32
User Story Number: 4
User Story Title: As a compliance officer, I want all actions related to scheduled reports to be logged, so that I can ensure auditability and regulatory compliance.

User Story Description: Every action related to scheduled reports (creation, modification, deletion, execution, delivery, and failure) should be logged with user, timestamp, and action details. Logs should be accessible to authorized compliance and audit personnel.

Acceptance Criteria:
1. All actions on scheduled reports are logged with user, timestamp, and action details.
2. Audit logs are tamper-proof and accessible only to authorized personnel.
3. Logs can be exported for compliance reviews.
4. System retains logs for a configurable retention period (e.g., 7 years).

Validations:
1. Only authorized users can access audit logs.
2. Logs are immutable and cannot be altered by end users.
3. Log export includes all relevant details and is in a standard format (CSV, JSON).

Business Logic:
- On every action (create, edit, delete, execute, deliver, fail), write an entry to the audit log table.
- Restrict log access based on user roles.
- Implement log retention and secure deletion policies.

Technical Context:
- .NET Core backend for logging logic.
- Azure SQL or Azure Log Analytics for log storage.
- Role-based access via Azure AD.
- Export functionality via secure API.

Non-Functional Requirements:
- Logs must be written within 1 second of action.
- System must support 7+ years of log retention.
- High security and encryption for log data.
- Audit log access must be monitored and logged.
