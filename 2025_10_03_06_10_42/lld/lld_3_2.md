# For User Story Number [2]
\
1. Objective
\
Enable travelers to book and pay for their selected air transport option securely and conveniently. Support multiple payment methods, seat selection, and ensure confirmation via email and platform. Guarantee robust validation, error handling, and secure transaction processing.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - BookingService (handles booking logic)
\
    - PaymentService (integrates with payment gateways)
\
    - SeatLockService (locks seats during booking)
\
    - EmailService (sends confirmation)
\
    - ErrorHandler (handles errors)
\

\
  2.2 API Details
\
| Operation | REST Method | Type        | URL                        | Request (JSON) | Response (JSON) |
\
|-----------|-------------|-------------|----------------------------|----------------|-----------------|
\
| Book      | POST        | Success     | /api/air/book              | {"flightId": "123", "passengerDetails": [{"name": "John Doe", "dob": "1990-01-01", "email": "john@example.com"}], "seatSelection": ["12A"], "paymentMethod": "credit_card", "paymentInfo": {"cardNumber": "****", "expiry": "12/26"}} | {"bookingReference": "ABC123", "status": "CONFIRMED", "receipt": {...}, "itinerary": {...}} |
\
| Book      | POST        | Failure     | /api/air/book              | {"flightId": "123", "passengerDetails": [], "paymentMethod": "credit_card", "paymentInfo": {"cardNumber": "****", "expiry": "12/26"}} | {"error": "Passenger details are required."} |
\
| Payment   | POST        | Failure     | /api/air/payment           | {"bookingReference": "ABC123", "paymentInfo": {"cardNumber": "****", "expiry": "12/26"}} | {"error": "Payment failed. Please retry."} |
\

\
  2.3 Exceptions
\
    - InvalidPassengerException: Thrown for missing/invalid passenger info.
\
    - PaymentFailedException: Thrown for failed payment transactions.
\
    - SeatUnavailableException: Thrown if selected seat is already booked.
\
    - ValidationException: Generic validation errors.
\

\
3. Functional Design
\
  3.1 Class Diagram
\
```mermaid
\
classDiagram
\
    class BookingController {
\
        +bookFlight(request)
\
    }
\
    class BookingService {
\
        +createBooking(details)
\
    }
\
    class PaymentService {
\
        +processPayment(paymentInfo)
\
    }
\
    class SeatLockService {
\
        +lockSeat(flightId, seat)
\
    }
\
    class EmailService {
\
        +sendConfirmation(email, details)
\
    }
\
    class ErrorHandler {
\
        +handleError(error)
\
    }
\
    BookingController --> BookingService
\
    BookingService --> PaymentService
\
    BookingService --> SeatLockService
\
    BookingService --> EmailService
\
    BookingController --> ErrorHandler
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
    participant U as User
\
    participant BC as BookingController
\
    participant BS as BookingService
\
    participant SLS as SeatLockService
\
    participant PS as PaymentService
\
    participant ES as EmailService
\
    participant EH as ErrorHandler
\
    U->>BC: Submit booking request
\
    BC->>BS: Validate and create booking
\
    BS->>SLS: Lock selected seat(s)
\
    SLS-->>BS: Seat lock result
\
    BS->>PS: Process payment
\
    PS-->>BS: Payment result
\
    BS->>ES: Send confirmation email
\
    ES-->>U: Confirmation email
\
    BS-->>BC: Booking result
\
    BC-->>U: Return booking confirmation
\
    Note over BC,EH: On error, BC->>EH: Handle error
\
    EH-->>U: Return error message
\
```
\

\
  3.3 Components
\
| Component Name        | Description                                            | Existing/New |
\
|----------------------|--------------------------------------------------------|--------------|
\
| BookingController    | Handles booking API requests                           | New          |
\
| BookingService       | Handles booking creation and logic                     | New          |
\
| PaymentService       | Integrates with payment gateways                       | New          |
\
| SeatLockService      | Locks seats during booking process                     | New          |
\
| EmailService         | Sends confirmation emails                              | Existing     |
\
| ErrorHandler         | Formats and returns error responses                    | Existing     |
\

\
  3.4 Service Layer Logic & Validations
\
| FieldName         | Validation                           | Error Message                       | ClassUsed           |
\
|-------------------|--------------------------------------|-------------------------------------|---------------------|
\
| passengerDetails  | Not empty, valid fields              | Passenger details are required.     | BookingService      |
\
| paymentInfo       | Valid payment format                  | Invalid payment information.        | PaymentService      |
\
| seatSelection     | Seat availability check               | Selected seat is unavailable.       | SeatLockService     |
\
| flightId          | Must exist and be available           | Flight not found or unavailable.    | BookingService      |
\

\
4. Integrations
\
| SystemToBeIntegrated | IntegratedFor           | IntegrationType |
\
|---------------------|------------------------|-----------------|
\
| Stripe API          | Payment processing      | API             |
\
| PayPal API          | Payment processing      | API             |
\
| Email Gateway       | Confirmation emails     | API             |
\

\
5. DB Details
\
  5.1 ER Model
\
```mermaid
\
erDiagram
\
    BOOKING {
\
        id PK
\
        flight_id
\
        user_id
\
        booking_reference
\
        status
\
        created_at
\
        updated_at
\
    }
\
    PASSENGER {
\
        id PK
\
        booking_id FK
\
        name
\
        dob
\
        email
\
    }
\
    PAYMENT {
\
        id PK
\
        booking_id FK
\
        payment_method
\
        amount
\
        status
\
        transaction_id
\
        created_at
\
    }
\
    SEAT {
\
        id PK
\
        flight_id FK
\
        seat_number
\
        status
\
    }
\
    BOOKING ||--o| PASSENGER: has
\
    BOOKING ||--o| PAYMENT: has
\
    FLIGHT_OPTIONS ||--o| SEAT: contains
\
```
\

\
  5.2 DB Validations
\
    - Ensure booking reference is unique.
\
    - Payment record must exist for confirmed bookings.
\
    - Seat status updated upon booking.
\

\
6. Non-Functional Requirements
\
  6.1 Performance
\
    - Payment processing time <3 seconds.
\
    - Scalable booking engine for concurrent users.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - Secure user authentication for booking.
\
    6.2.2 Authorization
\
      - Only authenticated users can book flights.
\
      - PCI DSS compliance for payment data.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: Payment API request/response
\
      - INFO: Successful bookings
\
      - ERROR: Payment failures, validation errors
\
      - WARN: Seat lock failures
\
    6.3.2 Audit Log
\
      - Log all booking and payment transactions with user ID and timestamp
\

\
7. Dependencies
\
    - Payment gateway APIs (Stripe, PayPal)
\
    - Email gateway
\
    - PostgreSQL database
\

\
8. Assumptions
\
    - Payment gateways support REST APIs and return JSON.
\
    - Seat locking mechanism prevents double booking.
\
    - Email gateway is reliable for confirmation delivery.