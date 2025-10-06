EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to manage my baggage information online, So that I can ensure my baggage is checked in and tracked efficiently.

User Story Description: This user story allows passengers to add, modify, and track their baggage information online. The system should provide details on baggage allowance, excess baggage fees, and real-time tracking of checked-in baggage.

Acceptance Criteria:
1. Passengers can add baggage details during booking or check-in.
2. System displays baggage allowance and calculates excess fees.
3. Passengers can track the status/location of checked-in baggage.
4. Notifications are sent if baggage is delayed or misplaced.

Validations:
1. Baggage weight and dimensions must comply with airline policies.
2. Excess baggage fees are calculated accurately.
3. Baggage tracking data is updated in real-time.

Business Logic:
- System validates baggage details against airline policies.
- Calculates and processes excess baggage fees.
- Integrates with airport baggage tracking systems for real-time updates.

Technical Context:
- Technology stack: ReactJS, Node.js, Azure SQL Database.
- Integration with airline/airport baggage APIs.
- Secure payment gateway for excess fees.
- Data format: JSON.
- Security: HTTPS, PCI DSS compliance, user authentication.

Non-Functional Requirements:
- Real-time baggage tracking updates.
- 99.9% uptime for baggage service.
- Response time < 2 seconds for baggage queries.
- Monitoring for baggage tracking API failures.
