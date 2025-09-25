EPIC Number: 32
User Story Number: 2
User Story Title: As an end user, I want to view and manage my scheduled reports, So that I can keep track of and modify my report schedules as needed.

User Story Description: This user story enables end users to view a list of all their scheduled reports, including details such as frequency, next run time, recipients, and report type. Users should be able to edit, pause, resume, or delete schedules from a dashboard interface.

Acceptance Criteria:
1. Users can view a list of all scheduled reports.
2. Users can edit scheduling parameters for any report.
3. Users can pause or resume scheduled reports.
4. Users can delete scheduled reports.
5. Changes are reflected immediately in the schedule dashboard.

Validations:
1. Only the owner or authorized users can modify or delete a schedule.
2. Edits must pass the same validations as initial scheduling (frequency, time, recipients).
3. Deleted schedules are removed from the system and no longer trigger report generation.

Business Logic: The dashboard should fetch all schedules for the logged-in user. Editing a schedule updates the database and reschedules the report. Pausing disables the timer, resuming re-enables it. Deleting removes the schedule and cancels future report generation.

Technical Context: Technology stack: .NET Core backend, Angular frontend. Dashboard UI will call backend APIs to fetch, update, pause/resume, and delete schedules. Security: Role-based access control to ensure only authorized users can manage schedules.

Non-Functional Requirements:
- Performance: Dashboard should load all schedules within 2 seconds.
- Availability: Dashboard must be available 99.9% of the time.
- Security: Only authenticated users can access their schedules.
- Scalability: Support up to 10,000 schedules per user.
- Monitoring: All schedule changes should be logged for audit purposes.