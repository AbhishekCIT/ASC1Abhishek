# For User Story Number [2]
1. Objective
This requirement enables passengers to book a selected flight by entering passenger details, selecting seats, and making a payment. The system must confirm the booking, reserve the seat, and provide a booking reference and e-ticket. Secure payment processing and seat availability checks are critical.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (for API authentication)
  - BookingService (for booking logic)
  - PaymentGatewayClient (for payment integration)
  - EmailService (for sending confirmation and e-ticket)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Book Flight | POST | Success | /api/bookings | {
    "flightId": "FL123",
    "passengerDetails": [{"name": "John Doe", "passport": "A1234567"}],
    "seatSelection": ["12A"],
    "paymentInfo": {"method": "card", "token": "tok_abc123"}
  } | {
    "bookingReference": "BR12345",
    "status": "CONFIRMED",
    "eTicket": "<PDF/URL>"
  } |
  | Book Flight | POST | Failure | /api/bookings | Same as above | {
    "error": "Payment failed. Booking not confirmed."
  } |

  2.3 Exceptions
  - InvalidPassengerDetailsException: Thrown if passenger details are incomplete/invalid.
  - SeatUnavailableException: Thrown if selected seats are not available.
  - PaymentFailedException: Thrown if payment authorization fails.
  - ExternalAPIException: Thrown if external booking or payment API fails.

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class BookingController {
      +bookFlight(request)
    }
    class BookingService {
      +createBooking(details)
    }
    class PaymentGatewayClient {
      +processPayment(paymentInfo)
    }
    class EmailService {
      +sendConfirmation(booking)
    }
    class Booking {
      -bookingReference
      -status
      -passengerDetails
      -flightId
      -seatSelection
      -eTicket
    }
    BookingController --> BookingService
    BookingService --> PaymentGatewayClient
    BookingService --> EmailService
    BookingService --> Booking
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as BookingController
    participant S as BookingService
    participant P as PaymentGatewayClient
    participant E as EmailService
    participant B as Booking
    U->>C: POST /api/bookings
    C->>S: createBooking(details)
    S->>P: processPayment(paymentInfo)
    P-->>S: paymentStatus
    S->>B: create booking
    S->>E: sendConfirmation(booking)
    E-->>U: confirmation email
    S-->>C: bookingReference, status, eTicket
    C-->>U: bookingReference, status, eTicket
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | BookingController | Handles API requests for booking | New |
  | BookingService | Business logic for booking | New |
  | PaymentGatewayClient | Integrates with payment gateway | New |
  | EmailService | Sends booking confirmation and e-ticket | New |
  | Booking | Model for booking data | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | passengerDetails | Must be complete and valid | Invalid passenger details | BookingService |
  | seatSelection | Must be available | Selected seat not available | BookingService |
  | paymentInfo | Must be authorized | Payment failed | PaymentGatewayClient |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Payment Gateway (Stripe/PayPal) | Payment processing | API |
  | Email Service | Sending confirmation/e-ticket | API |
  | Authentication Service | Securing API access | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING {
      string bookingReference
      string status
      string flightId
      string passengerDetails
      string seatSelection
      string eTicket
    }
  ```
  5.2 DB Validations
  - Booking reference must be unique.
  - Seat selection must be checked for availability before booking.

6 Non-Functional Requirements
  6.1 Performance
  - Booking process must complete within 5 seconds.
  - Payment and seat reservation must be atomic.
  6.2 Security
    6.2.1 Authentication
    - All API calls require JWT-based authentication.
    6.2.2 Authorization
    - Only authenticated users can book flights.
    - PCI DSS compliance for payment data.
  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Payment request/response, booking details
    - INFO: Successful bookings
    - ERROR: Payment failures, validation errors
    - WARN: Booking retries
    6.3.2 Audit Log
    - Log user ID, booking details, payment status, timestamp

7 Dependencies
  - Payment gateway provider (Stripe, PayPal)
  - Email service provider
  - Authentication service

8 Assumptions
  - Payment gateway and email services are available and performant.
  - Seat availability is checked in real-time.
