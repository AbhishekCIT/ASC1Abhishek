EPIC Number: 3
User Story Number: 1
User Story Title: As a traveler, I want to book air transport tickets online, So that I can conveniently plan and purchase my travel.

User Story Description: Enable travelers to search for flights, compare options, and book tickets directly through the application. The feature should support multiple airlines, destinations, and allow users to select dates, times, and seat preferences. Payment integration and confirmation notifications are required.

Acceptance Criteria:
1. Users can search for flights by destination, date, and airline.
2. Users can view available flights and compare prices.
3. Users can book tickets and receive confirmation.
4. Payment is processed securely.

Validations:
1. Only valid travel dates and destinations are accepted.
2. Payment details are validated before processing.
3. User email is verified for confirmation notification.

Business Logic: 
- Search flights by querying airline APIs.
- Display results sorted by price, time, or airline.
- Booking logic reserves seat and generates ticket.
- Payment gateway integration for transaction.
- Send confirmation email upon successful booking.

Technical Context:
- Technology stack: .NET Core backend, React frontend.
- Airline APIs (REST/JSON), Payment API integration.
- Secure HTTPS for all transactions.
- Data stored in Azure SQL Database.

Non-Functional Requirements:
- Response time < 2 seconds for search and booking.
- 99.9% uptime SLA.
- PCI DSS compliance for payments.
- Scalable to handle peak travel seasons.
- Monitoring via Azure Application Insights.
