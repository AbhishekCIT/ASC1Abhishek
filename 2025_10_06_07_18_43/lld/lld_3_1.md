# For User Story Number [1]
\
1. Objective
\
This requirement enables travelers to search, compare, and book air transport tickets online. The system ensures a seamless and secure booking process, providing users with confirmation details upon successful booking. It aims to improve convenience and reliability for trip planning.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - User Authentication Service
\
    - Flight Search Service
\
    - Booking Service
\
    - Payment Service
\
    - Notification Service
\

\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Search Flights | GET | Success/Failure | /api/flights/search | {"date": "2025-10-10", "destination": "NYC", "passengerCount": 2} | {"flights": [{"flightId": "F123", "price": 320, "time": "10:00"}]} |
\
| Book Flight | POST | Success/Failure | /api/bookings | {"flightId": "F123", "userId": "U456", "passengerDetails": [{...}], "paymentInfo": {...}} | {"bookingId": "B789", "confirmationNumber": "CN001", "status": "CONFIRMED"} |
\
| Get Booking Confirmation | GET | Success/Failure | /api/bookings/{bookingId}/confirmation | N/A | {"bookingId": "B789", "confirmationNumber": "CN001", "details": {...}} |
\

\
  2.3 Exceptions
\
    - FlightNotFoundException: Returned if no flights match search criteria.
\
    - PaymentValidationException: Returned if payment info is invalid.
\
    - FlightUnavailableException: Returned if selected flight is not available during booking.
\
    - BookingServiceException: General booking errors.
\

\
3 Functional Design
\
  3.1 Class Diagram
\
```mermaid
\
classDiagram
\
  User <|-- BookingController
\
  BookingController --> FlightSearchService
\
  BookingController --> BookingService
\
  BookingService --> PaymentService
\
  BookingService --> NotificationService
\
  BookingService --> AirlineAPIClient
\
  FlightSearchService --> AirlineAPIClient
\
  BookingService --> AzureSQLRepository
\
```
\

\
  3.2 UML Sequence Diagram
\
```mermaid
\
sequenceDiagram
\
  participant User
\
  participant BookingController
\
  participant FlightSearchService
\
  participant BookingService
\
  participant PaymentService
\
  participant NotificationService
\
  participant AirlineAPIClient
\
  participant AzureSQLRepository
\
  User->>BookingController: Search Flights
\
  BookingController->>FlightSearchService: searchFlights(criteria)
\
  FlightSearchService->>AirlineAPIClient: getFlights(criteria)
\
  AirlineAPIClient-->>FlightSearchService: flightsList
\
  FlightSearchService-->>BookingController: flightsList
\
  BookingController-->>User: Show flights
\
  User->>BookingController: Book Flight
\
  BookingController->>BookingService: bookFlight(details)
\
  BookingService->>PaymentService: validatePayment(paymentInfo)
\
  PaymentService-->>BookingService: paymentStatus
\
  BookingService->>AirlineAPIClient: reserveSeat(flightId)
\
  AirlineAPIClient-->>BookingService: seatReserved
\
  BookingService->>AzureSQLRepository: saveBooking(booking)
\
  AzureSQLRepository-->>BookingService: bookingId
\
  BookingService->>NotificationService: sendConfirmation(user, booking)
\
  NotificationService-->>User: confirmation details
\
```
\

\
  3.3 Components
\
| Component Name | Description | Existing/New |
\
|----------------|-------------|--------------|
\
| BookingController | Handles API requests for booking and searching flights | New |
\
| FlightSearchService | Business logic for searching flights | New |
\
| BookingService | Business logic for booking flights | New |
\
| PaymentService | Validates and processes payments | Existing |
\
| NotificationService | Sends booking confirmations | Existing |
\
| AirlineAPIClient | Integrates with airline APIs | New |
\
| AzureSQLRepository | Persists booking data | Existing |
\

\
  3.4 Service Layer Logic and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|-----------|---------------|-----------|
\
| date | Must not be null | "Date is required" | FlightSearchService |
\
| destination | Must not be null | "Destination is required" | FlightSearchService |
\
| passengerCount | Must be > 0 | "Passenger count required" | FlightSearchService |
\
| paymentInfo | PCI DSS validation | "Invalid payment info" | PaymentService |
\
| flightId | Must be available | "Flight not available" | BookingService |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|--------------|-----------------|
\
| Airline APIs | Real-time flight data, seat reservation | API |
\
| Payment Gateway | Payment processing | API |
\
| Notification Service | Confirmation delivery | API |
\

\
5 DB Details
\
  5.1 ER Model
\
```mermaid
\
erDiagram
\
  USER ||--o{ BOOKING : has
\
  BOOKING }|--|| FLIGHT : books
\
  BOOKING }|--|| PAYMENT : pays
\
  FLIGHT ||--o{ AIRLINE : operated_by
\
  USER {
\
    string userId
\
    string name
\
    string email
\
  }
\
  FLIGHT {
\
    string flightId
\
    string airlineId
\
    string destination
\
    date date
\
    int availableSeats
\
    float price
\
  }
\
  BOOKING {
\
    string bookingId
\
    string userId
\
    string flightId
\
    string confirmationNumber
\
    datetime bookedAt
\
    string status
\
  }
\
  PAYMENT {
\
    string paymentId
\
    string bookingId
\
    string paymentStatus
\
    float amount
\
  }
\
  AIRLINE {
\
    string airlineId
\
    string name
\
  }
\
```
\

\
  5.2 DB Validations
\
    - Booking must reference a valid flight and user.
\
    - Payment must reference a valid booking.
\
    - Flight seat availability must be checked before booking.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Handle 1000 concurrent users.
\
    - Booking confirmation delivered within 5 seconds.
\
    - Caching for flight search results at API layer.
\

\
  6.2 Security
\
    6.2.1 Authentication
\
      - User authentication required for booking (OAuth2/JWT).
\
      - IAM integration for user management.
\
    6.2.2 Authorization
\
      - Role-based access for booking endpoints (traveler, admin).
\
      - Only authenticated users can book flights.
\

\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API request/response payloads.
\
      - INFO: Successful bookings, searches.
\
      - ERROR: Payment failures, booking errors.
\
      - WARN: Flight availability issues.
\
    6.3.2 Audit Log
\
      - Log booking creation, payment events, and confirmation delivery with user and timestamp.
\

\
7 Dependencies
\
    - Airline APIs for flight data and seat reservation.
\
    - Payment gateway for payment processing.
\
    - Notification service for confirmation delivery.
\
    - Azure SQL Database for persistence.
\

\
8 Assumptions
\
    - All airline APIs support real-time data and seat reservation.
\
    - Payment gateway is PCI DSS compliant.
\
    - Notification service supports email, SMS, and in-app alerts.
\
    - User authentication and authorization are managed via existing IAM system.