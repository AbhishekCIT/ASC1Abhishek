# For User Story Number [2]

## 1. Objective
The objective of this requirement is to enable travelers to view and manage their air transport bookings online. The system must allow authenticated users to access, modify, or cancel their bookings, ensuring secure handling of user data and compliance with airline policies. All changes and cancellations should be processed reliably, with appropriate validations and audit logging.

## 2. API Model
### 2.1 Common Components/Services
- BookingService (for managing bookings)
- UserService (for authentication and user data)
- AirlineIntegrationService (for interacting with airline APIs)
- RefundService (for processing cancellations and refunds)
- AuditLogService (for logging changes)
- ValidationUtils (for input and policy validations)

### 2.2 API Details
| Operation | REST Method | Type     | URL                              | Request (Sample JSON)                                         | Response (Sample JSON)                                                      |
|-----------|-------------|----------|----------------------------------|---------------------------------------------------------------|-------------------------------------------------------------------------------|
| Read      | GET         | Success  | /api/bookings                    | -                                                             | { "bookings": [{ "bookingRef": "BR123456", "flight": {...}, "status": "CONFIRMED" }] } |
| Read      | GET         | Success  | /api/bookings/{bookingRef}       | -                                                             | { "bookingRef": "BR123456", "flight": {...}, "passengerInfo": {...}, "status": "CONFIRMED" } |
| Update    | PUT         | Success  | /api/bookings/{bookingRef}       | { "passengerInfo": {...}, "flightChange": {...} }           | { "bookingRef": "BR123456", "status": "UPDATED", "changeFee": 50.00 } |
| Delete    | DELETE      | Success  | /api/bookings/{bookingRef}       | -                                                             | { "bookingRef": "BR123456", "status": "CANCELLED", "refund": 300.00 } |
| Failure   | GET/PUT/DELETE | Failure | (all above)                      | Invalid request, unauthorized, or policy violation            | { "error": "Change not allowed as per airline policy" }                    |

### 2.3 Exceptions
- AuthenticationException: Thrown when user authentication fails
- BookingNotFoundException: Thrown when a booking is not found
- PolicyViolationException: Thrown for changes/cancellations not allowed by airline policy
- RefundCalculationException: Thrown when refund calculation fails

## 3. Functional Design
### 3.1 Class Diagram
```mermaid
classDiagram
    class BookingService {
        +List<Booking> getBookingsByUser(String userId)
        +Booking getBooking(String bookingRef)
        +Booking updateBooking(String bookingRef, UpdateRequest req)
        +RefundResult cancelBooking(String bookingRef)
    }
    class UserService {
        +User authenticate(String token)
    }
    class AirlineIntegrationService {
        +boolean updateBookingWithAirline(Booking booking)
        +boolean cancelBookingWithAirline(Booking booking)
    }
    class RefundService {
        +RefundResult calculateRefund(Booking booking)
    }
    class AuditLogService {
        +void logChange(String bookingRef, String action, String userId)
    }
    class ValidationUtils {
        +void validateUpdateRequest(UpdateRequest req)
        +void validateCancellation(Booking booking)
    }
    BookingService --> UserService
    BookingService --> AirlineIntegrationService
    BookingService --> RefundService
    BookingService --> AuditLogService
    BookingService --> ValidationUtils
```

### 3.2 UML Sequence Diagram
```mermaid
sequenceDiagram
    participant User
    participant APIController
    participant UserService
    participant BookingService
    participant ValidationUtils
    participant AirlineIntegrationService
    participant RefundService
    participant AuditLogService

    User->>APIController: GET /api/bookings
    APIController->>UserService: authenticate(token)
    UserService-->>APIController: User
    APIController->>BookingService: getBookingsByUser(userId)
    BookingService-->>APIController: List<Booking>
    APIController-->>User: Return bookings

    User->>APIController: PUT /api/bookings/{bookingRef}
    APIController->>UserService: authenticate(token)
    UserService-->>APIController: User
    APIController->>ValidationUtils: validateUpdateRequest()
    ValidationUtils-->>APIController: OK/Exception
    APIController->>BookingService: updateBooking()
    BookingService->>AirlineIntegrationService: updateBookingWithAirline()
    AirlineIntegrationService-->>BookingService: Success/Failure
    BookingService->>AuditLogService: logChange()
    AuditLogService-->>BookingService: void
    BookingService-->>APIController: Booking
    APIController-->>User: Updated booking

    User->>APIController: DELETE /api/bookings/{bookingRef}
    APIController->>UserService: authenticate(token)
    UserService-->>APIController: User
    APIController->>ValidationUtils: validateCancellation()
    ValidationUtils-->>APIController: OK/Exception
    APIController->>BookingService: cancelBooking()
    BookingService->>AirlineIntegrationService: cancelBookingWithAirline()
    AirlineIntegrationService-->>BookingService: Success/Failure
    BookingService->>RefundService: calculateRefund()
    RefundService-->>BookingService: RefundResult
    BookingService->>AuditLogService: logChange()
    AuditLogService-->>BookingService: void
    BookingService-->>APIController: RefundResult
    APIController-->>User: Cancellation confirmation and refund info
```

