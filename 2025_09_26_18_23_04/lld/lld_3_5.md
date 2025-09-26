# For User Story Number [5]

1. Objective
This user story enables travelers to cancel their flight bookings and request a refund if eligible. The system must display cancellation policies, calculate refund amounts, process refunds securely, and notify users of the status. The goal is to provide a transparent, secure, and efficient cancellation and refund experience.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth 2.0 secured endpoints)
  - CancellationService (handles cancellation and refund logic)
  - BookingService (retrieves and updates booking status)
  - PaymentGatewayIntegrationService (for refund processing)
  - NotificationService (sends cancellation/refund notifications)
  - ValidationService (for input and eligibility validation)
  - LoggingService (for monitoring and analytics)

  2.2 API Details
  | Operation | REST Method | Type    | URL                          | Request (JSON)                                                                                                   | Response (JSON)                                                                                       |
  |-----------|-------------|---------|------------------------------|------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
  | Cancel    | POST        | Success | /api/bookings/cancel         | {"bookingRef":"XYZ123","lastName":"Doe","reason":"Personal"}                                            | {"status":"CANCELLED","refundAmount":200.00,"message":"Refund initiated. Confirmation sent."} |
  | Cancel    | POST        | Failure | /api/bookings/cancel         | {"bookingRef":"INVALID","lastName":"Doe","reason":"Personal"}                                          | {"error":"Validation failed: Booking not eligible for cancellation or refund."}                   |

  2.3 Exceptions
  | Exception Name                | Description                                            |
  |------------------------------|--------------------------------------------------------|
  | InvalidInputException         | Thrown when input validation fails                     |
  | CancellationWindowException   | Thrown if booking is outside allowed cancellation window|
  | RefundCalculationException    | Thrown if refund calculation fails                     |
  | PaymentGatewayException       | Thrown if refund processing fails                      |
  | BookingNotFoundException      | Thrown if booking reference is invalid                 |
  | DatabaseException             | Thrown on DB errors                                   |

3. Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class CancellationController {
      +cancelBooking(request)
    }
    class CancellationService {
      +cancelBooking(params)
    }
    class BookingService {
      +getBooking(bookingRef, lastName)
      +updateStatus(bookingRef, status)
    }
    class PaymentGatewayIntegrationService {
      +processRefund(paymentDetails, amount)
    }
    class NotificationService {
      +sendCancellationNotification(booking, refund)
    }
    class ValidationService {
      +validateCancellationParams(params)
      +validateCancellationWindow(booking)
    }
    class LoggingService {
      +logCancellation(request, response)
    }
    CancellationController --> CancellationService
    CancellationService --> BookingService
    CancellationService --> PaymentGatewayIntegrationService
    CancellationService --> NotificationService
    CancellationService --> ValidationService
    CancellationService --> LoggingService
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as CancellationController
    participant S as CancellationService
    participant V as ValidationService
    participant B as BookingService
    participant P as PaymentGatewayIntegrationService
    participant N as NotificationService
    participant L as LoggingService
    U->>C: POST /api/bookings/cancel
    C->>S: cancelBooking(request)
    S->>V: validateCancellationParams(params)
    V-->>S: ValidationResult
    S->>B: getBooking(bookingRef, lastName)
    B-->>S: Booking
    S->>V: validateCancellationWindow(booking)
    V-->>S: WindowResult
    S->>B: updateStatus(bookingRef, 'CANCELLED')
    B-->>S: UpdateResult
    S->>P: processRefund(paymentDetails, amount)
    P-->>S: RefundResult
    S->>N: sendCancellationNotification(booking, refund)
    N-->>S: NotificationResult
    S->>L: logCancellation(request, response)
    S-->>C: cancellationConfirmation
    C-->>U: Response
  ```

  3.3 Components
  | Component Name                | Description                                             | Existing/New |
  |------------------------------|---------------------------------------------------------|--------------|
  | CancellationController        | Handles REST endpoints for cancellation                 | New          |
  | CancellationService           | Orchestrates cancellation, validation, and integration logic| New      |
  | BookingService                | Retrieves and updates booking status                    | Existing     |
  | PaymentGatewayIntegrationService| Processes refunds with payment providers              | Existing     |
  | NotificationService           | Sends cancellation and refund notifications             | Existing     |
  | ValidationService             | Validates user input and eligibility                    | Existing     |
  | LoggingService                | Logs cancellation requests and responses                | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName         | Validation                                      | Error Message                                   | ClassUsed            |
  |-------------------|-------------------------------------------------|-------------------------------------------------|----------------------|
  | bookingRef        | Not empty, valid reference                       | Invalid booking reference                       | ValidationService    |
  | lastName          | Not empty, matches booking                       | Invalid last name                               | ValidationService    |
  | cancellation window| Within allowed time window                      | Booking not eligible for cancellation           | ValidationService    |
  | refund calculation| Based on fare rules and time of cancellation    | Refund calculation failed                       | CancellationService  |
  | payment method    | Refund to original method only                   | Refund cannot be processed to different method   | PaymentGatewayIntegrationService |
  | user identity     | Verified before processing                       | User identity verification failed               | ValidationService    |

4. Integrations
  | SystemToBeIntegrated | IntegratedFor              | IntegrationType |
  |----------------------|----------------------------|-----------------|
  | Payment Gateway      | Refund processing          | API (REST/JSON) |
  | Notification Service | Cancellation/refund notification| API (REST/JSON) |

5. DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING {
      int booking_id PK
      string booking_ref
      int flight_id FK
      string status
      datetime created_at
      string contact_email
      string contact_phone
      decimal total_amount
      decimal refund_amount
      datetime cancelled_at
    }
    REFUND_LOG {
      int id PK
      int booking_id FK
      decimal amount
      string payment_method
      datetime processed_at
      string status
    }
  ```

  5.2 DB Validations
  - Ensure cancellation is within allowed window.
  - Refund amount is calculated and logged in REFUND_LOG.
  - Refund is processed only to original payment method.

6. Non-Functional Requirements
  6.1 Performance
  - Cancellation and refund process must complete within 10 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth 2.0 for all API endpoints.
    6.2.2 Authorization
    - Only authenticated users can cancel bookings and request refunds.
    - PCI DSS compliance for payment data.

  6.3 Logging
    6.3.1 Application Logging
    - Log all cancellation/refund requests (INFO), validation failures (WARN), errors (ERROR), and refund failures (ERROR).
    6.3.2 Audit Log
    - Log all refund transactions and status changes.

7. Dependencies
  - Payment gateway provider must be available and PCI DSS compliant.
  - Notification service must be available for status delivery.

8. Assumptions
  - Fare rules and cancellation policies are up-to-date and accessible.
  - Payment provider supports real-time refund processing.
  - User contact details are valid and reachable.
