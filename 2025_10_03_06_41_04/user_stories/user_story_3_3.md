EPIC Number: 3
User Story Number: 3
User Story Title: As a customer, I want to view and manage my air transport bookings, so that I can make changes, cancellations, or request support as needed.

User Story Description: This user story covers the ability for customers to log in, view their active and past bookings, modify or cancel bookings, and contact customer support for assistance. The system should provide clear options and information about policies and fees.

Acceptance Criteria:
1. Users can log in and view a list of their bookings.
2. Users can select a booking to view details, modify, or cancel it.
3. Users can request support or submit queries related to their bookings.
4. System displays applicable policies, fees, and refund information.

Validations:
1. Only authenticated users can access their booking history.
2. Modifications and cancellations are allowed only as per airline policy (e.g., within allowed time window).
3. Support requests must be acknowledged and tracked.

Business Logic:
- Booking modifications and cancellations must check for eligibility and calculate applicable fees/refunds.
- Support requests must be assigned a unique ticket number and routed to the appropriate team.

Technical Context:
- Technology stack: React.js frontend, Node.js backend, integration with airline booking and support systems.
- Data formats: JSON for API communication.
- Security: User authentication (OAuth2), HTTPS, secure session management.

Non-Functional Requirements:
- User portal must be available 24/7.
- All changes to bookings must be processed within 3 seconds.
- Support queries must be acknowledged within 1 minute.
- System must log all user actions for auditing and compliance.