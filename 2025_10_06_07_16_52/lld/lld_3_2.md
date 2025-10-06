# For User Story Number [2]

1. Objective
This requirement enables travelers to track their booked flight status in real-time, including updates on delays, gate changes, and cancellations. Users can view status by entering a flight number and receive timely push/email notifications for significant changes. The solution ensures accurate, reliable, and prompt information delivery to enhance user experience and travel planning.

2. API Model
  2.1 Common Components/Services
  - AirlineStatusAPIService (integration with airline status APIs)
  - NotificationService (push/email notifications)
  - FlightStatusPollingService (scheduled polling logic)
  - UserAuthenticationService (OAuth2 authentication)

  2.2 API Details
| Operation   | REST Method | Type    | URL                               | Request (Sample)                                  | Response (Sample)                                                    |
|-------------|-------------|---------|-----------------------------------|---------------------------------------------------|-----------------------------------------------------------------------|
| Get Status  | GET         | Success | /api/flights/status/{flightNo}    | -                                                 | {"flightNo":"DL123","status":"DELAYED","gate":"A5","eta":"12:30"} |
| Subscribe   | POST        | Success | /api/flights/subscribe            | {"userId":100,"flightNo":"DL123","channel":"push"} | {"subscribed":true,"flightNo":"DL123"}                                |
| Notify      | POST        | Success | /api/notifications/send           | {"userId":100,"message":"Gate changed to B2"}      | {"notified":true}                                                    |
| Get Status  | GET         | Failure | /api/flights/status/{flightNo}    | -                                                 | {"error":"Flight not found"}                                         |

  2.3 Exceptions
  - FlightNotFoundException
  - NotificationFailedException
  - UnauthorizedAccessException
  - ValidationException

3. Functional Design
  3.1 Class Diagram
```mermaid
classDiagram
    class FlightStatusController
    class FlightStatusService
    class AirlineStatusAPIService
    class NotificationService
    class FlightStatusPollingService
    class UserRepository
    class BookingRepository
    class User
    class Booking
    FlightStatusController --> FlightStatusService
    FlightStatusService --> AirlineStatusAPIService
    FlightStatusService --> NotificationService
    FlightStatusService --> UserRepository
    FlightStatusService --> BookingRepository
    FlightStatusPollingService --> AirlineStatusAPIService
    BookingRepository --> Booking
    Booking --> User
```

  3.2 UML Sequence Diagram
```mermaid
sequenceDiagram
    participant U as User
    participant FSC as FlightStatusController
    participant FSS as FlightStatusService
    participant AS as AirlineStatusAPIService
    participant NR as NotificationService
    participant BR as BookingRepository
    U->>FSC: Get Flight Status
    FSC->>FSS: getFlightStatus()
    FSS->>AS: fetchFlightStatus()
    AS-->>FSS: statusData
    FSS-->>FSC: statusData
    U->>FSC: Subscribe to Notifications
    FSC->>FSS: subscribe()
    FSS->>BR: validateBooking()
    BR-->>FSS: bookingValid
    FSS->>NR: sendSubscriptionConfirmation()
    NR-->>FSS: confirmationSent
    FSS-->>FSC: subscriptionStatus
    Note over FSS,AS: Polling Service runs every 2 min
    FSS->>AS: fetchFlightStatus()
    AS-->>FSS: statusData
    FSS->>NR: notifyUserOnChange()
    NR-->>FSS: notificationSent
```

  3.3 Components
| Component Name             | Description                                         | Existing/New |
|---------------------------|-----------------------------------------------------|--------------|
| FlightStatusController    | REST controller for flight status operations         | New          |
| FlightStatusService       | Core logic for status retrieval and notifications    | New          |
| AirlineStatusAPIService   | Handles airline status API integration               | New          |
| NotificationService       | Sends push/email notifications                       | New          |
| FlightStatusPollingService| Schedules and polls airline APIs for updates         | New          |
| UserRepository            | Data access for user details                        | Existing     |
| BookingRepository         | Data access for booking details                     | Existing     |
| User                      | Entity for user details                             | Existing     |
| Booking                   | Entity for booking details                          | Existing     |

  3.4 Service Layer Logic and Validations
| FieldName   | Validation                                | Error Message                  | ClassUsed                |
|-------------|-------------------------------------------|-------------------------------|--------------------------|
| flightNo    | Must be valid and exist in system          | Flight not found              | FlightStatusService      |
| userId      | Must have a valid booking for notifications| No valid booking              | BookingRepository        |
| channel     | Must be push or email                      | Invalid notification channel  | NotificationService      |

4. Integrations
| SystemToBeIntegrated | IntegratedFor           | IntegrationType |
|---------------------|------------------------|-----------------|
| Airline Status APIs | Real-time flight status | API             |
| Firebase            | Push notifications      | API             |
| Email Service       | Email notifications     | API             |

5. DB Details
  5.1 ER Model
```mermaid
erDiagram
    USER ||--o{ BOOKING : has
    BOOKING }o--|| FLIGHT : books
    FLIGHT ||--o{ FLIGHT_STATUS : has
    BOOKING {
      int booking_id PK
      int user_id FK
      int flight_id FK
    }
    USER {
      int user_id PK
      string name
      string email
    }
    FLIGHT {
      int flight_id PK
      string flight_no
      string airline
      date flight_date
    }
    FLIGHT_STATUS {
      int status_id PK
      int flight_id FK
      string status
      string gate
      timestamp updated_at
    }
```

  5.2 DB Validations
  - Foreign key constraints for all FK fields
  - Unique constraint on (flight_id, updated_at) in FLIGHT_STATUS

6. Non-Functional Requirements
  6.1 Performance
    - Polling interval: every 2 minutes
    - Notification delivery within 1 minute of status change
    - Optimized polling to reduce mobile battery usage
  6.2 Security
    6.2.1 Authentication
      - OAuth2 authentication for all APIs
    6.2.2 Authorization
      - Only users with valid bookings can subscribe to notifications
    - Data privacy for user flight information
  6.3 Logging
    6.3.1 Application Logging
      - DEBUG: API requests/responses (masked sensitive data)
      - INFO: Status updates, notifications sent
      - ERROR: Notification failures, API errors
      - WARN: Repeated polling failures
    6.3.2 Audit Log
      - All status changes and notifications logged with user and timestamp

7. Dependencies
  - Airline status APIs (external)
  - Firebase (external)
  - Email service (external)

8. Assumptions
  - Airline status APIs provide near real-time data
  - User device supports push notifications
  - User email is verified