# For User Story Number [3]

1. Objective
This user story enables travelers to complete online check-in for their booked flights, select or confirm seats, and receive a digital boarding pass. The system must validate check-in eligibility, update seat maps in real-time, and ensure secure delivery of boarding passes. The goal is to streamline the check-in process, reduce airport wait times, and enhance traveler convenience.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth 2.0 secured endpoints)
  - CheckInService (handles check-in process)
  - BookingService (retrieves and updates booking status)
  - SeatMapService (manages seat selection and restrictions)
  - BoardingPassService (generates digital boarding passes)
  - ValidationService (for input and eligibility validation)
  - LoggingService (for monitoring and analytics)

  2.2 API Details
  | Operation | REST Method | Type    | URL                          | Request (JSON)                                                                                                   | Response (JSON)                                                                                       |
  |-----------|-------------|---------|------------------------------|------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
  | CheckIn   | POST        | Success | /api/checkin                 | {"bookingRef":"XYZ123","lastName":"Doe","seats":["12A"],"travelDocs":[{"type":"passport","number":"A1234567"}]} | {"status":"CHECKED_IN","boardingPass":{"pdfUrl":"/boardingpass/XYZ123.pdf","qrCode":"base64string"}} |
  | CheckIn   | POST        | Failure | /api/checkin                 | {"bookingRef":"XYZ123","lastName":"Doe","seats":[],"travelDocs":[]}                                     | {"error":"Validation failed: Check-in window closed; Required documents missing."}                  |

  2.3 Exceptions
  | Exception Name                | Description                                            |
  |------------------------------|--------------------------------------------------------|
  | InvalidInputException         | Thrown when input validation fails                     |
  | CheckInWindowException        | Thrown if check-in is outside allowed window           |
  | SeatRestrictionException      | Thrown if seat selection violates airline restrictions |
  | BookingNotFoundException      | Thrown if booking reference is invalid                 |
  | BoardingPassGenerationException| Thrown if boarding pass generation fails              |
  | DatabaseException             | Thrown on DB errors                                   |

3. Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class CheckInController {
      +checkIn(request)
    }
    class CheckInService {
      +checkIn(params)
    }
    class BookingService {
      +getBooking(bookingRef, lastName)
      +updateStatus(bookingRef, status)
    }
    class SeatMapService {
      +selectSeats(flightId, seats)
      +validateSeatRestrictions(seats)
    }
    class BoardingPassService {
      +generateBoardingPass(booking)
    }
    class ValidationService {
      +validateCheckInParams(params)
      +validateCheckInWindow(flightTime)
    }
    class LoggingService {
      +logCheckIn(request, response)
    }
    CheckInController --> CheckInService
    CheckInService --> BookingService
    CheckInService --> SeatMapService
    CheckInService --> BoardingPassService
    CheckInService --> ValidationService
    CheckInService --> LoggingService
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as CheckInController
    participant S as CheckInService
    participant V as ValidationService
    participant B as BookingService
    participant M as SeatMapService
    participant P as BoardingPassService
    participant L as LoggingService
    U->>C: POST /api/checkin
    C->>S: checkIn(request)
    S->>V: validateCheckInParams(params)
    V-->>S: ValidationResult
    S->>B: getBooking(bookingRef, lastName)
    B-->>S: Booking
    S->>V: validateCheckInWindow(flightTime)
    V-->>S: WindowResult
    S->>M: selectSeats(flightId, seats)
    M-->>S: SeatSelectionResult
    S->>M: validateSeatRestrictions(seats)
    M-->>S: RestrictionResult
    S->>P: generateBoardingPass(booking)
    P-->>S: BoardingPass
    S->>B: updateStatus(bookingRef, 'CHECKED_IN')
    B-->>S: UpdateResult
    S->>L: logCheckIn(request, response)
    S-->>C: checkInConfirmation
    C-->>U: Response
  ```

  3.3 Components
  | Component Name                | Description                                             | Existing/New |
  |------------------------------|---------------------------------------------------------|--------------|
  | CheckInController             | Handles REST endpoints for check-in                     | New          |
  | CheckInService                | Orchestrates check-in, validation, and integration logic| New          |
  | BookingService                | Retrieves and updates booking status                    | Existing     |
  | SeatMapService                | Manages seat map and restrictions                      | Existing     |
  | BoardingPassService           | Generates digital boarding passes                       | New          |
  | ValidationService             | Validates user input and check-in eligibility           | Existing     |
  | LoggingService                | Logs check-in requests and responses                    | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName         | Validation                                      | Error Message                                   | ClassUsed            |
  |-------------------|-------------------------------------------------|-------------------------------------------------|----------------------|
  | bookingRef        | Not empty, valid reference                       | Invalid booking reference                       | ValidationService    |
  | lastName          | Not empty, matches booking                       | Invalid last name                               | ValidationService    |
  | seats             | Not empty, valid, no restriction conflicts       | Invalid or restricted seat selection            | SeatMapService      |
  | travelDocs        | All required documents provided                  | Required travel documents missing               | ValidationService    |
  | check-in window   | Within allowed time window                       | Check-in window closed                          | ValidationService    |

4. Integrations
  | SystemToBeIntegrated | IntegratedFor              | IntegrationType |
  |----------------------|----------------------------|-----------------|
  | Airline DCS          | Check-in status update     | API (REST/SOAP) |

5. DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    BOOKING ||--o{ PASSENGER : has
    BOOKING {
      int booking_id PK
      string booking_ref
      int flight_id FK
      string status
      datetime created_at
      string contact_email
      string contact_phone
    }
    PASSENGER {
      int passenger_id PK
      int booking_id FK
      string name
      int age
      string seat_number
      string travel_doc_type
      string travel_doc_number
    }
  ```

  5.2 DB Validations
  - Ensure booking status is updated to 'CHECKED_IN' only within allowed window.
  - All required travel documents are non-null.

6. Non-Functional Requirements
  6.1 Performance
  - Check-in process must complete within 3 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth 2.0 for all API endpoints.
    6.2.2 Authorization
    - Only authenticated users can check in.
    - Secure boarding pass generation and delivery.

  6.3 Logging
    6.3.1 Application Logging
    - Log all check-in requests (INFO), validation failures (WARN), errors (ERROR), and boarding pass delivery issues (ERROR).
    6.3.2 Audit Log
    - Log all check-in attempts and boarding pass generations.

7. Dependencies
  - Airline DCS must be available for check-in status updates.
  - Seat map and booking data must be up-to-date.

8. Assumptions
  - Airline DCS supports real-time check-in updates.
  - Boarding pass is delivered as PDF and QR code is scannable at airport.
  - All seat restrictions are enforced by SeatMapService.
