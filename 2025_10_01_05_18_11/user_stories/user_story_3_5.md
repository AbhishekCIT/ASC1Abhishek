EPIC Number: 3
User Story Number: 5
User Story Title: As a traveler, I want to view and manage my booked tickets, So that I can access my travel details and make changes if needed

User Story Description: This feature allows users to view all their booked tickets, see details such as flight number, departure time, seat assignment, and booking reference. Users should also be able to cancel or modify bookings as per airline policies.

Acceptance Criteria:
1. Users can view a list of all their booked tickets.
2. Users can see detailed information for each ticket.
3. Users can cancel or modify bookings if allowed by policy.
4. Users receive confirmation for any changes made.

Validations:
1. Only authenticated users can access their tickets.
2. Modifications and cancellations must comply with airline rules.
3. All changes must be logged for auditing.

Business Logic:
- Retrieve ticket data for the logged-in user from the database.
- Check airline policy before allowing modifications or cancellations.
- Update booking status and notify user upon changes.

Technical Context:
- Technology stack: React frontend, Node.js backend, airline API integration.
- Data formats: JSON for API communication.
- Security: User authentication (OAuth2), secure data storage, GDPR compliance.

Non-Functional Requirements:
- Ticket information must load within 2 seconds.
- System must support 5,000 concurrent users viewing tickets.
- All ticket access and changes must be logged for compliance.