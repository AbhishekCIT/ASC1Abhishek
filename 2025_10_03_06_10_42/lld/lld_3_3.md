# For User Story Number [3]
\
1. Objective
\
Enable travelers to view and manage their air transport bookings, access detailed itineraries, and make changes or cancel bookings as per airline policies. Ensure secure access, compliance with fare rules, and robust audit logging for all actions. Provide real-time updates and notifications for booking changes and cancellations.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - BookingService (fetches and manages bookings)
\
    - ItineraryService (provides detailed itineraries)
\
    - ChangeRequestService (handles change/cancellation requests)
\
    - NotificationService (sends email/SMS notifications)
\
    - AuditLogService (logs all user actions)
\
    - ErrorHandler (handles errors)
\

\
  2.2 API Details
\
| Operation    | REST Method | Type        | URL                               | Request (JSON) | Response (JSON) |
\
|--------------|-------------|-------------|-----------------------------------|----------------|-----------------|
\
| ViewBookings | GET         | Success     | /api/air/bookings                 | {"userId": "123"} | {"bookings": [{"bookingReference": "ABC123", "flightId": "123", "status": "CONFIRMED"}]} |
\
| Itinerary    | GET         | Success     | /api/air/bookings/{ref}/itinerary | {}             | {"itinerary": {...}} |
\
| Change       | POST        | Success     | /api/air/bookings/{ref}/change    | {"changeType": "date", "newValue": "2025-12-05"} | {"status": "CHANGE_PENDING", "message": "Change request submitted."} |
\
| Cancel       | POST        | Success     | /api/air/bookings/{ref}/cancel    | {}             | {"status": "CANCELLED", "refundStatus": "PROCESSING"} |
\
| Change/Cancel| POST        | Failure     | /api/air/bookings/{ref}/change/cancel | {"changeType": "date", "newValue": "2020-01-01"} | {"error": "Change not allowed as per fare rules."} |
\

\
  2.3 Exceptions
\
    - UnauthorizedAccessException: Thrown for unauthenticated access.
\
    - InvalidChangeException: Thrown for changes not allowed by fare rules.
\
    - BookingNotFoundException: Thrown for missing booking reference.
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
        +viewBookings(userId)
\
        +getItinerary(ref)
\
        +requestChange(ref, changeRequest)
\
        +cancelBooking(ref)
\
    }
\
    class BookingService {
\
        +fetchBookings(userId)
\
        +manageBooking(ref, action)
\
    }
\
    class ItineraryService {
\
        +getItinerary(ref)
\
    }
\
    class ChangeRequestService {
\
        +submitChange(ref, changeRequest)
\
    }
\
    class NotificationService {
\
        +sendNotification(userId, message)
\
    }
\
    class AuditLogService {
\
        +logAction(userId, action, details)
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
    BookingController --> ItineraryService
\
    BookingController --> ChangeRequestService
\
    BookingController --> NotificationService
\
    BookingController --> AuditLogService
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
    participant IS as ItineraryService
\
    participant CRS as ChangeRequestService
\
    participant NS as NotificationService
\
    participant ALS as AuditLogService
\
    participant EH as ErrorHandler
\
    U->>BC: View bookings request
\
    BC->>BS: Fetch bookings
\
    BS-->>BC: Booking list
\
    BC-->>U: Return bookings
\
    U->>BC: Request itinerary
\
    BC->>IS: Get itinerary
\
    IS-->>BC: Itinerary
\
    BC-->>U: Return itinerary
\
    U->>BC: Request change/cancel
\
    BC->>CRS: Submit change/cancel
\
    CRS-->>BC: Change/cancel result
\
    BC->>NS: Send notification
\
    NS-->>U: Notification
\
    BC->>ALS: Log action
\
    ALS-->>BC: Log result
\
    BC-->>U: Return change/cancel status
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
| BookingController    | Handles booking management API requests                | New          |
\
| BookingService       | Fetches and manages bookings                           | New          |
\
| ItineraryService     | Provides detailed itinerary information                | New          |
\
| ChangeRequestService | Handles change/cancellation requests                   | New          |
\
| NotificationService  | Sends notifications (email/SMS)                        | Existing     |
\
| AuditLogService      | Logs all user actions                                  | Existing     |
\
| ErrorHandler         | Formats and returns error responses                    | Existing     |
\

\
  3.4 Service Layer Logic & Validations
\
| FieldName        | Validation                           | Error Message                       | ClassUsed           |
\
|------------------|--------------------------------------|-------------------------------------|---------------------|
\
| userId           | Must be authenticated                | Unauthorized access.                | BookingController   |
\
| bookingReference | Must exist and belong to user        | Booking not found.                  | BookingService      |
\
| changeRequest    | Must comply with fare rules          | Change not allowed as per fare rules.| ChangeRequestService|
\
| cancelRequest    | Must comply with airline policies    | Cancellation not allowed.           | ChangeRequestService|
\

\
4. Integrations
\
| SystemToBeIntegrated | IntegratedFor           | IntegrationType |
\
|---------------------|------------------------|-----------------|
\
| Airline APIs        | Booking management      | API             |
\
| Email/SMS Gateway   | Notifications          | API             |
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
    CHANGE_REQUEST {
\
        id PK
\
        booking_id FK
\
        change_type
\
        new_value
\
        status
\
        requested_at
\
    }
\
    CANCELLATION {
\
        id PK
\
        booking_id FK
\
        status
\
        refund_status
\
        cancelled_at
\
    }
\
    AUDIT_LOG {
\
        id PK
\
        user_id
\
        action
\
        details
\
        timestamp
\
    }
\
    BOOKING ||--o| CHANGE_REQUEST: has
\
    BOOKING ||--o| CANCELLATION: has
\
    BOOKING ||--o| AUDIT_LOG: logs
\
```
\

\
  5.2 DB Validations
\
    - Ensure change/cancellation requests comply with fare rules and airline policies.
\
    - Log all user actions for auditability.
\

\
6. Non-Functional Requirements
\
  6.1 Performance
\
    - Booking management pages must load within 2 seconds.
\
    - Scalable backend for concurrent user management.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 user authentication for all booking management actions.
\
    6.2.2 Authorization
\
      - Only authenticated users can access and modify their bookings.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API request/response payloads
\
      - INFO: Successful changes/cancellations
\
      - ERROR: Failed change/cancellation requests
\
      - WARN: Unauthorized access attempts
\
    6.3.2 Audit Log
\
      - Log all booking management actions with user ID and timestamp
\

\
7. Dependencies
\
    - Airline APIs for booking management
\
    - Email/SMS gateway
\
    - PostgreSQL database
\

\
8. Assumptions
\
    - Airline APIs support booking management via REST and return JSON.
\
    - Fare rules and airline policies are available for validation.
\
    - Audit logging is required for compliance and monitoring.