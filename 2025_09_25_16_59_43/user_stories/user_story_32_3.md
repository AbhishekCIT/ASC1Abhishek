EPIC Number: 32
User Story Number: 3
User Story Title: As a user, I want an intuitive interface to view, edit, and delete my scheduled reports, so that I can easily manage my report schedules.

User Story Description: This feature provides users with a dashboard to view all their scheduled reports, edit scheduling parameters, and delete schedules as needed. The interface should be user-friendly and provide clear options for managing schedules.

Acceptance Criteria:
1. Users can view a list of all their scheduled reports with details (report name, frequency, next run, recipients).
2. Users can edit schedule parameters for any report.
3. Users can delete scheduled reports.
4. Changes are reflected immediately in the scheduling system.

Validations:
1. Only authorized users can access and modify their schedules.
2. Editing a schedule updates the next run time and delivery settings.
3. Deleting a schedule removes it from the system and cancels future runs.

Business Logic:
- The dashboard retrieves all schedules for the logged-in user from the database.
- Edit and delete actions update or remove schedule records and trigger background job updates.
- All changes are logged for audit purposes.

Technical Context:
- Technology stack: Angular frontend, .NET Core backend, Azure SQL Database.
- API: RESTful endpoints for schedule management.
- Data formats: JSON for API communication.
- Security: Role-based access control, audit logging for all changes.

Non-Functional Requirements:
- Performance: Dashboard loads all schedules within 2 seconds.
- Availability: 99.9% uptime for scheduling dashboard.
- Security: Audit logs for all schedule changes.
- Scalability: Dashboard supports up to 1,000 schedules per user.
- Monitoring: Application Insights for dashboard usage and errors.