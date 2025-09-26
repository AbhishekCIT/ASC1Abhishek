# For User Story Number [1]

1. Objective
This user story enables travelers to search for available flights based on their preferences (origin, destination, date, passengers, class), view matching flights, and compare options. The system must support real-time search, filtering, and sorting. The goal is to provide a responsive, secure, and scalable flight search experience.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth 2.0 secured endpoints)
  - FlightInventoryService (for querying flight DB)
  - AirlineApiIntegrationService (for real-time airline API sync)
  - ValidationService (for input validation)
  - LoggingService (for monitoring and analytics)

  2.2 API Details
  | Operation | REST Method | Type    | URL                           | Request (JSON)                                                                                       | Response (JSON)                                                                                       |
  |-----------|-------------|---------|-------------------------------|------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
  | Search    | POST        | Success | /api/flights/search           | {"origin":"JFK","destination":"LAX","date":"2025-10-01","passengers":2,"class":"Economy"} | [{"flightId":123,"airline":"Delta","departure":"2025-10-01T08:00","arrival":"2025-10-01T11:00","duration":"6h","layovers":0,"price":350.00}] |
  | Search    | POST        | Failure | /api/flights/search           | {"origin":"JFK","destination":"JFK","date":"2024-01-01","passengers":0,"class":"Economy"} | {"error":"Validation failed: Origin and destination cannot be the same; Date cannot be in the past; Passengers must be at least 1."} |

  2.3 Exceptions
  | Exception Name                | Description                                            |
  |------------------------------|--------------------------------------------------------|
  | InvalidInputException         | Thrown when input validation fails                     |
  | NoFlightsFoundException       | Thrown if no flights match search criteria             |
  | AirlineApiUnavailableException| Thrown if airline API is unreachable                   |
  | DatabaseException             | Thrown on DB errors                                   |

3. Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class FlightSearchController {
      +searchFlights(request)
    }
    class FlightSearchService {
      +searchFlights(params)
    }
    class FlightInventoryService {
      +findFlights(params)
    }
    class AirlineApiIntegrationService {
      +syncFlights(params)
    }
    class ValidationService {
      +validateSearchParams(params)
    }
    class LoggingService {
      +logSearch(request, response)
    }
    FlightSearchController --> FlightSearchService
    FlightSearchService --> FlightInventoryService
    FlightSearchService --> AirlineApiIntegrationService
    FlightSearchService --> ValidationService
    FlightSearchService --> LoggingService
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as FlightSearchController
    participant S as FlightSearchService
    participant V as ValidationService
    participant I as FlightInventoryService
    participant A as AirlineApiIntegrationService
    participant L as LoggingService
    U->>C: POST /api/flights/search
    C->>S: searchFlights(request)
    S->>V: validateSearchParams(params)
    V-->>S: ValidationResult
    S->>I: findFlights(params)
    I-->>S: flightsFromDB
    S->>A: syncFlights(params)
    A-->>S: realTimeFlights
    S->>L: logSearch(request, response)
    S-->>C: searchResults
    C-->>U: Response
  ```

  3.3 Components
  | Component Name                | Description                                             | Existing/New |
  |------------------------------|---------------------------------------------------------|--------------|
  | FlightSearchController        | Handles REST endpoints for flight search                | New          |
  | FlightSearchService           | Orchestrates search, validation, and integration logic  | New          |
  | FlightInventoryService        | Queries internal flight inventory DB                    | Existing     |
  | AirlineApiIntegrationService  | Integrates with external airline APIs                   | Existing     |
  | ValidationService             | Validates user input                                   | Existing     |
  | LoggingService                | Logs search requests and responses                      | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName         | Validation                                      | Error Message                                   | ClassUsed            |
  |-------------------|-------------------------------------------------|-------------------------------------------------|----------------------|
  | origin            | Not empty, valid airport code, != destination   | Invalid origin or same as destination           | ValidationService    |
  | destination       | Not empty, valid airport code, != origin        | Invalid destination or same as origin           | ValidationService    |
  | date              | Not in past                                     | Travel date cannot be in the past               | ValidationService    |
  | passengers        | >=1, <= aircraft capacity                       | Invalid number of passengers                    | ValidationService    |
  | class             | Valid class (Economy, Business, etc.)           | Invalid class of service                        | ValidationService    |

4. Integrations
  | SystemToBeIntegrated | IntegratedFor              | IntegrationType |
  |----------------------|----------------------------|-----------------|
  | Airline APIs         | Real-time flight availability | API (REST/JSON) |

5. DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    FLIGHT ||--o{ SEAT : has
    FLIGHT {
      int flight_id PK
      string airline
      string origin
      string destination
      datetime departure_time
      datetime arrival_time
      decimal price
      int available_seats
      string class
    }
    SEAT {
      int seat_id PK
      int flight_id FK
      string seat_number
      string seat_class
      boolean is_available
    }
  ```

  5.2 DB Validations
  - Ensure available_seats >= passengers in FLIGHT table.
  - Valid airport codes in origin/destination columns.

6. Non-Functional Requirements
  6.1 Performance
  - API must return search results within 2 seconds.
  - Use caching for static data (airport codes, airlines).

  6.2 Security
    6.2.1 Authentication
    - OAuth 2.0 for all API endpoints.
    6.2.2 Authorization
    - Only authenticated users can search flights.

  6.3 Logging
    6.3.1 Application Logging
    - Log all search requests (INFO), validation failures (WARN), errors (ERROR), and API latency (DEBUG).
    6.3.2 Audit Log
    - Log failed searches and user search activity for analytics.

7. Dependencies
  - Airline partner APIs must be available and responsive.
  - Valid and updated airport/airline master data.

8. Assumptions
  - All airline APIs support real-time seat availability.
  - Airport codes provided by users are IATA standard.
  - Aircraft capacity is managed and updated in the inventory DB.
