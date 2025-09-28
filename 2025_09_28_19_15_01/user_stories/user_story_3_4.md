EPIC Number: 3
User Story Number: 4
User Story Title: As a passenger, I want to manage my baggage online, So that I can prepay for extra luggage and track my baggage status.

User Story Description: This feature allows passengers to add and pay for extra baggage online, view baggage allowance, and track the status of their checked baggage. The system should integrate with airline baggage systems and provide real-time updates.

Acceptance Criteria:
1. Users can view baggage allowance for their flight/class.
2. Users can add extra baggage and pay online.
3. Users can track the status of checked baggage.
4. Users receive notifications if baggage is delayed or rerouted.

Validations:
1. Baggage allowance is validated against airline rules.
2. Payment for extra baggage must be processed securely.
3. Baggage tracking data must be accurate and timely.

Business Logic:
- Baggage allowance is determined by airline and class of service.
- Extra baggage fees are calculated based on weight/size and airline policy.
- Baggage tracking integrates with airport/airline systems for real-time status.

Technical Context:
- Technology stack: ReactJS (frontend), Node.js (backend), Azure SQL Database.
- Integration with airline and airport baggage APIs.
- Payment gateway integration for extra baggage fees.
- Secure endpoints, GDPR compliance for personal data.

Non-Functional Requirements:
- System should support 1,000 concurrent baggage transactions.
- Real-time baggage status updates within 1 minute.
- 99.9% uptime.
- Monitoring for baggage tracking failures.