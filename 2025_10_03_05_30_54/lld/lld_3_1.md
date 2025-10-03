# For User Story Number [1]
\
1. Objective
\
Enable travelers to search for available air transport options by providing origin, destination, travel dates, and preferences, and to display relevant results efficiently. Ensure user-friendly input and fast, accurate results. Support filtering, sorting, and display of key flight details.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - FlightSearchService (core search logic)
\
    - FlightAPIClient (integration with third-party APIs)
\
    - ValidationUtil (input validation)
\
    - SearchQueryLogger (analytics logging)
\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Search Flights | POST | Success | /api/flights/search | { "origin": "JFK", "destination": "LHR", "departureDate": "2025-12-01", "returnDate": "2025-12-10", "filters": { "airline": "Delta", "priceRange": [200, 800], "stops": 1 }, "sortBy": "price" } | { "flights": [ { "flightId": "DL100", "airline": "Delta", "departureTime": "2025-12-01T09:00:00Z", "arrivalTime": "2025-12-01T17:00:00Z", "price": 500, "stops": 1, "layovers": ["CDG"] } ], "totalResults": 12 } |
\
| Search Flights | POST | Failure | /api/flights/search | { "origin": "JFK", "destination": "JFK", ... } | { "error": "Origin and destination cannot be the same." } |
\
  2.3 Exceptions
\
    - InvalidInputException: For invalid or missing fields
\
    - NoFlightsFoundException: No flights match criteria
\
    - ExternalAPIException: Third-party API failure
\
    - SearchTimeoutException: Search exceeds 3 seconds
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
  class FlightSearchController {
\
    +searchFlights()
\
  }
\
  class FlightSearchService {
\
    +searchFlights()
\
  }
\
  class FlightAPIClient {
\
    +fetchFlights()
\
  }
\
  class ValidationUtil {
\
    +validateSearchCriteria()
\
  }
\
  class SearchQueryLogger {
\
    +logQuery()
\
  }
\
  FlightSearchController --> FlightSearchService
\
  FlightSearchService --> FlightAPIClient
\
  FlightSearchService --> ValidationUtil
\
  FlightSearchService --> SearchQueryLogger
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
  participant C as FlightSearchController
\
  participant S as FlightSearchService
\
  participant V as ValidationUtil
\
  participant F as FlightAPIClient
\
  participant L as SearchQueryLogger
\
  U->>C: POST /api/flights/search
\
  C->>S: searchFlights(request)
\
  S->>V: validateSearchCriteria(request)
\
  V-->>S: validationResult
\
  S->>F: fetchFlights(criteria)
\
  F-->>S: flights
\
  S->>L: logQuery(request)
\
  S-->>C: searchResults
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
| FlightSearchController | REST endpoint for flight search | New |
\
| FlightSearchService | Business logic for search/filter/sort | New |
\
| FlightAPIClient | Handles integration with external APIs | New |
\
| ValidationUtil | Validates search input | New |
\
| SearchQueryLogger | Logs search queries for analytics | New |
\
  3.4 Service Layer Logic as per the user story requirement and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|------------|--------------|-----------|
\
| origin, destination | Cannot be same | Origin and destination cannot be the same. | ValidationUtil |
\
| departureDate, returnDate | Must be future date | Travel dates must be in the future. | ValidationUtil |
\
| origin, destination, departureDate | Mandatory | All mandatory fields must be filled. | ValidationUtil |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|---------------|-----------------|
\
| Amadeus/Sabre APIs | Fetching flight data | API |
\
| Azure SQL Database | Logging queries | API/DB |
\
| Azure Application Insights | Monitoring & analytics | API |
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
  SEARCH_QUERY ||--o{ FLIGHT_RESULT : logs
\
  SEARCH_QUERY {
\
    string id PK
\
    string userId
\
    string origin
\
    string destination
\
    date departureDate
\
    date returnDate
\
    datetime timestamp
\
  }
\
  FLIGHT_RESULT {
\
    string id PK
\
    string searchQueryId FK
\
    string flightId
\
    string airline
\
    datetime departureTime
\
    datetime arrivalTime
\
    decimal price
\
    int stops
\
    string layovers
\
  }
\
```
\
  5.2 DB Validations
\
    - Ensure search queries have all mandatory fields before insert.
\
    - Validate that departureDate > current date.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - API response time < 3 seconds.
\
    - Use caching for frequent queries if possible.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 for user authentication.
\
    6.2.2 Authorization
\
      - Only authenticated users can search.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API requests/responses, external API calls.
\
      - INFO: Successful searches, user actions.
\
      - WARN: Slow responses, partial failures.
\
      - ERROR: Validation failures, API errors.
\
    6.3.2 Audit Log
\
      - Log all search queries with user and timestamp.
\

\
7 Dependencies
\
  - Third-party flight APIs (Amadeus, Sabre)
\
  - Azure SQL Database
\
  - Azure Application Insights
\

\
8 Assumptions
\
  - All third-party APIs are available and performant.
\
  - User authentication is handled before search API is called.
\
  - System clock is synchronized for date validations.