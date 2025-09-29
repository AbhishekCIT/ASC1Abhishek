EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to cancel my air transport booking and request a refund, So that I can manage changes in my travel plans easily.

User Story Description: This feature allows travelers to cancel their booked flights and request refunds based on the airline's cancellation policy. The system should calculate refund amounts, process the request, and update the booking status. Notifications should be sent regarding the cancellation and refund status.

Acceptance Criteria:
1. Users can view and select bookings eligible for cancellation.
2. Refund amount is calculated as per airline policy and displayed to the user.
3. Users can confirm cancellation and receive a notification.
4. Refund is processed and status updated in the system.

Validations:
1. Only eligible bookings can be cancelled.
2. Refund amount must be correctly calculated.
3. User must confirm cancellation before processing.

Business Logic:
- Check eligibility for cancellation based on fare rules and time before departure.
- Calculate refund after deducting applicable charges.
- Update booking status and initiate refund transaction.
- Notify user of cancellation and refund progress.

Technical Context:
- Technology stack: ReactJS frontend, Node.js backend, Azure SQL Database.
- APIs: Airline booking API, Payment/refund API, Notification API.
- Data formats: JSON.
- Security: HTTPS, user authentication, audit logging for cancellations.

Non-Functional Requirements:
- Refund processing time < 48 hours.
- 99.9% accuracy in refund calculation.
- Secure handling of financial transactions.
- Scalable to handle mass cancellations (e.g., during disruptions).
- Monitoring for failed or delayed refunds.
