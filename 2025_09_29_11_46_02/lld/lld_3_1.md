# For User Story Number [1]
1. Objective
This requirement enables passengers to search for available flights based on origin, destination, date, and number of passengers. The system must return a list of flights matching the criteria, including details such as airline, times, and price. Users can sort and filter results to find flights that best suit their needs.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (for API authentication)
  - FlightSearchService (for querying flights)
  - ExternalFlightAPIClient (for integration with Amadeus/Sabre)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Search Flights | POST | Success | /api/flights/search | {
    "origin": "JFK",
    "destination": "LAX",
    "date": "2025-10-01",
    "passengers": 2,
    "sortBy": "price",
    "filter": {"airline": "Delta"}
  } | {
    "flights": [
      {
        "airline": "Delta",
        "flightNumber": "DL123",
        "departureTime": "2025-10-01T09:00:00",
        "arrivalTime": "2025-10-01T12:00:00",
        "price": 350.00
      }
    ]
  } |
  | Search Flights | POST | Failure | /api/flights/search | Same as above | {
    "error": "No flights found matching criteria."
  } |

  2.3 Exceptions
  - InvalidAirportCodeException: Thrown if origin or destination is not a valid airport code.
  - InvalidDateException: Thrown if the date is in the past.
  - InvalidPassengerCountException: Thrown if passengers is not a positive integer.
  - ExternalAPIException: Thrown if external flight API fails.

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class FlightSearchController {
      +searchFlights(request)
    }
    class FlightSearchService {
      +findFlights(criteria)
    }
    class ExternalFlightAPIClient {
      +fetchFlights(criteria)
    }
    class Flight {
      -airline
      -flightNumber
      -departureTime
      -arrivalTime
      -price
    }
    FlightSearchController --> FlightSearchService
    FlightSearchService --> ExternalFlightAPIClient
    FlightSearchService --> Flight
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as FlightSearchController
    participant S as FlightSearchService
    participant E as ExternalFlightAPIClient
    participant F as Flight
    U->>C: POST /api/flights/search
    C->>S: findFlights(criteria)
    S->>E: fetchFlights(criteria)
    E-->>S: flights[]
    S-->>C: flights[]
    C-->>U: flights[]
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | FlightSearchController | Handles API requests for flight search | New |
  | FlightSearchService | Business logic for searching flights | New |
  | ExternalFlightAPIClient | Integrates with external flight APIs | New |
  | Flight | Model for flight data | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | origin | Must be valid IATA code | Invalid origin airport code | FlightSearchService |
  | destination | Must be valid IATA code | Invalid destination airport code | FlightSearchService |
  | date | Must not be in the past | Date cannot be in the past | FlightSearchService |
  | passengers | Must be > 0 | Number of passengers must be positive | FlightSearchService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Amadeus/Sabre API | Fetching flight data | API |
  | Authentication Service | Securing API access | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    FLIGHT {
      string airline
      string flightNumber
      datetime departureTime
      datetime arrivalTime
      decimal price
    }
  ```
  5.2 DB Validations
  - Not applicable (data fetched from external API, not persisted)

6 Non-Functional Requirements
  6.1 Performance
  - Search API must return results within 2 seconds.
  - Caching may be implemented at API layer for frequent queries.
  6.2 Security
    6.2.1 Authentication
    - All API calls require JWT-based authentication.
    6.2.2 Authorization
    - Only authenticated users can search flights.
  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: External API request/response, search criteria
    - INFO: Successful search requests
    - ERROR: API failures, validation errors
    - WARN: Slow API responses
    6.3.2 Audit Log
    - Log user ID, search criteria, timestamp for each search

7 Dependencies
  - External flight data providers (Amadeus, Sabre)
  - Authentication service

8 Assumptions
  - All airport codes are IATA standard.
  - External APIs are available and performant.
  - No flight data is persisted locally.
