# For User Story Number [3]
\
1. Objective
\
Allow travelers to retrieve, modify, or cancel their air transport bookings using a reference number. Support updates to passenger details, travel dates, or seats, and process cancellations with applicable refund policies. Ensure all changes are validated, logged, and confirmed to the user.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - BookingManagementService (core logic for view/modify/cancel)
\
    - AirlinePolicyClient (integration for fare rules)
\
    - RefundService (refund calculation/processing)
\
    - ValidationUtil (input and policy validation)
\
    - AuditLogger (change/cancel audit trail)
\
    - NotificationService (confirmation email)
\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Retrieve Booking | GET | Success | /api/bookings/{ref} | N/A | { "bookingRef": "ABC123", "status": "CONFIRMED", "flightId": "DL100", ... } |
\
| Modify Booking | PUT | Success | /api/bookings/{ref} | { "passengerInfo": [...], "travelDates": {...}, "seatSelection": [...] } | { "bookingRef": "ABC123", "status": "MODIFIED", ... } |
\
| Cancel Booking | DELETE | Success | /api/bookings/{ref} | N/A | { "bookingRef": "ABC123", "status": "CANCELLED", "refund": 250, "message": "Booking cancelled. Refund processed." } |
\
| Modify/Cancel Booking | PUT/DELETE | Failure | /api/bookings/{ref} | ... | { "error": "Modification not allowed as per airline policy." } |
\
  2.3 Exceptions
\
    - BookingNotFoundException: Invalid or missing reference
\
    - ModificationNotAllowedException: Policy or timing restriction
\
    - RefundCalculationException: Refund processing error
\
    - ExternalAPIException: Airline API failure
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
  class BookingManagementController {
\
    +getBooking()
\
    +modifyBooking()
\
    +cancelBooking()
\
  }
\
  class BookingManagementService {
\
    +getBooking()
\
    +modifyBooking()
\
    +cancelBooking()
\
  }
\
  class AirlinePolicyClient {
\
    +validateChange()
\
    +validateCancel()
\
  }
\
  class RefundService {
\
    +calculateRefund()
\
    +processRefund()
\
  }
\
  class ValidationUtil {
\
    +validateModification()
\
    +validateCancellation()
\
  }
\
  class AuditLogger {
\
    +logChange()
\
    +logCancel()
\
  }
\
  class NotificationService {
\
    +sendConfirmation()
\
  }
\
  BookingManagementController --> BookingManagementService
\
  BookingManagementService --> AirlinePolicyClient
\
  BookingManagementService --> RefundService
\
  BookingManagementService --> ValidationUtil
\
  BookingManagementService --> AuditLogger
\
  BookingManagementService --> NotificationService
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
  participant C as BookingManagementController
\
  participant S as BookingManagementService
\
  participant V as ValidationUtil
\
  participant P as AirlinePolicyClient
\
  participant R as RefundService
\
  participant A as AuditLogger
\
  participant N as NotificationService
\
  U->>C: GET /api/bookings/{ref}
\
  C->>S: getBooking(ref)
\
  S-->>C: bookingDetails
\
  C-->>U: Response
\
  U->>C: PUT /api/bookings/{ref}
\
  C->>S: modifyBooking(ref, request)
\
  S->>V: validateModification(ref, request)
\
  V-->>S: validationResult
\
  S->>P: validateChange(ref, request)
\
  P-->>S: policyResult
\
  S->>A: logChange(ref, request)
\
  S->>N: sendConfirmation(user, details)
\
  S-->>C: modifiedBooking
\
  C-->>U: Response
\
  U->>C: DELETE /api/bookings/{ref}
\
  C->>S: cancelBooking(ref)
\
  S->>V: validateCancellation(ref)
\
  V-->>S: validationResult
\
  S->>P: validateCancel(ref)
\
  P-->>S: policyResult
\
  S->>R: calculateRefund(ref)
\
  R-->>S: refundAmount
\
  S->>R: processRefund(ref, refundAmount)
\
  R-->>S: refundStatus
\
  S->>A: logCancel(ref)
\
  S->>N: sendConfirmation(user, details)
\
  S-->>C: cancelResponse
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
| BookingManagementController | REST endpoint for booking management | New |
\
| BookingManagementService | Business logic for modify/cancel | New |
\
| AirlinePolicyClient | Integrates with airline APIs | New |
\
| RefundService | Calculates/processes refunds | New |
\
| ValidationUtil | Validates modification/cancellation | New |
\
| AuditLogger | Logs all changes/cancellations | New |
\
| NotificationService | Sends confirmation emails | New |
\
  3.4 Service Layer Logic as per the user story requirement and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|------------|--------------|-----------|
\
| bookingRef | Must exist | Booking not found. | ValidationUtil |
\
| travelDates | Must be future | Only future bookings can be modified/cancelled. | ValidationUtil |
\
| change/cancel request | Airline policy | Modification not allowed as per airline policy. | AirlinePolicyClient |
\
| refund | Policy-based calculation | Refund calculation error. | RefundService |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|---------------|-----------------|
\
| Airline APIs | Fare rules, change/cancel | API |
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
  BOOKING ||--o{ MODIFICATION_LOG : logs
\
  BOOKING ||--o{ REFUND : refunds
\
  BOOKING {
\
    string bookingRef PK
\
    string userId
\
    string flightId
\
    string status
\
    datetime bookingTime
\
    datetime modifiedTime
\
  }
\
  MODIFICATION_LOG {
\
    string id PK
\
    string bookingRef FK
\
    string action
\
    string details
\
    datetime timestamp
\
  }
\
  REFUND {
\
    string id PK
\
    string bookingRef FK
\
    decimal amount
\
    string status
\
    datetime processedTime
\
  }
\
```
\
  5.2 DB Validations
\
    - Ensure only future bookings are modifiable/cancellable.
\
    - Log all changes and cancellations.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Changes/cancellations processed within 5 seconds.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 for user authentication.
\
    6.2.2 Authorization
\
      - Only authenticated users can modify/cancel.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API requests/responses, policy checks.
\
      - INFO: Successful modifications/cancellations.
\
      - WARN: Policy rejections, partial failures.
\
      - ERROR: Validation failures, refund errors.
\
    6.3.2 Audit Log
\
      - Log all changes/cancellations with user and timestamp.
\

\
7 Dependencies
\
  - Airline APIs for fare rules
\
  - Azure SQL Database
\
  - Email gateway
\

\
8 Assumptions
\
  - Airline APIs are available and performant.
\
  - Refund policies are up-to-date and accurate.
\
  - User authentication is handled before management API is called.