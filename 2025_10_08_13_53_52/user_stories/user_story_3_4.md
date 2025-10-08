EPIC Number: 3
User Story Number: 4
User Story Title: As a customer, I want to cancel my air transport booking and request a refund, So that I have flexibility in managing my shipments.

User Story Description: This feature allows customers to cancel their air transport bookings through the application. The system should display cancellation policies, calculate any applicable fees, and process refunds through the original payment method. Customers should receive confirmation of cancellation and refund status.

Acceptance Criteria:
1. Customers can view and initiate cancellation for eligible bookings.
2. System displays applicable cancellation fees and refund amount.
3. Refund is processed through the original payment method.
4. Cancellation and refund confirmation is sent to the customer.

Validations:
1. Only eligible bookings can be cancelled (e.g., not already departed).
2. Cancellation fees are calculated according to policy.
3. Refunds are processed securely and accurately.

Business Logic:
- Check booking eligibility for cancellation.
- Calculate cancellation fees and refund amount based on booking terms.
- Integrate with payment gateway for refund processing.
- Update booking status and notify customer.

Technical Context:
- Technology stack: .NET Core backend, React frontend, Azure SQL Database.
- Integration with payment gateway (REST APIs, JSON format).
- Secure handling of financial transactions (PCI DSS compliance).

Non-Functional Requirements:
- Refund processing time <24 hours.
- System availability 99.9%.
- Audit logging for all cancellation and refund transactions.
- Scalability to support 1,000 cancellations per day.
- Monitoring for failed refund transactions.
