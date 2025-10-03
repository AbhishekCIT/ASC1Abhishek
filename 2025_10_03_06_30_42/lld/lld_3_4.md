# For User Story Number [4]

1. Objective
This feature allows travelers to contact customer support for their air transport bookings via chat, email, or phone. Users can submit queries, receive a ticket number, and track the status and resolution of their requests. Support agents can access booking details to assist users and all interactions are logged and tracked until resolution.

2. API Model
  2.1 Common Components/Services
  - SupportService (new)
  - BookingService (existing)
  - UserService (existing)
  - TicketingIntegrationService (new)
  - AuthService (existing)

  2.2 API Details
| Operation | REST Method | Type | URL | Request | Response |
|-----------|-------------|------|-----|---------|----------|
| Submit Support Request | POST | Success/Failure | /api/support/requests | { "userId": "", "bookingId": "", "channel": "CHAT", "query": "..." } | { "ticketId": "", "status": "OPEN" } |
| Get Support Ticket Status | GET | Success/Failure | /api/support/tickets/{ticketId} | N/A | { "ticketId": "", "status": "IN_PROGRESS", "resolution": "..." } |
| List Support Tickets | GET | Success/Failure | /api/support/tickets?userId={userId} | N/A | { "tickets": [ { "ticketId": "", "status": "RESOLVED", "created": "..." } ] } |
| Agent Update Ticket | PUT | Success/Failure | /api/support/tickets/{ticketId} | { "status": "RESOLVED", "resolution": "..." } | { "ticketId": "", "status": "RESOLVED" } |

  2.3 Exceptions
  - UnauthorizedSupportRequestException
  - TicketNotFoundException
  - TicketUpdateException
  - SLABreachException

3. Functional Design
  3.1 Class Diagram
```mermaid
classDiagram
  class SupportService {
    +Ticket submitRequest(userId, bookingId, channel, query)
    +Ticket getTicketStatus(ticketId)
    +List<Ticket> listTickets(userId)
    +Ticket updateTicket(ticketId, status, resolution)
  }
  class BookingService {
    +Booking getBooking(bookingId)
  }
  class UserService {
    +User authenticate(token)
  }
  class TicketingIntegrationService {
    +Ticket createTicket(query)
    +Ticket updateTicket(ticketId, status, resolution)
  }
  SupportService --> TicketingIntegrationService
  SupportService --> BookingService
  SupportService --> UserService
```

  3.2 UML Sequence Diagram
```mermaid
sequenceDiagram
  participant User
  participant AuthService
  participant SupportService
  participant BookingService
  participant TicketingIntegrationService

  User->>AuthService: authenticate(token)
  AuthService-->>User: authenticationResult
  User->>SupportService: submitRequest(userId, bookingId, channel, query)
  SupportService->>BookingService: getBooking(bookingId)
  BookingService-->>SupportService: bookingDetails
  SupportService->>TicketingIntegrationService: createTicket(query)
  TicketingIntegrationService-->>SupportService: ticketId
  SupportService-->>User: ticketId, status
  User->>SupportService: getTicketStatus(ticketId)
  SupportService->>TicketingIntegrationService: getTicketStatus(ticketId)
  TicketingIntegrationService-->>SupportService: ticketStatus
  SupportService-->>User: ticketStatus
```

  3.3 Components
| Component Name | Description | Existing/New |
|----------------|-------------|--------------|
| SupportService | Handles support requests and ticket tracking | New |
| BookingService | Retrieves booking details | Existing |
| UserService | Handles user authentication | Existing |
| TicketingIntegrationService | Integrates with third-party support/ticketing | New |
| AuthService | Handles authentication tokens | Existing |

  3.4 Service Layer Logic as per the user story requirement and Validations
| FieldName | Validation | Error Message | ClassUsed |
|-----------|-----------|--------------|-----------|
| userAuthentication | Must be authenticated | User not authenticated | AuthService |
| bookingId | Must belong to user | Unauthorized support request | SupportService |
| query | Must be present | Query cannot be empty | SupportService |
| ticketStatus | Must be updated until resolution | Ticket not updated | TicketingIntegrationService |
| sla | Response within SLA | SLA breach | SupportService |

4. Integrations
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
|----------------------|--------------|-----------------|
| Ticketing Platform (e.g., Zendesk) | Support ticket creation/tracking | API |
| Booking Service | Retrieve booking details | API |
| Auth Service | User authentication | API |

5. DB Details
  5.1 ER Model
```mermaid
erDiagram
  USER ||--o{ SUPPORT_TICKET : raises
  SUPPORT_TICKET ||--|{ TICKET_LOG : logs
  USER {
    string user_id PK
    string email
  }
  SUPPORT_TICKET {
    string ticket_id PK
    string user_id FK
    string booking_id FK
    string channel
    string status
    datetime created
    datetime resolved
  }
  TICKET_LOG {
    string log_id PK
    string ticket_id FK
    string action
    string agent_id
    datetime timestamp
  }
```

  5.2 DB Validations
  - Support tickets must be linked to authenticated users
  - Ticket logs must be written for every action
  - SLA response times must be tracked

6. Non-Functional Requirements
  6.1 Performance
    - Support requests must be acknowledged within 1 minute.
    - Ticket lookup and status retrieval optimized for speed.
  6.2 Security
    6.2.1 Authentication
      - OAuth2/JWT for API authentication.
      - HTTPS for all endpoints.
    6.2.2 Authorization
      - Only users can access their own tickets.
      - Agents can access tickets assigned to them.
  6.3 Logging
    6.3.1 Application Logging
      - INFO: All support requests, ticket updates, and resolutions.
      - ERROR: Ticket creation/update failures, SLA breaches.
      - DEBUG: Support request payloads.
    6.3.2 Audit Log
      - Log all ticket actions for 2 years for audit.

7. Dependencies
  - Ticketing platform (e.g., Zendesk)
  - Booking service for booking details
  - Auth service for user authentication

8. Assumptions
  - Ticketing platform APIs are reliable and available.
  - Booking service provides up-to-date booking details.
  - SLA definitions are clearly established and monitored.
