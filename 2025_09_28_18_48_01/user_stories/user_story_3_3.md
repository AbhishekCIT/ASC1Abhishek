EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to manage my baggage online, So that I can pre-pay for checked bags and understand baggage policies before arriving at the airport.

User Story Description: This user story covers the ability for passengers to add, remove, or modify checked baggage options, view baggage fees, and pre-pay for baggage online, reducing wait times and confusion at the airport.

Acceptance Criteria:
1. Passengers can view baggage allowance and fees for their flight.
2. Passengers can add or remove checked bags before departure.
3. Payment for baggage fees can be completed online.
4. Updated baggage information is reflected in the booking and boarding pass.

Validations:
1. Baggage allowance and fees are displayed accurately based on fare class and route.
2. Baggage additions must not exceed airline or regulatory limits.
3. Payment must be confirmed before baggage is added to the booking.

Business Logic: The system calculates baggage fees based on route, fare class, and loyalty status, validates baggage limits, and updates the booking record upon successful payment.

Technical Context: Integration with airline baggage policy APIs, secure payment gateway, updates to booking database, and real-time synchronization with check-in and boarding systems.

Non-Functional Requirements: System must process baggage updates within 2 seconds, support 2,000 concurrent users, ensure PCI DSS compliance, and provide audit logs for all transactions.