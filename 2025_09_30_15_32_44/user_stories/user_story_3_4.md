EPIC Number: 3
User Story Number: 4
User Story Title: As an airline administrator, I want to manage flight schedules and seat inventory, So that I can optimize capacity and ensure accurate availability for bookings.

User Story Description: This feature allows airline staff to create, update, and manage flight schedules, routes, and seat inventory. Administrators can adjust flight timings, add or remove flights, and manage seat allocations in real-time, ensuring that the booking system always reflects current availability.

Acceptance Criteria:
1. Admins can create new flights with schedules, routes, and seat maps.
2. Admins can update or cancel existing flights and adjust seat inventory.
3. Changes are reflected immediately in the booking portal.
4. System prevents overbooking and ensures accurate seat counts.
5. Audit logs are maintained for all administrative actions.

Validations:
1. Only authenticated admins can access flight management features.
2. Flight times and routes must be valid and not conflict with existing schedules.
3. Seat inventory changes cannot exceed aircraft capacity.

Business Logic:
- Flight creation and modification logic checks for conflicts and validates data.
- Seat inventory management prevents overbooking and tracks allocations.
- All changes are logged for compliance and audit.

Technical Context:
- Technology stack: ReactJS (admin frontend), Node.js (backend), Azure SQL Database.
- APIs: Internal APIs for schedule and inventory management.
- Security: Role-based access control, HTTPS, audit logging.

Non-Functional Requirements:
- Changes must propagate to booking system within 30 seconds.
- 99.9% uptime SLA.
- System must support 100+ concurrent admin users.
- All admin actions logged and available for compliance reporting.