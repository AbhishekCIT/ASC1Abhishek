EPIC Number: 32
User Story Number: 3
User Story Title: As a business user, I want to view a dashboard of all my scheduled reports, so that I can monitor their status, history, and manage them efficiently.

User Story Description: This feature provides a dashboard where users can see all their scheduled reports, including details such as next run time, last run status, delivery history, and any errors. Users can filter, sort, and search their schedules for efficient management.

Acceptance Criteria:
1. Users can view a list of all their scheduled reports with key details (report name, frequency, next run, last run status).
2. Users can see a history of report deliveries and any errors encountered.
3. Users can filter and search schedules by report name, status, or date.
4. Users can access actions to edit or delete schedules from the dashboard.

Validations:
1. Only schedules owned by the user are visible.
2. Dashboard data must be refreshed in real-time or near real-time.
3. Error logs must display clear, actionable information.

Business Logic:
- Aggregate all schedules for the user from the database.
- Fetch delivery and error logs for each schedule.
- Provide sorting and filtering on the frontend.
- Link dashboard actions to edit/delete endpoints.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, SQL Server database.
- Real-time updates via SignalR or periodic polling.
- Security: Dashboard access restricted to authenticated users.

Non-Functional Requirements:
- Dashboard must load within 3 seconds for up to 100 schedules.
- Delivery and error logs must be retained for at least 90 days.
- All dashboard actions must be auditable.
- System must be responsive and accessible on mobile devices.