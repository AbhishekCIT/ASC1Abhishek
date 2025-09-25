EPIC Number: 32
User Story Number: 2
User Story Title: As an end user, I want to set my preferred delivery method for scheduled reports, So that I can receive reports via email, download link, or dashboard notification according to my needs.

User Story Description: This user story enables end users to choose how they receive scheduled reports. Options include email, direct download link, or notification within the application dashboard. The feature provides flexibility and improves user experience.

Acceptance Criteria:
1. User can select delivery method for each scheduled report.
2. User can update delivery preferences at any time.
3. System delivers reports using the selected method.
4. User receives confirmation of report delivery.

Validations:
1. Email address must be valid if email delivery is selected.
2. Download link must be accessible only to the intended user.
3. Dashboard notifications must be visible to the user upon login.

Business Logic: When a report is scheduled, the system checks the user's delivery preference and routes the report accordingly. If email is selected, the report is sent as an attachment or link. For dashboard notifications, the report is made available for download in the user's account. Pseudocode:
- On report generation: Check delivery method, send report via chosen channel.
- On preference update: Store new preference, apply to future deliveries.

Technical Context: Technology stack: .NET Core backend, Angular frontend, Azure SendGrid for email delivery, Azure Blob Storage for download links, SignalR for dashboard notifications. API endpoints for managing preferences. Security: Ensure only authenticated users access their reports.

Non-Functional Requirements:
- Performance: Report delivery should occur within 1 minute of scheduled time.
- Availability: Delivery system available 99.9% uptime.
- Security: Reports accessible only to authorized users.
- Scalability: Support up to 100,000 report deliveries per day.
- Monitoring: Delivery success/failure tracked in Azure Monitor.