### 3.3 Components
| Component Name           | Description                                              | Existing/New |
|-------------------------|----------------------------------------------------------|--------------|
| BookingService          | Manages booking retrieval, updates, and cancellations    | New          |
| UserService             | Handles authentication and user data                     | Existing     |
| AirlineIntegrationService | Integrates with airline APIs for modifications/cancels | New          |
| RefundService           | Calculates and processes refunds                         | New          |
| AuditLogService         | Logs all changes and cancellations                       | New          |
| ValidationUtils         | Performs input and policy validations                    | New          |
| APIController           | REST API endpoint controller                             | New          |

### 3.4 Service Layer Logic and Validations
| FieldName         | Validation                                         | Error Message                                   | ClassUsed        |
|-------------------|----------------------------------------------------|-------------------------------------------------|------------------|
| token             | Must be valid and authenticated                    | User authentication failed                      | UserService      |
| bookingRef        | Must exist and belong to user                      | Booking not found or unauthorized               | BookingService   |
| updateRequest     | Must comply with airline change policies           | Change not allowed as per airline policy        | ValidationUtils  |
| cancellation      | Must comply with airline cancellation policies     | Cancellation not allowed as per airline policy  | ValidationUtils  |
| refund            | Must be calculated as per policy                   | Refund calculation error                        | RefundService    |

## 4. Integrations
| SystemToBeIntegrated | IntegratedFor         | IntegrationType |
|----------------------|----------------------|-----------------|
| Airline APIs         | Booking changes/cancels | API           |
| Refund Service       | Refund calculation      | API           |
| Audit Log Service    | Change/cancel logging   | API           |

## 5. DB Details
### 5.1 ER Model
```mermaid
erDiagram
    USER ||--o{ BOOKING : has
    BOOKING }|..|{ FLIGHT : books
    BOOKING }|..|{ PAYMENT : pays
    AUDIT_LOG }|..|{ BOOKING : logs
    USER {
        string userId PK
        string email
        string name
    }
    BOOKING {
        string bookingRef PK
        string userId FK
        string flightId FK
        datetime bookingDate
        string status
    }
    FLIGHT {
        string flightId PK
        string origin
        string destination
        datetime departureDate
        datetime arrivalDate
        string airline
        decimal price
    }
    PAYMENT {
        string paymentId PK
        string bookingRef FK
        decimal amount
        string status
        datetime paymentDate
    }
    AUDIT_LOG {
        string logId PK
        string bookingRef FK
        string action
        string userId
        datetime timestamp
    }
```

### 5.2 DB Validations
- Only authenticated users can access their bookings
- Booking status must be updated as per change/cancellation
- All changes/cancellations must be logged in AUDIT_LOG

## 6. Non-Functional Requirements
### 6.1 Performance
- Support high concurrency for viewing/managing bookings
- Optimize DB queries for quick retrieval

### 6.2 Security
#### 6.2.1 Authentication
- All APIs secured via OAuth2 tokens
#### 6.2.2 Authorization
- Only authenticated users can view/manage their bookings
- Admin access for audit log retrieval (if applicable)

### 6.3 Logging
#### 6.3.1 Application Logging
- DEBUG: API request/response payloads (excluding sensitive data)
- INFO: Successful changes/cancellations
- ERROR: Policy violations, refund calculation errors
- WARN: Suspicious access patterns
#### 6.3.2 Audit Log
- Log all changes/cancellations with user, action, and timestamp

## 7. Dependencies
- Airline APIs for booking changes/cancellations
- Refund service provider
- Audit log storage

## 8. Assumptions
- Airline APIs support real-time booking modifications
- Refund policies are accessible and up-to-date
- Audit log storage is reliable and secure
