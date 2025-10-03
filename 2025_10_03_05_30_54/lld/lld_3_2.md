# For User Story Number [2]
\
1. Objective
\
Enable travelers to book a selected air transport option by entering passenger details, selecting seats, reviewing fare breakdown, and confirming the booking. Ensure secure payment processing and seat reservation. Generate and send booking confirmation to the user.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - BookingService (core booking logic)
\
    - PaymentGatewayClient (integration with payment providers)
\
    - SeatInventoryService (seat reservation)
\
    - BookingConfirmationService (confirmation generation/email)
\
    - ValidationUtil (input validation)
\
    - AuditLogger (booking audit trail)
\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Book Flight | POST | Success | /api/bookings | { "flightId": "DL100", "passengerInfo": [{"name": "John Doe", "age": 32, "passport": "X1234567"}], "seatSelection": ["12A"], "payment": {"cardNumber": "****", "exp": "12/27", "cvv": "***"} } | { "bookingRef": "ABC123", "status": "CONFIRMED", "details": {"flightId": "DL100", "seats": ["12A"], "amount": 500 }, "message": "Booking confirmed. Confirmation sent to email." } |
\
| Book Flight | POST | Failure | /api/bookings | { ... } | { "error": "Seat not available." } |
\
  2.3 Exceptions
\
    - InvalidInputException: For invalid or missing fields
\
    - PaymentFailedException: Payment authorization failed
\
    - SeatUnavailableException: Selected seat already booked
\
    - BookingTimeoutException: Booking exceeds 5 seconds
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
  class BookingController {
\
    +bookFlight()
\
  }
\
  class BookingService {
\
    +bookFlight()
\
  }
\
  class PaymentGatewayClient {
\
    +processPayment()
\
  }
\
  class SeatInventoryService {
\
    +reserveSeats()
\
  }
\
  class BookingConfirmationService {
\
    +generateConfirmation()
\
    +sendEmail()
\
  }
\
  class ValidationUtil {
\
    +validateBookingRequest()
\
  }
\
  class AuditLogger {
\
    +logBooking()
\
  }
\
  BookingController --> BookingService
\
  BookingService --> ValidationUtil
\
  BookingService --> SeatInventoryService
\
  BookingService --> PaymentGatewayClient
\
  BookingService --> BookingConfirmationService
\
  BookingService --> AuditLogger
\
```
\
  3.2 UML Sequence Diagram
\
```mermaid
\
sequenceDiagram
\
  participant U as User
\
  participant C as BookingController
\
  participant S as BookingService
\
  participant V as ValidationUtil
\
  participant I as SeatInventoryService
\
  participant P as PaymentGatewayClient
\
  participant B as BookingConfirmationService
\
  participant A as AuditLogger
\
  U->>C: POST /api/bookings
\
  C->>S: bookFlight(request)
\
  S->>V: validateBookingRequest(request)
\
  V-->>S: validationResult
\
  S->>I: reserveSeats(flightId, seats)
\
  I-->>S: seatStatus
\
  S->>P: processPayment(paymentInfo)
\
  P-->>S: paymentStatus
\
  S->>B: generateConfirmation(booking)
\
  B-->>S: confirmation
\
  S->>B: sendEmail(user, confirmation)
\
  S->>A: logBooking(booking)
\
  S-->>C: bookingResponse
\
  C-->>U: Response
\
```
\
  3.3 Components
\
| Component Name | Description | Existing/New |
\
|----------------|-------------|--------------|
\
| BookingController | REST endpoint for booking | New |
\
| BookingService | Business logic for booking | New |
\
| PaymentGatewayClient | Payment integration | New |
\
| SeatInventoryService | Manages seat reservation | New |
\
| BookingConfirmationService | Generates/sends confirmation | New |
\
| ValidationUtil | Validates booking input | New |
\
| AuditLogger | Logs booking actions | New |
\
  3.4 Service Layer Logic as per the user story requirement and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|------------|--------------|-----------|
\
| passengerInfo | All details valid | Invalid passenger details. | ValidationUtil |
\
| payment | Valid and authorized | Payment failed. | PaymentGatewayClient |
\
| seatSelection | Seat available | Seat not available. | SeatInventoryService |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|---------------|-----------------|
\
| Stripe/PayPal | Payment processing | API |
\
| Azure SQL Database | Booking storage | API/DB |
\
| Email Gateway | Confirmation email | API |
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
  BOOKING ||--o{ PASSENGER : contains
\
  BOOKING ||--o{ SEAT_RESERVATION : reserves
\
  BOOKING {
\
    string bookingRef PK
\
    string userId
\
    string flightId
\
    decimal amount
\
    string status
\
    datetime bookingTime
\
  }
\
  PASSENGER {
\
    string id PK
\
    string bookingRef FK
\
    string name
\
    int age
\
    string passport
\
  }
\
  SEAT_RESERVATION {
\
    string id PK
\
    string bookingRef FK
\
    string seatNumber
\
    string status
\
  }
\
```
\
  5.2 DB Validations
\
    - Ensure all passenger and payment fields are valid before insert.
\
    - Check seat availability before reservation.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Booking completes within 5 seconds.
\
    - Use async email sending to reduce response time.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 for user authentication.
\
    6.2.2 Authorization
\
      - Only authenticated users can book.
\
      - PCI DSS compliance for payment.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API requests/responses, payment attempts.
\
      - INFO: Successful bookings, seat reservations.
\
      - WARN: Payment retries, seat conflicts.
\
      - ERROR: Validation failures, payment errors.
\
    6.3.2 Audit Log
\
      - Log all booking actions with user and timestamp.
\

\
7 Dependencies
\
  - Payment gateways (Stripe, PayPal)
\
  - Email gateway
\
  - Azure SQL Database
\

\
8 Assumptions
\
  - Payment gateways are available and performant.
\
  - Seat inventory is up-to-date and consistent.
\
  - User authentication is handled before booking API is called.