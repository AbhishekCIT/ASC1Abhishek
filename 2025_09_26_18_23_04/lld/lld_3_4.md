# For User Story Number [4]

1. Objective
This user story enables travelers to track the real-time status of their flights, including departure/arrival times, delays, gate changes, and cancellations. The system must allow users to search by flight number or booking reference and receive notifications for any status changes. The goal is to provide timely, accurate, and user-friendly flight status updates.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth 2.0 secured endpoints)
  - FlightStatusService (handles status queries and updates)
  - NotificationService (sends SMS/email/push notifications)
  - AirlineApiIntegrationService (for real-time airline/airport API sync)
  - ValidationService (for input validation)
  - LoggingService (for monitoring and analytics)

  2.2 API Details
  | Operation | REST Method | Type    | URL                          | Request (JSON)                                                                                                   | Response (JSON)                                                                                       |
  |-----------|-------------|---------|------------------------------|------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
  | Status    | POST        | Success | /api/flights/status          | {"flightNumber":"DL123","bookingRef":"XYZ123","notify":true,"contact":{"email":"john@example.com"}} | {"flightNumber":"DL123","status":"DELAYED","departure":"2025-10-01T08:00","arrival":"2025-10-01T11:00","gate":"A12","delay":"30m"} |
  | Status    | POST        | Failure | /api/flights/status          | {"flightNumber":"INVALID","bookingRef":"","notify":false}                                               | {"error":"Validation failed: Invalid flight number or booking reference."}                        |

  2.3 Exceptions
  | Exception Name                | Description                                            |
  |------------------------------|--------------------------------------------------------|
  | InvalidInputException         | Thrown when input validation fails                     |
  | FlightNotFoundException       | Thrown if flight number or booking reference is invalid|
  | AirlineApiUnavailableException| Thrown if airline/airport API is unreachable           |
  | NotificationFailedException   | Thrown if notification delivery fails                  |
  | DatabaseException             | Thrown on DB errors                                   |

3. Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class FlightStatusController {
      +getFlightStatus(request)
    }
    class FlightStatusService {
      +getFlightStatus(params)
    }
    class AirlineApiIntegrationService {
      +fetchFlightStatus(params)
    }
    class NotificationService {
      +sendStatusNotification(contact, status)
    }
    class ValidationService {
      +validateStatusParams(params)
    }
    class LoggingService {
      +logStatusQuery(request, response)
    }
    FlightStatusController --> FlightStatusService
    FlightStatusService --> AirlineApiIntegrationService
    FlightStatusService --> NotificationService
    FlightStatusService --> ValidationService
    FlightStatusService --> LoggingService
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as FlightStatusController
    participant S as FlightStatusService
    participant V as ValidationService
    participant A as AirlineApiIntegrationService
    participant N as NotificationService
    participant L as LoggingService
    U->>C: POST /api/flights/status
    C->>S: getFlightStatus(request)
    S->>V: validateStatusParams(params)
    V-->>S: ValidationResult
    S->>A: fetchFlightStatus(params)
    A-->>S: FlightStatus
    S->>N: sendStatusNotification(contact, status)
    N-->>S: NotificationResult
    S->>L: logStatusQuery(request, response)
    S-->>C: statusResponse
    C-->>U: Response
  ```

  3.3 Components
  | Component Name                | Description                                             | Existing/New |
  |------------------------------|---------------------------------------------------------|--------------|
  | FlightStatusController        | Handles REST endpoints for flight status                | New          |
  | FlightStatusService           | Orchestrates status queries and integration logic       | New          |
  | AirlineApiIntegrationService  | Integrates with airline/airport APIs                    | Existing     |
  | NotificationService           | Sends real-time notifications                           | Existing     |
  | ValidationService             | Validates user input                                   | Existing     |
  | LoggingService                | Logs status queries and responses                       | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName         | Validation                                      | Error Message                                   | ClassUsed            |
  |-------------------|-------------------------------------------------|-------------------------------------------------|----------------------|
  | flightNumber      | Not empty, valid airline/airport code            | Invalid flight number                            | ValidationService    |
  | bookingRef        | Optional, if provided must be valid              | Invalid booking reference                        | ValidationService    |
  | contact           | Valid email/phone if notifications enabled       | Invalid contact details                          | ValidationService    |
  | notify            | Boolean, only if contact is verified             | Notification cannot be sent to unverified contact| NotificationService  |

4. Integrations
  | SystemToBeIntegrated | IntegratedFor              | IntegrationType |
  |----------------------|----------------------------|-----------------|
  | Airline/Airport APIs | Real-time status updates   | API (REST/JSON, XML) |
  | Notification Service | Status change notifications| API (REST/JSON) |

5. DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    FLIGHT_STATUS {
      int id PK
      string flight_number
      datetime status_time
      string status
      string gate
      string delay
      string message
    }
    NOTIFICATION_LOG {
      int id PK
      string contact_email
      string contact_phone
      string flight_number
      datetime sent_at
      string status
      string message
    }
  ```

  5.2 DB Validations
  - Ensure flight_number is valid and exists in master data.
  - Only verified contacts are stored in NOTIFICATION_LOG.

6. Non-Functional Requirements
  6.1 Performance
  - Flight status must be updated within 30 seconds of change.
  - Automatic refresh every 60 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth 2.0 for all API endpoints.
    6.2.2 Authorization
    - Only authenticated users can request notifications.
    - Secure handling of user contact details.

  6.3 Logging
    6.3.1 Application Logging
    - Log all status queries (INFO), validation failures (WARN), errors (ERROR), and notification delivery (INFO/ERROR).
    6.3.2 Audit Log
    - Log all notification events and status changes.

7. Dependencies
  - Airline and airport APIs must be available and responsive.
  - Notification service must be available for timely delivery.

8. Assumptions
  - All airline/airport APIs provide real-time status updates.
  - Contact details provided by users are verified and reachable.
  - Partner airline flights are supported by the integration layer.
