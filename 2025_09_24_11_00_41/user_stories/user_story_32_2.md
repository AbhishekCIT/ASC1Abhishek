EPIC Number: 32
User Story Number: 2
User Story Title: As a report owner, I want to manage (view, edit, delete) my scheduled reports, so that I can keep my reporting schedules up to date and relevant.

User Story Description: Users should be able to access a dashboard listing all their scheduled reports. From this dashboard, they can view details, modify schedules, change recipients, or delete schedules as needed.

Acceptance Criteria:
1. Users can view a list of all scheduled reports they have created.
2. Users can edit the schedule, frequency, and recipients of any scheduled report.
3. Users can delete any scheduled report.
4. System prompts for confirmation before deleting a schedule.
5. Changes are reflected immediately and confirmed to the user.

Validations:
1. Only the owner or authorized users can edit or delete a schedule.
2. Editing a schedule must not create conflicts or invalid configurations.
3. Deletion is irreversible and must be confirmed by the user.

Business Logic:
- Fetch all schedules linked to the logged-in user from the database.
- Allow CRUD operations on schedules with appropriate permission checks.
- Audit all changes for compliance and traceability.

Technical Context:
- Angular frontend for dashboard UI.
- .NET Core API for schedule management.
- Azure SQL for storing schedules.
- Role-based access control via Azure AD.

Non-Functional Requirements:
- Dashboard must load within 3 seconds for up to 100 schedules.
- All actions must be logged for audit purposes.
- Secure access and data privacy enforced.
- High availability (99.9%).
