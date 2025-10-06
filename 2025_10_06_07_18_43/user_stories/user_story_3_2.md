EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to view and manage my air transport bookings, So that I can make changes or cancellations as needed.

User Story Description: This feature enables users to access their existing air transport bookings, view details, and perform actions such as modifying passenger information, changing flight dates, or cancelling bookings with applicable refund policies.

Acceptance Criteria:
1. Users can view a list of all their active and past bookings.
2. Users can select a booking to see detailed information.
3. Users can initiate changes or cancellations as per airline policies.
4. Users receive notifications for any changes or cancellations made.

Validations:
1. Only authenticated users can access their bookings.
2. Changes/cancellations must comply with airline rules and deadlines.
3. Refund calculations must be accurate based on policy.

Business Logic:
- Retrieve bookings linked to the user account.
- Implement rules for allowable changes and cancellations.
- Calculate and process refunds or additional charges as per airline policy.

Technical Context:
- Technology stack: React (frontend), Node.js (backend), Azure SQL Database.
- APIs: Airline booking management APIs.
- Data formats: JSON for API communication.
- Security: User authentication via OAuth2, HTTPS for all transactions.

Non-Functional Requirements:
- System should allow changes/cancellations to be processed within 10 seconds.
- 99.9% uptime for booking management service.
- All user actions logged for audit purposes.
- Real-time notifications via email and in-app alerts.