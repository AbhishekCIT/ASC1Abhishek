EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to cancel my booked air transport ticket, So that I can manage changes in my travel plans.

User Story Description: This feature enables passengers to cancel their previously booked tickets through the application. The system must validate cancellation policies, process refunds if applicable, and update seat availability.

Acceptance Criteria:
1. Passenger can view and select tickets eligible for cancellation.
2. System displays cancellation policy and refund details.
3. Cancellation request is processed and confirmation is sent.

Validations:
1. Only tickets within cancellation window can be cancelled.
2. Refund amount is calculated as per policy.
3. Seat availability is updated after cancellation.

Business Logic:
- Check cancellation eligibility based on booking date and flight schedule.
- Calculate refund amount as per fare rules.
- Update booking status and seat inventory.
- Notify passenger via email/SMS.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), MongoDB (database)
- Integration with payment gateway for refunds
- API endpoints for cancellation and refund
- Data format: JSON
- Security: Authentication required for cancellation requests

Non-Functional Requirements:
- Refund processing time < 24 hours
- Secure handling of sensitive data
- High availability of cancellation service
- Audit logs for cancellation actions