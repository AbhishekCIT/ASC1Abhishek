# For User Story Number [1]
1. Objective
This requirement enables passengers to search, select, and book air transport tickets online. It ensures real-time availability, pricing, and booking confirmation through a secure and user-friendly web platform. The goal is to provide a seamless and reliable ticket booking experience.

2. API Model
  2.1 Common Components/Services
  - User Authentication Service (Spring Security)
  - Flight Search Service
  - Booking Service
  - Payment Gateway Integration Service
  - Notification Service (Email/SMS)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Search Flights | GET | Success/Failure | /api/flights/search | {"origin":"JFK","destination":"LAX","date":"2025-10-10"} | {"flights":[{"flightId":1,"origin":"JFK","destination":"LAX","price":350.00,"seatsAvailable":5}]} |
  | Book Flight | POST | Success/Failure | /api/bookings | {"flightId":1,"passengerDetails":{...},"paymentDetails":{...}} | {"bookingReference":"ABC123","status":"CONFIRMED"} |
  | Payment Callback | POST | Success/Failure | /api/payments/callback | {"transactionId":"xyz","status":"SUCCESS"} | {"paymentStatus":"SUCCESS"} |

  2.3 Exceptions
  - FlightNotFoundException
  - SeatUnavailableException
  - PaymentFailedException
  - ValidationException

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class FlightController
    class BookingController
    class FlightService
    class BookingService
    class PaymentService
    class NotificationService
    class FlightRepository
    class BookingRepository
    class PaymentGatewayClient
    FlightController --> FlightService
    BookingController --> BookingService
    BookingService --> FlightService
    BookingService --> PaymentService
    BookingService --> NotificationService
    FlightService --> FlightRepository
    BookingService --> BookingRepository
    PaymentService --> PaymentGatewayClient
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant FlightController
    participant FlightService
    participant BookingController
    participant BookingService
    participant PaymentService
    participant NotificationService
    participant PaymentGatewayClient
    User->>FlightController: Search Flights
    FlightController->>FlightService: getAvailableFlights()
    FlightService-->>FlightController: flights
    FlightController-->>User: flights
    User->>BookingController: Book Flight
    BookingController->>BookingService: createBooking()
    BookingService->>FlightService: checkSeatAvailability()
    BookingService->>PaymentService: processPayment()
    PaymentService->>PaymentGatewayClient: initiatePayment()
    PaymentGatewayClient-->>PaymentService: paymentStatus
    PaymentService-->>BookingService: paymentStatus
    BookingService->>NotificationService: sendConfirmation()
    BookingService-->>BookingController: bookingReference
    BookingController-->>User: bookingConfirmation
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | FlightController | Handles flight search requests | New |
  | BookingController | Handles booking requests | New |
  | FlightService | Business logic for flights | New |
  | BookingService | Business logic for bookings | New |
  | PaymentService | Handles payment processing | New |
  | NotificationService | Sends email/SMS notifications | New |
  | FlightRepository | Data access for flights | New |
  | BookingRepository | Data access for bookings | New |
  | PaymentGatewayClient | Integrates with payment gateway | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | date, origin, destination | Mandatory | "All search fields are required" | FlightService |
  | passengerDetails | Mandatory | "Passenger details required" | BookingService |
  | paymentDetails | Valid & Authorized | "Invalid payment details" | PaymentService |
  | seatAvailability | Real-time check | "Selected seat not available" | BookingService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline APIs | Real-time flight/seat data | API |
  | Payment Gateway (Stripe/PayPal) | Payment processing | API |
  | Email/SMS Service | Booking confirmation | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    FLIGHT ||--o{ BOOKING : has
    BOOKING ||--o{ PASSENGER : includes
    BOOKING }o--|| PAYMENT : made_by
    FLIGHT {
      int id PK
      string origin
      string destination
      datetime departure_time
      int seats_available
      decimal price
    }
    BOOKING {
      int id PK
      int flight_id FK
      datetime booking_time
      string booking_reference
      string status
    }
    PASSENGER {
      int id PK
      int booking_id FK
      string name
      string email
      string phone
    }
    PAYMENT {
      int id PK
      int booking_id FK
      decimal amount
      string status
      datetime payment_time
    }
  ```

  5.2 DB Validations
  - Booking reference must be unique.
  - Seats available must be >= 0.
  - Payment status must be one of [PENDING, SUCCESS, FAILED].

6 Non-Functional Requirements
  6.1 Performance
  - API response time < 2 seconds.
  - Caching of frequent flight search queries at API layer.

  6.2 Security
    6.2.1 Authentication
    - OAuth2/JWT-based authentication for all APIs.
    - HTTPS enforced for all endpoints.
    6.2.2 Authorization
    - Role-based access (e.g., passenger, admin).

  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: API requests/responses (excluding sensitive data)
    - INFO: Booking and payment events
    - ERROR: Failed transactions, exceptions
    - WARN: Unusual activity
    6.3.2 Audit Log
    - Log all booking and payment events with timestamp, user, and status.

7 Dependencies
  - Airline APIs for real-time data
  - Payment gateway services
  - Email/SMS providers

8 Assumptions
  - All airline APIs are available and reliable.
  - Payment gateway is PCI DSS compliant.
  - Users have valid email/phone for notifications.
