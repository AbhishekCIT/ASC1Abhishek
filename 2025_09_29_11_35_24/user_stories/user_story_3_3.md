EPIC Number: 3
User Story Number: 3
User Story Title: As a traveler, I want to view, modify, or cancel my flight bookings, so that I can manage my travel plans as needed.

User Story Description: This feature enables users to access their booking history, view booking details, make changes (such as date or seat selection), or cancel bookings as per airline policy. Refunds or change fees should be calculated and displayed.

Acceptance Criteria:
1. User can view a list of all their bookings.
2. User can view detailed information for each booking.
3. User can modify booking details (date, seat, passenger info) if allowed.
4. User can cancel a booking and see refund/change fee details.
5. Confirmation email is sent for any change or cancellation.

Validations:
1. Only bookings within the allowed modification/cancellation window can be changed/cancelled.
2. Refunds and fees are calculated according to airline policy.
3. User authentication is required to access booking management.

Business Logic: The system checks airline rules for modification/cancellation eligibility and calculates any applicable fees or refunds. Updates are applied to the booking and notifications are sent.

Technical Context: Backend in Node.js/Express, data in PostgreSQL, integration with airline APIs for real-time updates, secure RESTful endpoints, JWT-based authentication.

Non-Functional Requirements: Booking management actions should complete within 4 seconds. All changes must be logged for audit. System must support 99.9% availability and secure access.