EPIC Number: 32
User Story Number: 1
User Story Title: As a business user, I want to schedule automated reports, so that I can receive timely insights without manual intervention.

User Story Description: This feature allows business users to set up schedules for generating and distributing reports automatically at specified intervals (daily, weekly, monthly, etc.). The user can select the report, configure the schedule, and specify recipients. The system will generate and deliver the report as per the defined schedule.

Acceptance Criteria:
1. Users can select any available report for scheduling.
2. Users can define the frequency (daily, weekly, monthly, custom) and time for report generation.
3. Users can specify multiple recipients for report delivery.
4. System sends the report to the recipients at the scheduled time.
5. Users receive confirmation/notification upon successful report delivery.
6. Users can view, edit, or delete scheduled reports.

Validations:
1. Only valid email addresses can be added as recipients.
2. Scheduling frequency and time must be within allowed parameters.
3. Users cannot schedule reports for past dates/times.
4. Duplicate schedules for the same report and recipients are prevented.

Business Logic: 
- When a schedule is created, store the report ID, schedule details, and recipient list in the database.
- At the scheduled time, trigger the report generation process.
- If report generation is successful, send the report as an email attachment or link to all recipients.
- Log all scheduled jobs, executions, and delivery statuses.
- Allow users to modify or cancel schedules at any time before execution.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database, Azure Functions for scheduling, SendGrid for email delivery.
- APIs: RESTful APIs for schedule management, report generation, and notification.
- Data formats: JSON for API payloads, PDF/Excel for reports.
- Security: OAuth2 authentication, role-based access control, encrypted storage of schedules and recipient data.

Non-Functional Requirements:
- Performance: Scheduled reports must be generated and delivered within 5 minutes of the scheduled time.
- Availability: 99.9% uptime for scheduling and delivery services.
- Security: All data in transit and at rest must be encrypted; access logs must be maintained.
- Scalability: System must support up to 10,000 concurrent scheduled jobs.
- Monitoring: Real-time monitoring and alerting for failed report generations or deliveries.
