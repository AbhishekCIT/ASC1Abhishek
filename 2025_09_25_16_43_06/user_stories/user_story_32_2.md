EPIC Number: 32
User Story Number: 2
User Story Title: As a report administrator, I want to manage report templates for scheduling, so that users can select from standardized, approved report formats.

User Story Description: Report administrators should be able to create, update, and deactivate report templates that are available for scheduling. This ensures consistency and compliance with organizational standards.

Acceptance Criteria:
1. Administrators can create new report templates with predefined filters and layouts.
2. Administrators can update existing templates.
3. Administrators can deactivate templates, making them unavailable for scheduling.
4. Users can only schedule reports using active templates.

Validations:
1. Template names must be unique.
2. Templates must pass validation for required fields and data sources.
3. Deactivated templates must not appear in the scheduling UI.

Business Logic:
- Templates are stored in a central repository.
- Only active templates are exposed via the scheduling interface.
- Deactivation is soft-delete to preserve historical data.

Technical Context:
- Templates stored in SQL Server with status flags.
- Admin UI in Angular with role-based access control.
- API endpoints for CRUD operations on templates.

Non-Functional Requirements:
- Template changes must propagate to all users within 1 minute.
- Only authorized users can manage templates.
- Audit logs for all template changes.
