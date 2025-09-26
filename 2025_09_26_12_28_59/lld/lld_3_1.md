# For User Story Number [1]

1. Objective
The objective is to enable travelers to search for available flights, select preferred options, and book air transport tickets online. The system must provide real-time availability, pricing, and booking confirmation. This will allow users to conveniently plan and manage their trips end-to-end.

2. API Model
2.1 Common Components/Services
- Authentication Service (OAuth2/JWT)
- Flight Search Service
- Booking Service
- Payment Service
- GDS Integration Service
- Notification Service

2.2 API Details
| Operation   | REST Method | Type     | URL                               | Request (Sample JSON)                                                                 | Response (Sample JSON)                                                               |
|-------------|-------------|----------|------------------------------------|--------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| Search      | GET         | Success  | /api/flights/search                | {"origin": "JFK", "destination": "LAX", "date": "2025-10-01"}              | [{"flightId": "F123", "airline": "AA", "price": 300, "seatsAvailable": 5}] |
| Book        | POST        | Success  | /api/flights/book                  | {"flightId": "F123", "userId": "U456", "paymentInfo": {...}}                | {"bookingId": "B789", "status": "CONFIRMED", "pnr": "XYZ123"}             |
| Book        | POST        | Failure  | /api/flights/book                  | {"flightId": "F123", "userId": "U456", "paymentInfo": {...}}                | {"error": "Payment authorization failed."}                                        |

2.3 Exceptions
- FlightNotFoundException
- NoSeatsAvailableException
- PaymentAuthorizationException
- InvalidInputException
- ExternalServiceException (GDS/Payment Gateway)

3 Functional Design
3.1 Class Diagram
```mermaid
classDiagram
    class FlightController
    class FlightService
    class BookingService
    class PaymentService
    class GDSClient
    class FlightRepository
    class BookingRepository
    class PaymentGatewayClient
    class NotificationService
    FlightController --> FlightService
    FlightController --> BookingService
    BookingService --> PaymentService
    BookingService --> GDSClient
    FlightService --> FlightRepository
    BookingService --> BookingRepository
    PaymentService --> PaymentGatewayClient
    BookingService --> NotificationService
```

3.2 UML Sequence Diagram
```mermaid
sequenceDiagram
    participant U as User
    participant FC as FlightController
    participant FS as FlightService
    participant BR as BookingRepository
    participant BS as BookingService
    participant PS as PaymentService
    participant GDS as GDSClient
    participant PG as PaymentGatewayClient
    participant NS as NotificationService
    U->>FC: Search Flights
    FC->>FS: searchFlights(origin, destination, date)
    FS->>GDS: getAvailableFlights()
    GDS-->>FS: flightsList
    FS-->>FC: flightsList
    FC-->>U: Show flights
    U->>FC: Book Flight
    FC->>BS: bookFlight(flightId, userId, paymentInfo)
    BS->>GDS: reserveSeat(flightId)
    GDS-->>BS: seatReserved
    BS->>PS: authorizePayment(paymentInfo)
    PS->>PG: processPayment()
    PG-->>PS: paymentStatus
    PS-->>BS: paymentStatus
    BS->>BR: saveBooking()
    BS->>NS: sendBookingConfirmation()
    BS-->>FC: bookingConfirmation
    FC-->>U: Show confirmation
```

3.3 Components
| Component Name        | Description                                         | Existing/New |
|----------------------|-----------------------------------------------------|--------------|
| FlightController     | REST controller for flight search and booking        | New          |
| FlightService        | Business logic for searching flights                 | New          |
| BookingService       | Handles booking logic and seat reservation           | New          |
| PaymentService       | Handles payment authorization and processing         | New          |
| GDSClient            | Integrates with airline GDS APIs                     | New          |
| FlightRepository     | CRUD operations for flight data                      | New          |
| BookingRepository    | CRUD operations for booking data                     | New          |
| PaymentGatewayClient | Integrates with external payment gateway             | New          |
| NotificationService  | Sends booking confirmations to users                 | New          |

3.4 Service Layer Logic and Validations
| FieldName         | Validation                              | Error Message                        | ClassUsed         |
|-------------------|-----------------------------------------|--------------------------------------|-------------------|
| origin            | Must not be empty                       | "Origin cannot be empty"             | FlightService     |
| destination       | Must not be empty                       | "Destination cannot be empty"        | FlightService     |
| date              | Must be in the future                   | "Travel date must be in the future"  | FlightService     |
| paymentInfo       | Must be authorized                      | "Payment authorization failed"       | PaymentService    |
| flightId          | Must have available seats                | "No seats available"                 | BookingService    |

4 Integrations
| SystemToBeIntegrated | IntegratedFor           | IntegrationType |
|----------------------|------------------------|-----------------|
| Airline GDS          | Flight search/booking  | API (REST)      |
| Payment Gateway      | Payment authorization  | API (REST)      |
| Notification Service | Booking confirmation   | API (REST)      |

5 DB Details
5.1 ER Model
```mermaid
erDiagram
    USER ||--o{ BOOKING : makes
    BOOKING }o--|| FLIGHT : books
    BOOKING }o--|| PAYMENT : has
    FLIGHT ||--o{ SEAT : contains
    USER {
        string user_id
        string name
        string email
    }
    FLIGHT {
        string flight_id
        string airline
        string origin
        string destination
        datetime departure_time
        int seats_available
        decimal price
    }
    BOOKING {
        string booking_id
        string user_id
        string flight_id
        datetime booking_time
        string status
        string pnr
    }
    PAYMENT {
        string payment_id
        string booking_id
        decimal amount
        string status
        datetime payment_time
    }
    SEAT {
        string seat_id
        string flight_id
        string seat_number
        boolean is_reserved
    }
```

5.2 DB Validations
- Booking cannot be created if seats_available <= 0
- Payment status must be 'AUTHORIZED' before confirming booking

6 Non-Functional Requirements
6.1 Performance
- Response time for search and booking APIs must be < 2 seconds.
- Use caching for frequent flight search queries at the API layer.

6.2 Security
6.2.1 Authentication
- OAuth2 authentication for all endpoints.
- HTTPS enforced for all API traffic.
6.2.2 Authorization
- Role-based access for booking endpoints (only authenticated users can book).

6.3 Logging
6.3.1 Application Logging
- DEBUG: API request/response payloads (excluding sensitive data)
- INFO: Successful bookings, payment authorizations
- ERROR: Failed bookings, payment failures, integration errors
- WARN: GDS or Payment API latency above threshold
6.3.2 Audit Log
- Log all booking and payment transactions with user, timestamp, and status.

7 Dependencies
- Airline GDS API availability
- Payment gateway uptime
- Notification service reliability

8 Assumptions
- All users are pre-registered and authenticated.
- GDS API provides real-time seat and pricing data.
- Payment gateway is PCI DSS compliant.
- Notification service is available for confirmations.
