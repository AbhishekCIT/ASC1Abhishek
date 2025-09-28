# For User Story Number [3]
1. Objective
This requirement enables passengers to manage their baggage online, including viewing baggage policies, adding/removing checked bags, and pre-paying baggage fees. It aims to reduce airport wait times and confusion by providing clear, up-to-date baggage information and seamless payment integration. The goal is to enhance passenger convenience and operational efficiency.

2. API Model
  2.1 Common Components/Services
  - User Authentication Service (Spring Security)
  - Baggage Management Service
  - Payment Gateway Integration Service
  - Booking Service
  - Notification Service (Email/SMS)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | View Baggage Policy | GET | Success/Failure | /api/baggage/policy | {"bookingReference":"ABC123"} | {"allowance":2,"fees":[{"bag":1,"fee":30.00}] } |
  | Add/Remove Baggage | POST | Success/Failure | /api/baggage/manage | {"bookingReference":"ABC123","addBags":1,"removeBags":0} | {"status":"UPDATED","totalFee":60.00} |
  | Pay Baggage Fee | POST | Success/Failure | /api/baggage/pay | {"bookingReference":"ABC123","paymentDetails":{...}} | {"paymentStatus":"SUCCESS","baggageStatus":"CONFIRMED"} |

  2.3 Exceptions
  - BaggageLimitExceededException
  - PaymentFailedException
  - ValidationException

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class BaggageController
    class BaggageService
    class PaymentService
    class BookingService
    class NotificationService
    class BaggageRepository
    class BookingRepository
    class PaymentGatewayClient
    BaggageController --> BaggageService
    BaggageService --> BookingService
    BaggageService --> PaymentService
    BaggageService --> NotificationService
    BaggageService --> BaggageRepository
    BookingService --> BookingRepository
    PaymentService --> PaymentGatewayClient
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant BaggageController
    participant BaggageService
    participant BookingService
    participant PaymentService
    participant NotificationService
    participant PaymentGatewayClient
    User->>BaggageController: View Baggage Policy
    BaggageController->>BaggageService: getBaggagePolicy()
    BaggageService->>BookingService: getBookingDetails()
    BaggageService-->>BaggageController: policyDetails
    BaggageController-->>User: policyDetails
    User->>BaggageController: Add/Remove Baggage
    BaggageController->>BaggageService: updateBaggage()
    BaggageService->>BookingService: validateLimits()
    BaggageService-->>BaggageController: updateStatus
    BaggageController-->>User: updateStatus
    User->>BaggageController: Pay Baggage Fee
    BaggageController->>PaymentService: processPayment()
    PaymentService->>PaymentGatewayClient: initiatePayment()
    PaymentGatewayClient-->>PaymentService: paymentStatus
    PaymentService-->>BaggageController: paymentStatus
    BaggageController->>NotificationService: sendBaggageConfirmation()
    BaggageController-->>User: paymentStatus
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | BaggageController | Handles baggage management requests | New |
  | BaggageService | Business logic for baggage management | New |
  | PaymentService | Handles payment processing | New |
  | BookingService | Booking data and validation | New |
  | NotificationService | Sends baggage confirmation | New |
  | BaggageRepository | Data access for baggage records | New |
  | BookingRepository | Data access for bookings | New |
  | PaymentGatewayClient | Integrates with payment gateway | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | baggage allowance | Policy/rule check | "Baggage allowance exceeded" | BaggageService |
  | baggage fees | Accurate calculation | "Fee calculation error" | BaggageService |
  | paymentDetails | Valid & Authorized | "Invalid payment details" | PaymentService |
  | bookingReference | Existence check | "Invalid booking reference" | BookingService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline Baggage Policy API | Policy and fee data | API |
  | Payment Gateway | Payment processing | API |
  | Booking System | Booking updates | API |
  | Notification Service | Baggage confirmation | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING ||--o{ BAGGAGE : has
    BAGGAGE }o--|| PAYMENT : paid_by
    BAGGAGE {
      int id PK
      int booking_id FK
      int bag_count
      decimal total_fee
      string status
    }
    PAYMENT {
      int id PK
      int baggage_id FK
      decimal amount
      string status
      datetime payment_time
    }
  ```

  5.2 DB Validations
  - Baggage count must not exceed policy limit.
  - Payment status must be SUCCESS before baggage is confirmed.

6 Non-Functional Requirements
  6.1 Performance
  - Baggage updates processed within 2 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth2/JWT-based authentication for all APIs.
    - HTTPS enforced for all endpoints.
    6.2.2 Authorization
    - Only booking owner can modify baggage.

  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Baggage management requests
    - INFO: Baggage updates and payments
    - ERROR: Failed transactions, exceptions
    - WARN: Attempts to exceed baggage limits
    6.3.2 Audit Log
    - Log all baggage and payment events with timestamp, user, and status.

7 Dependencies
  - Airline baggage policy API
  - Payment gateway
  - Booking system
  - Notification service

8 Assumptions
  - Airline baggage policy API is reliable and up-to-date.
  - Payment gateway is PCI DSS compliant.
  - Booking data is consistent and synchronized.
