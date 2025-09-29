# For User Story Number [3]
1. Objective
This requirement enables passengers to cancel their booked flights and request refunds according to airline policies. The system must process cancellations, update seat availability, and initiate refunds if eligible. Users must be notified of cancellation and refund status.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (for API authentication)
  - BookingService (for booking management)
  - RefundService (for refund logic)
  - AirlineAPIClient (for fare rules and booking status)
  - PaymentGatewayClient (for refund processing)
  - NotificationService (for sending notifications)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Cancel Booking | POST | Success | /api/bookings/cancel | {
    "bookingReference": "BR12345"
  } | {
    "status": "CANCELLED",
    "refundEligible": true,
    "refundAmount": 200.00,
    "refundStatus": "INITIATED"
  } |
  | Cancel Booking | POST | Failure | /api/bookings/cancel | Same as above | {
    "error": "Booking not eligible for cancellation."
  } |

  2.3 Exceptions
  - BookingNotFoundException: Thrown if booking reference is invalid.
  - NotEligibleForCancellationException: Thrown if booking cannot be cancelled.
  - RefundCalculationException: Thrown if refund cannot be calculated.
  - PaymentGatewayException: Thrown if refund processing fails.
  - ExternalAPIException: Thrown if airline API fails.

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class BookingController {
      +cancelBooking(request)
    }
    class BookingService {
      +cancelBooking(bookingReference)
    }
    class RefundService {
      +initiateRefund(booking)
    }
    class AirlineAPIClient {
      +getFareRules(bookingReference)
    }
    class PaymentGatewayClient {
      +processRefund(paymentDetails)
    }
    class NotificationService {
      +sendCancellationNotification(user, booking)
    }
    class Booking {
      -bookingReference
      -status
      -refundEligible
      -refundAmount
      -refundStatus
    }
    BookingController --> BookingService
    BookingService --> RefundService
    BookingService --> AirlineAPIClient
    RefundService --> PaymentGatewayClient
    BookingService --> NotificationService
    BookingService --> Booking
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as BookingController
    participant S as BookingService
    participant A as AirlineAPIClient
    participant R as RefundService
    participant P as PaymentGatewayClient
    participant N as NotificationService
    U->>C: POST /api/bookings/cancel
    C->>S: cancelBooking(bookingReference)
    S->>A: getFareRules(bookingReference)
    A-->>S: fareRules
    S->>R: initiateRefund(booking)
    R->>P: processRefund(paymentDetails)
    P-->>R: refundStatus
    R-->>S: refundStatus
    S->>N: sendCancellationNotification(user, booking)
    N-->>U: cancellation/refund notification
    S-->>C: status, refundEligible, refundAmount, refundStatus
    C-->>U: status, refundEligible, refundAmount, refundStatus
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | BookingController | Handles API requests for booking cancellation | New |
  | BookingService | Business logic for cancellation | New |
  | RefundService | Handles refund initiation and logic | New |
  | AirlineAPIClient | Integrates with airline APIs for fare rules | New |
  | PaymentGatewayClient | Processes refunds | New |
  | NotificationService | Sends cancellation/refund notifications | New |
  | Booking | Model for booking and refund data | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | bookingReference | Must exist and be eligible | Booking not eligible for cancellation | BookingService |
  | refundAmount | Must be calculated as per fare rules | Refund calculation error | RefundService |
  | paymentDetails | Must be valid for refund | Refund processing failed | PaymentGatewayClient |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline API | Fare rules, booking status | API |
  | Payment Gateway | Refund processing | API |
  | Notification Service | Sending cancellation/refund notifications | API |
  | Authentication Service | Securing API access | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING {
      string bookingReference
      string status
      boolean refundEligible
      decimal refundAmount
      string refundStatus
    }
  ```
  5.2 DB Validations
  - Booking reference must exist.
  - Refund eligibility must be checked before processing.

6 Non-Functional Requirements
  6.1 Performance
  - Cancellations processed within 2 minutes.
  - Refunds initiated within 24 hours.
  6.2 Security
    6.2.1 Authentication
    - All API calls require JWT-based authentication.
    6.2.2 Authorization
    - Only authenticated users can cancel bookings.
    - PCI DSS compliance for payment data.
  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Fare rule checks, refund calculations
    - INFO: Successful cancellations/refunds
    - ERROR: Refund failures, validation errors
    - WARN: Delayed refunds
    6.3.2 Audit Log
    - Log user ID, booking reference, cancellation/refund status, timestamp

7 Dependencies
  - Airline API provider
  - Payment gateway provider
  - Notification service provider
  - Authentication service

8 Assumptions
  - Airline and payment APIs are available and performant.
  - Refunds are processed to the original payment method.
