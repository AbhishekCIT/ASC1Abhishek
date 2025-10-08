EPIC Number: 3
User Story Number: 1
User Story Title: As a customer, I want to book air transport for my goods, So that I can ensure timely delivery to my destination.

User Story Description: This feature allows customers to book air transport for their cargo through the application. The booking process should capture details such as origin, destination, cargo type, weight, dimensions, preferred dates, and special handling instructions. The system should validate inputs, provide available flight options, and allow customers to confirm and pay for their booking.

Acceptance Criteria:
1. Customers can enter all required booking details.
2. System displays available flight options based on customer inputs.
3. Customers can select a flight and confirm the booking.
4. Payment gateway integration is available for booking confirmation.
5. Booking confirmation and details are sent to the customer via email/SMS.

Validations:
1. Origin and destination must be valid airport codes.
2. Cargo weight and dimensions must be within allowable limits.
3. Preferred dates must be in the future.
4. Payment must be successfully processed before booking is confirmed.

Business Logic: 
- Validate booking inputs (airport codes, cargo specs, dates).
- Query available flights based on cargo and dates.
- Calculate pricing based on cargo type, weight, and route.
- Integrate with payment gateway for transaction.
- Generate booking reference and send confirmation.

Technical Context:
- Technology stack: .NET Core backend, React frontend, Azure SQL Database.
- API integration with airline flight databases and payment gateway (REST APIs, JSON format).
- Secure data transmission (HTTPS, OAuth2 for authentication).

Non-Functional Requirements:
- Response time for booking search <2 seconds.
- System availability 99.9%.
- PCI DSS compliance for payment processing.
- Scalability to handle 10,000 concurrent bookings.
- Audit logging for all booking activities.
