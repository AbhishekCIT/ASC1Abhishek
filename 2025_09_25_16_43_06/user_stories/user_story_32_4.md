EPIC Number: 32
User Story Number: 4
User Story Title: As an administrator, I want to manage user permissions for report scheduling, so that only authorized users can schedule, edit, or view reports and their schedules.

User Story Description: Administrators must be able to assign and revoke permissions for report scheduling features. Permissions should be role-based and support segregation of duties (e.g., scheduler, viewer, admin).

Acceptance Criteria:
1. Admins can assign roles to users (scheduler, viewer, admin).
2. Only users with the scheduler role can create or edit schedules.
3. Only users with the viewer role can view scheduled reports.
4. Only admins can manage permissions and templates.

Validations:
1. Role changes are logged in the audit log.
2. Users cannot access features outside their assigned role.
3. Permission changes take effect immediately.

Business Logic:
- Roles are mapped to feature access in the application.
- Permission checks are enforced on all relevant API endpoints and UI components.
- Role assignments are auditable.

Technical Context:
- Role-based access control implemented in .NET Core backend.
- User and role data stored in Azure AD or SQL Server.
- UI adapts based on user role.

Non-Functional Requirements:
- Permission checks must not add more than 100ms latency to API calls.
- All permission changes are logged and monitored.
- System supports integration with enterprise identity providers.
