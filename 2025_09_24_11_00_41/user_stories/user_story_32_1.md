EPIC Number: 32
User Story Number: 1
User Story Title: As a business analyst, I want to schedule automated reports, so that I can ensure timely delivery of insights to stakeholders.

User Story Description: This feature allows users to set up schedules for reports to be generated and delivered automatically at specified intervals (daily, weekly, monthly, etc.). The scheduling interface should be user-friendly and support customization of frequency, time, and recipients.

Acceptance Criteria:
1. Users can select a report and set a schedule for automatic generation.
2. Users can specify frequency (daily, weekly, monthly, custom).
3. Users can define delivery time and recipients (email, system notification).
4. Users receive confirmation upon successful scheduling.
5. Scheduled reports are generated and delivered as per the defined schedule.
6. Users can view, edit, or delete existing schedules.

Validations:
1. Only valid email addresses can be added as recipients.
2. Scheduling conflicts (e.g., overlapping schedules) are detected and warned.
3. Users cannot set schedules in the past.

Business Logic: 
- When a user sets a schedule, store the schedule details in the database.
- A background job checks the schedule and triggers report generation at the specified time.
- Generated reports are sent to the defined recipients.
- If delivery fails, notify the user and log the error.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure SQL Database.
- Use Azure Functions for background scheduling jobs.
- Email delivery via SendGrid API.
- Secure access via Azure AD authentication.
- Data format: PDF, Excel, CSV for reports.

Non-Functional Requirements:
- Reports must be generated within 2 minutes of scheduled time.
- System must be available 99.9% of the time.
- All data transmissions must be encrypted (TLS 1.2+).
- System must scale to handle 1000+ concurrent scheduled reports.
- Monitoring via Azure Application Insights.
