EPIC Number: 32
User Story Number: 5
User Story Title: As a system, I want to automatically retry failed scheduled report deliveries, so that temporary issues do not prevent users from receiving their reports.

User Story Description: This feature ensures that if a scheduled report delivery fails due to temporary issues (e.g., network outage, mail server down), the system will automatically retry delivery a configurable number of times before marking it as failed and notifying the user/admin.

Acceptance Criteria:
1. System detects failed report deliveries automatically.
2. System retries delivery up to a configurable number of attempts (e.g., 3 times) with exponential backoff.
3. Users and admins are notified only if all retries fail.
4. All retry attempts and outcomes are logged for auditing.

Validations:
1. Only transient errors trigger retries (e.g., network, temporary mail server issues).
2. Permanent errors (e.g., invalid recipient) do not trigger retries.
3. Retry logic does not result in duplicate report deliveries.

Business Logic:
- On delivery failure, check error type.
- If transient, schedule a retry with exponential backoff.
- After max retries, mark as failed and notify stakeholders.
- Log all attempts and outcomes.

Technical Context:
- .NET Core backend with retry logic (e.g., Polly library).
- Logging to SQL Server audit tables.
- Integration with notification system for failure alerts.
- Security: Only system processes can trigger retries.

Non-Functional Requirements:
- Retry attempts must not exceed 15 minutes total delay.
- System must handle 100+ concurrent retry operations.
- All retry operations must be auditable.
- No duplicate deliveries allowed.
