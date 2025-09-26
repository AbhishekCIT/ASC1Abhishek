# For User Story Number [2]

1. Objective
This user story enables travelers to book a selected flight by entering passenger details, selecting seats, and completing payment. The system must securely process bookings, prevent double booking, and generate booking confirmations and e-tickets. The goal is to provide a reliable, secure, and efficient booking experience.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth 2.0 secured endpoints)
  - BookingService (handles booking process)
  - PaymentGatewayIntegrationService (for payment processing)
  - SeatLockService (locks seats during booking)
  - NotificationService (sends confirmation via email/SMS)
  - ValidationService (for input validation)
  - LoggingService (for monitoring and analytics)

  2.2 API Details
  | Operation | REST Method | Type    | URL                          | Request (JSON)                                                                                                   | Response (JSON)                                                                                       |
  |-----------|-------------|---------|------------------------------|------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
  | Book      | POST        | Success | /api/bookings                | {"flightId":123,"passengers":[{"name":"John Doe","age":30}],"seats":["12A"],"contact":{"email":"john@example.com","phone":"1234567890"},"payment":{"method":"card","token":"tok_abc"}} | {"bookingRef":"XYZ123","status":"CONFIRMED","ticket":{"pdfUrl":"/tickets/XYZ123.pdf"}} |
  | Book      | POST        | Failure | /api/bookings                | {"flightId":123,"passengers":[],"seats":[],"contact":{},"payment":{}}                                     | {"error":"Validation failed: All passenger details are mandatory; Contact information invalid."}    |

  2.3 Exceptions
  | Exception Name                | Description                                            |
  |------------------------------|--------------------------------------------------------|
  | InvalidInputException         | Thrown when input validation fails                     |
  | SeatUnavailableException      | Thrown if selected seats are already booked            |
  | PaymentFailedException        | Thrown if payment authorization fails                  |
  | BookingConflictException      | Thrown if double booking detected                      |
  | DatabaseException             | Thrown on DB errors                                   |

3. Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class BookingController {
      +bookFlight(request)
    }
    class BookingService {
      +bookFlight(params)
    }
    class SeatLockService {
      +lockSeats(flightId, seats)
    }
    class PaymentGatewayIntegrationService {
      +processPayment(paymentDetails)
    }
    class NotificationService {
      +sendConfirmation(booking)
    }
    class ValidationService {
      +validateBookingParams(params)
    }
    class LoggingService {
      +logBooking(request, response)
    }
    BookingController --> BookingService
    BookingService --> SeatLockService
    BookingService --> PaymentGatewayIntegrationService
    BookingService --> NotificationService
    BookingService --> ValidationService
    BookingService --> LoggingService
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as BookingController
    participant S as BookingService
    participant V as ValidationService
    participant L as SeatLockService
    participant P as PaymentGatewayIntegrationService
    participant N as NotificationService
    participant G as LoggingService
    U->>C: POST /api/bookings
    C->>S: bookFlight(request)
    S->>V: validateBookingParams(params)
    V-->>S: ValidationResult
    S->>L: lockSeats(flightId, seats)
    L-->>S: SeatLockResult
    S->>P: processPayment(paymentDetails)
    P-->>S: PaymentResult
    S->>N: sendConfirmation(booking)
    N-->>S: NotificationResult
    S->>G: logBooking(request, response)
    S-->>C: bookingConfirmation
    C-->>U: Response
  ```

  3.3 Components
  | Component Name                | Description                                             | Existing/New |
  |------------------------------|---------------------------------------------------------|--------------|
  | BookingController             | Handles REST endpoints for booking                      | New          |
  | BookingService                | Orchestrates booking, validation, and integration logic | New          |
  | SeatLockService               | Locks seats during booking process                      | New          |
  | PaymentGatewayIntegrationService| Integrates with payment providers                     | Existing     |
  | NotificationService           | Sends booking confirmation and e-ticket                 | Existing     |
  | ValidationService             | Validates user input                                   | Existing     |
  | LoggingService                | Logs booking requests and responses                     | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName         | Validation                                      | Error Message                                   | ClassUsed            |
  |-------------------|-------------------------------------------------|-------------------------------------------------|----------------------|
  | passengers        | Not empty, all details mandatory                 | All passenger details are mandatory             | ValidationService    |
  | seats             | Not empty, <= available seats                    | Seat selection exceeds available seats           | SeatLockService      |
  | contact           | Valid email and phone                            | Contact information invalid                     | ValidationService    |
  | payment           | Authorized and completed                         | Payment authorization failed                    | PaymentGatewayIntegrationService |

4. Integrations
  | SystemToBeIntegrated | IntegratedFor              | IntegrationType |
  |----------------------|----------------------------|-----------------|
  | Payment Gateway      | Payment authorization      | API (REST/JSON) |
  | Notification Service | Confirmation, e-ticket     | API (REST/JSON) |

5. DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING ||--o{ PASSENGER : has
    BOOKING {
      int booking_id PK
      string booking_ref
      int flight_id FK
      string status
      datetime created_at
      string contact_email
      string contact_phone
      decimal total_amount
    }
    PASSENGER {
      int passenger_id PK
      int booking_id FK
      string name
      int age
      string seat_number
    }
  ```

  5.2 DB Validations
  - Ensure seat_number is unique per flight and booking.
  - All passenger and contact details are non-null.

6. Non-Functional Requirements
  6.1 Performance
  - Booking process must complete within 5 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth 2.0 for all API endpoints.
    6.2.2 Authorization
    - Only authenticated users can book flights.
    - PCI DSS compliance for payment data.

  6.3 Logging
    6.3.1 Application Logging
    - Log all booking requests (INFO), validation failures (WARN), errors (ERROR), and payment errors (ERROR).
    6.3.2 Audit Log
    - Log all payment transactions and booking status changes.

7. Dependencies
  - Payment gateway provider must be available and PCI DSS compliant.
  - Notification service must be available for confirmation delivery.

8. Assumptions
  - Payment provider supports real-time authorization.
  - Contact details provided by users are valid and reachable.
  - Seat map is up-to-date and accurate.
