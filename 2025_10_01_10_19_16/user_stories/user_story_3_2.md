EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to view and manage my air transport bookings, so that I can check my itinerary, make changes, or cancel if needed.

User Story Description: This user story covers the ability for users to access their bookings, view details, modify dates or passenger information, and cancel bookings if required. The system should provide clear options and reflect changes in real-time.

Acceptance Criteria:
1. Users can log in and view a list of their current and past bookings.
2. Users can select a booking to view detailed itinerary and ticket information.
3. Users can modify booking details (where permitted by airline policy).
4. Users can cancel bookings and receive confirmation and refund details.

Validations:
1. Only authenticated users can access their bookings.
2. Modification and cancellation options are enabled only if allowed by fare rules.
3. Refund calculations are accurate as per airline policy.

Business Logic:
- Booking retrieval based on user authentication.
- Modification logic must check fare rules and seat availability.
- Cancellation and refund logic must follow airline and payment provider rules.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure SQL Database.
- APIs for booking management, modification, and cancellation.
- Data formats: JSON for API communication.
- Security: HTTPS, user authentication via OAuth2.

Non-Functional Requirements:
- System should update booking status in real-time.
- Availability: 99.9% uptime.
- Response time for booking management: <2 seconds.
- Audit logs for all booking modifications and cancellations.
