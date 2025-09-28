# For User Story Number [2]
1. Objective
This requirement enables passengers to check in online, select their preferred seat, and receive a digital boarding pass. It streamlines the check-in process, reduces airport wait times, and allows for special requests to be managed in advance. The goal is to enhance passenger convenience and operational efficiency.

2. API Model
  2.1 Common Components/Services
  - User Authentication Service (Spring Security)
  - Check-In Service
  - Seat Map Service
  - Boarding Pass Generator (PDF/QR)
  - Notification Service (Email/SMS)
  - Special Requests Service

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Start Check-In | POST | Success/Failure | /api/checkin/start | {"bookingReference":"ABC123"} | {"eligible":true, "seatMap":{...}} |
  | Select Seat | POST | Success/Failure | /api/checkin/seat | {"bookingReference":"ABC123","seatNumber":"12A"} | {"status":"SEAT_CONFIRMED"} |
  | Submit Special Request | POST | Success/Failure | /api/checkin/request | {"bookingReference":"ABC123","requestType":"MEAL","details":"Vegetarian"} | {"status":"REQUEST_LOGGED"} |
  | Generate Boarding Pass | POST | Success/Failure | /api/checkin/boardingpass | {"bookingReference":"ABC123"} | {"boardingPassUrl":"/files/boardingpass_ABC123.pdf"} |

  2.3 Exceptions
  - CheckInNotAllowedException
  - SeatUnavailableException
  - BoardingPassGenerationException
  - ValidationException

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class CheckInController
    class SeatMapService
    class CheckInService
    class BoardingPassService
    class NotificationService
    class SpecialRequestService
    class BookingRepository
    class SeatRepository
    CheckInController --> CheckInService
    CheckInService --> SeatMapService
    CheckInService --> BoardingPassService
    CheckInService --> NotificationService
    CheckInService --> SpecialRequestService
    SeatMapService --> SeatRepository
    CheckInService --> BookingRepository
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant CheckInController
    participant CheckInService
    participant SeatMapService
    participant BoardingPassService
    participant NotificationService
    participant SpecialRequestService
    User->>CheckInController: Start Check-In
    CheckInController->>CheckInService: verifyEligibility()
    CheckInService->>SeatMapService: getSeatMap()
    CheckInService-->>CheckInController: eligibility, seatMap
    CheckInController-->>User: eligibility, seatMap
    User->>CheckInController: Select Seat
    CheckInController->>CheckInService: selectSeat()
    CheckInService->>SeatMapService: reserveSeat()
    CheckInService-->>CheckInController: seatStatus
    CheckInController-->>User: seatStatus
    User->>CheckInController: Submit Special Request
    CheckInController->>SpecialRequestService: logRequest()
    SpecialRequestService-->>CheckInController: requestStatus
    CheckInController-->>User: requestStatus
    User->>CheckInController: Generate Boarding Pass
    CheckInController->>BoardingPassService: generate()
    BoardingPassService-->>CheckInController: boardingPassUrl
    CheckInController->>NotificationService: sendBoardingPass()
    CheckInController-->>User: boardingPassUrl
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | CheckInController | Handles check-in requests | New |
  | CheckInService | Business logic for check-in | New |
  | SeatMapService | Provides and updates seat maps | New |
  | BoardingPassService | Generates boarding passes (PDF/QR) | New |
  | NotificationService | Sends boarding passes and notifications | New |
  | SpecialRequestService | Manages special requests | New |
  | BookingRepository | Data access for bookings | New |
  | SeatRepository | Data access for seat maps | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | check-in window | Time window check | "Check-in not allowed at this time" | CheckInService |
  | seatNumber | Capacity check | "Seat not available" | SeatMapService |
  | boarding pass | Regulatory info check | "Boarding pass missing required info" | BoardingPassService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline Backend | Seat maps, check-in rules | API |
  | Email/SMS Service | Boarding pass delivery | API |
  | PDF/QR Generator | Boarding pass creation | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING ||--o{ CHECKIN : has
    CHECKIN }o--|| SEAT : assigned
    CHECKIN {
      int id PK
      int booking_id FK
      datetime checkin_time
      string status
    }
    SEAT {
      int id PK
      string seat_number
      int flight_id FK
      boolean is_available
    }
    SPECIAL_REQUEST {
      int id PK
      int checkin_id FK
      string type
      string details
    }
  ```

  5.2 DB Validations
  - Only one check-in per booking.
  - Seat assignment must be unique per flight.
  - Special requests linked to valid check-in.

6 Non-Functional Requirements
  6.1 Performance
  - Check-in response time < 2 seconds.
  - Real-time seat map updates.

  6.2 Security
    6.2.1 Authentication
    - OAuth2/JWT-based authentication for all APIs.
    - HTTPS enforced for all endpoints.
    6.2.2 Authorization
    - Only booking owner can check in.

  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Check-in and seat selection requests
    - INFO: Successful check-ins and boarding pass generation
    - ERROR: Failed check-ins, exceptions
    - WARN: Multiple failed seat selections
    6.3.2 Audit Log
    - Log all check-in and seat selection events with timestamp, user, and status.

7 Dependencies
  - Airline backend for seat maps and check-in rules
  - Email/SMS providers
  - PDF/QR code generator

8 Assumptions
  - Airline backend is available and up-to-date.
  - Passengers have access to email/SMS for boarding pass delivery.
