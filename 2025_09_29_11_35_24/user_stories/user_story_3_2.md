EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to book a selected flight, so that I can reserve my seat and complete my travel planning.

User Story Description: This feature allows users to book a flight after selecting from the search results. The user provides passenger details, selects seats (if available), and confirms the booking. The system should generate a booking reference and send a confirmation email.

Acceptance Criteria:
1. User can select a flight and proceed to booking.
2. User enters passenger details and selects seats if available.
3. System validates all mandatory fields.
4. Booking reference is generated upon successful booking.
5. Confirmation email is sent to the user.

Validations:
1. All passenger details must be provided and valid.
2. Seat selection must not exceed available seats.
3. Payment must be successful before booking is confirmed.

Business Logic: On booking, the system reserves seats and generates a unique booking reference. It triggers payment processing and, on success, updates the booking status and sends a confirmation email.

Technical Context: Backend with Node.js/Express, payment integration via Stripe/PayPal, email service integration (e.g., SendGrid). Data stored in PostgreSQL, API endpoints secured with OAuth2.

Non-Functional Requirements: Booking process should complete within 5 seconds. System must handle up to 1,000 bookings per minute. All sensitive data must be encrypted at rest and in transit. Booking failures must be logged and monitored.