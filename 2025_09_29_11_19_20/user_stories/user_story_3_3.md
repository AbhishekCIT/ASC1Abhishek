EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to manage my baggage information online, so that I can ensure my baggage is within limits and prepay for extra baggage if needed.

User Story Description: This feature allows passengers to view baggage allowance, add baggage details, calculate additional fees for excess baggage, and prepay for extra baggage online. The system should provide clear guidelines and real-time calculation of fees.

Acceptance Criteria:
1. Passengers can view standard baggage allowance for their ticket.
2. Users can enter baggage details and see if they exceed the allowance.
3. System calculates and displays additional fees for excess baggage.
4. Users can prepay for extra baggage and receive confirmation.

Validations:
1. Validate baggage weight and dimensions against airline policy.
2. Ensure payment is processed before confirming extra baggage.
3. Confirm that baggage details are linked to the correct booking.

Business Logic: The system retrieves baggage allowance from airline data, checks entered details, calculates fees based on airline rules, and processes payment for excess baggage. Confirmation is sent to the user and airline.

Technical Context: Integration with airline baggage APIs, backend in Node.js, frontend in React, payment gateway integration, secure data handling, data stored in PostgreSQL.

Non-Functional Requirements: Fee calculation must be accurate and instant, payment processing must meet PCI DSS standards, system must support 99.9% uptime, and provide audit logs for all transactions.