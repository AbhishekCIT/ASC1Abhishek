EPIC Number: 3
User Story Number: 2
User Story Title: As a passenger, I want to manage my baggage information online, So that I can ensure my baggage is processed smoothly.

User Story Description: This user story covers the ability for passengers to add, modify, and view their baggage information when booking or managing their air transport tickets. The system should provide clear information about baggage allowances, fees, and restrictions.

Acceptance Criteria:
1. Passengers can add or update baggage details during booking or later via their account.
2. The system displays baggage allowance and any applicable fees based on the ticket class and route.
3. Passengers receive confirmation of their baggage registration.

Validations:
1. Baggage weight and size must not exceed airline limits.
2. Additional fees are calculated and displayed for excess baggage.
3. Only valid baggage types (checked, carry-on) are accepted.

Business Logic: The system calculates baggage allowances based on ticket class and airline policy, applies fees for excess or special baggage, and updates the passenger’s booking record accordingly.

Technical Context: The feature will use backend APIs to retrieve and update baggage information, integrate with airline systems for policy checks, and process additional payments if required. Data is stored securely in the booking database.

Non-Functional Requirements: The system should update baggage information in real-time, ensure data consistency, and provide clear error messages for invalid entries.