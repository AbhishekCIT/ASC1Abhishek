# For User Story Number [4]
1. Objective
This requirement enables passengers to track the real-time status of their flights, including departure/arrival times, delays, gate changes, and cancellations. It aims to keep passengers informed and help them plan their airport arrival accordingly. The goal is to provide timely, accurate, and actionable flight status information.

2. API Model
  2.1 Common Components/Services
  - User Authentication Service (Spring Security)
  - Flight Status Service
  - Notification Service (Push/Email/SMS)
  - Flight History Service

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Search Flight Status | GET | Success/Failure | /api/flightstatus/search | {"flightNumber":"XY123","date":"2025-10-10"} | {"status":"ON_TIME","departureTime":"10:00","arrivalTime":"13:00","gate":"A5"} |
  | Subscribe to Notifications | POST | Success/Failure | /api/flightstatus/subscribe | {"flightNumber":"XY123","userId":123} | {"status":"SUBSCRIBED"} |
  | Get Flight History | GET | Success/Failure | /api/flightstatus/history | {"flightNumber":"XY123"} | {"history":[{"date":"2025-10-09","status":"DELAYED"},...]} |

  2.3 Exceptions
  - FlightNotFoundException
  - NotificationSubscriptionException
  - ValidationException

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class FlightStatusController
    class FlightStatusService
    class NotificationService
    class FlightHistoryService
    class FlightStatusRepository
    class NotificationRepository
    FlightStatusController --> FlightStatusService
    FlightStatusController --> NotificationService
    FlightStatusController --> FlightHistoryService
    FlightStatusService --> FlightStatusRepository
    NotificationService --> NotificationRepository
    FlightHistoryService --> FlightStatusRepository
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant FlightStatusController
    participant FlightStatusService
    participant NotificationService
    participant FlightHistoryService
    participant FlightStatusRepository
    participant NotificationRepository
    User->>FlightStatusController: Search Flight Status
    FlightStatusController->>FlightStatusService: getRealTimeStatus()
    FlightStatusService->>FlightStatusRepository: fetchStatus()
    FlightStatusService-->>FlightStatusController: statusData
    FlightStatusController-->>User: statusData
    User->>FlightStatusController: Subscribe to Notifications
    FlightStatusController->>NotificationService: subscribe()
    NotificationService->>NotificationRepository: saveSubscription()
    NotificationService-->>FlightStatusController: subscriptionStatus
    FlightStatusController-->>User: subscriptionStatus
    User->>FlightStatusController: Get Flight History
    FlightStatusController->>FlightHistoryService: getHistory()
    FlightHistoryService->>FlightStatusRepository: fetchHistory()
    FlightHistoryService-->>FlightStatusController: historyData
    FlightStatusController-->>User: historyData
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | FlightStatusController | Handles flight status and notification requests | New |
  | FlightStatusService | Business logic for real-time status | New |
  | NotificationService | Sends notifications to users | New |
  | FlightHistoryService | Provides historical status data | New |
  | FlightStatusRepository | Data access for flight status | New |
  | NotificationRepository | Data access for notification subscriptions | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | flightNumber, date | Mandatory | "Flight number and date required" | FlightStatusService |
  | notification subscription | Valid flight/user | "Unable to subscribe for notifications" | NotificationService |
  | flight status data | Source validation | "Flight status unavailable" | FlightStatusService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline APIs | Real-time flight status | API |
  | Airport APIs | Gate and delay info | API |
  | Push Notification Service | User notifications | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    FLIGHT_STATUS ||--o{ NOTIFICATION : triggers
    FLIGHT_STATUS {
      int id PK
      string flight_number
      datetime date
      string status
      string gate
      datetime departure_time
      datetime arrival_time
    }
    NOTIFICATION {
      int id PK
      int flight_status_id FK
      int user_id
      string type
      datetime sent_time
    }
    FLIGHT_HISTORY {
      int id PK
      string flight_number
      datetime date
      string status
    }
  ```

  5.2 DB Validations
  - Flight status records updated every 2 minutes.
  - Notification subscriptions unique per user/flight.

6 Non-Functional Requirements
  6.1 Performance
  - Support 1,000,000 queries/day.
  - Notifications delivered within 30 seconds of status change.

  6.2 Security
    6.2.1 Authentication
    - OAuth2/JWT-based authentication for all APIs.
    - HTTPS enforced for all endpoints.
    6.2.2 Authorization
    - Only subscribed users receive notifications.

  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Flight status queries
    - INFO: Notification events
    - ERROR: Failed API calls, exceptions
    - WARN: Delayed status updates
    6.3.2 Audit Log
    - Log all status changes and notification events with timestamp, user, and status.

7 Dependencies
  - Airline and airport APIs
  - Push notification service

8 Assumptions
  - Airline and airport APIs are reliable and updated in real time.
  - Users have opted in for notifications.
