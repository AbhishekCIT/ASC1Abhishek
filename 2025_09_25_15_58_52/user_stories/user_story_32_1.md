EPIC Number: 32
User Story Number: 1
User Story Title: As a business user, I want to schedule automated reports, so that I can receive regular updates without manual intervention.

User Story Description: This feature allows business users to set up automated schedules for generating and delivering reports at specified intervals (daily, weekly, monthly, etc.). Users can choose the report type, frequency, recipients, and preferred delivery method (email, download link, etc.).

Acceptance Criteria:
1. Users can select a report and schedule it for automated delivery.
2. Users can specify frequency (daily, weekly, monthly, custom).
3. Users can add or remove recipients for scheduled reports.
4. Users receive confirmation upon successful scheduling.
5. Scheduled reports are delivered as per the selected frequency.

Validations:
1. Only valid email addresses can be added as recipients.
2. Scheduling cannot be set for past dates/times.
3. Frequency options must be validated against business rules (e.g., not more than once per hour).

Business Logic: 
- When a user schedules a report, the system creates a recurring job in the backend scheduler.
- The job triggers report generation and delivery as per the defined schedule.
- If the report generation fails, the system retries as per retry policy and notifies the user of any persistent failures.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database, Azure Functions for scheduling.
- Reports are generated via REST API endpoints and delivered via SMTP or secure download links.
- Data formats: PDF, Excel, CSV.
- Security: Role-based access, encrypted report delivery, audit logs for scheduling actions.

Non-Functional Requirements:
- Performance: Reports must be generated and delivered within 5 minutes of scheduled time.
- Availability: 99.9% uptime for scheduling service.
- Security: All data in transit and at rest must be encrypted.
- Scalability: Support up to 10,000 concurrent scheduled jobs.
- Monitoring: Azure Application Insights for job status, failures, and delivery analytics.
