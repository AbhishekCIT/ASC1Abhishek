# For User Story Number [5]
\
1. Objective
\
Allow frequent travelers to manage their profile and view their travel history, including past and upcoming bookings. Provide secure interfaces for updating personal information and displaying booking history with relevant details. Ensure all data is securely stored, auditable, and accessible only to authenticated users.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - UserProfileService (profile management logic)
\
    - BookingHistoryService (retrieves booking history)
\
    - ValidationUtil (profile input validation)
\
    - AuditLogger (logs profile/data changes)
\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Create/Update Profile | POST/PUT | Success | /api/profile | { "userId": "U123", "name": "John Doe", "email": "john@example.com", "preferences": { ... } } | { "status": "PROFILE_UPDATED", "profile": { ... } } |
\
| Get Profile | GET | Success | /api/profile | N/A | { "profile": { "userId": "U123", "name": "John Doe", "email": "john@example.com", ... } } |
\
| Get Booking History | GET | Success | /api/profile/history | N/A | { "history": [ { "bookingRef": "ABC123", "flightId": "DL100", "status": "CONFIRMED", ... } ] } |
\
| Get Booking Detail | GET | Success | /api/profile/history/{bookingRef} | N/A | { "booking": { "bookingRef": "ABC123", ... } } |
\
| Delete Profile | DELETE | Success | /api/profile | N/A | { "status": "PROFILE_DELETED" } |
\
| Create/Update Profile | POST/PUT | Failure | /api/profile | { ... } | { "error": "Mandatory fields missing or invalid." } |
\
  2.3 Exceptions
\
    - InvalidInputException: For invalid or missing profile fields
\
    - UnauthorizedAccessException: Unauthenticated access attempt
\
    - ProfileNotFoundException: Profile does not exist
\
    - BookingNotFoundException: Booking not found in history
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
  class UserProfileController {
\
    +createOrUpdateProfile()
\
    +getProfile()
\
    +deleteProfile()
\
  }
\
  class BookingHistoryController {
\
    +getHistory()
\
    +getBookingDetail()
\
  }
\
  class UserProfileService {
\
    +createOrUpdateProfile()
\
    +getProfile()
\
    +deleteProfile()
\
  }
\
  class BookingHistoryService {
\
    +getHistory()
\
    +getBookingDetail()
\
  }
\
  class ValidationUtil {
\
    +validateProfile()
\
  }
\
  class AuditLogger {
\
    +logProfileChange()
\
  }
\
  UserProfileController --> UserProfileService
\
  UserProfileService --> ValidationUtil
\
  UserProfileService --> AuditLogger
\
  BookingHistoryController --> BookingHistoryService
\
  BookingHistoryService --> AuditLogger
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
  participant PC as UserProfileController
\
  participant PS as UserProfileService
\
  participant V as ValidationUtil
\
  participant A as AuditLogger
\
  participant HC as BookingHistoryController
\
  participant HS as BookingHistoryService
\
  U->>PC: POST/PUT /api/profile
\
  PC->>V: validateProfile(request)
\
  V-->>PC: validationResult
\
  PC->>PS: createOrUpdateProfile(request)
\
  PS->>A: logProfileChange(userId, action)
\
  PS-->>PC: profileResponse
\
  PC-->>U: Response
\
  U->>PC: GET /api/profile
\
  PC->>PS: getProfile(userId)
\
  PS-->>PC: profile
\
  PC-->>U: Response
\
  U->>HC: GET /api/profile/history
\
  HC->>HS: getHistory(userId)
\
  HS-->>HC: history
\
  HC-->>U: Response
\
  U->>HC: GET /api/profile/history/{bookingRef}
\
  HC->>HS: getBookingDetail(bookingRef)
\
  HS-->>HC: booking
\
  HC-->>U: Response
\
  U->>PC: DELETE /api/profile
\
  PC->>PS: deleteProfile(userId)
\
  PS->>A: logProfileChange(userId, "DELETE")
\
  PS-->>PC: deleteStatus
\
  PC-->>U: Response
\
```
\
  3.3 Components
\
| Component Name | Description | Existing/New |
\
|----------------|-------------|--------------|
\
| UserProfileController | REST endpoint for profile management | New |
\
| BookingHistoryController | REST endpoint for booking history | New |
\
| UserProfileService | Business logic for profile management | New |
\
| BookingHistoryService | Retrieves booking history/details | New |
\
| ValidationUtil | Validates profile input | New |
\
| AuditLogger | Logs profile/data changes | New |
\
  3.4 Service Layer Logic as per the user story requirement and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|------------|--------------|-----------|
\
| name | Mandatory, valid | Name is required. | ValidationUtil |
\
| email | Mandatory, valid email | Valid email is required. | ValidationUtil |
\
| userId | Authenticated | Unauthorized access. | UserProfileService |
\
| profile/booking changes | Audit log | Data change logged. | AuditLogger |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|---------------|-----------------|
\
| Azure SQL Database | Profile/history storage | API/DB |
\
| Authentication Service | OAuth2 authentication | API |
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
  USER_PROFILE ||--o{ BOOKING : has
\
  USER_PROFILE {
\
    string userId PK
\
    string name
\
    string email
\
    string preferences
\
    datetime createdTime
\
    datetime updatedTime
\
  }
\
  BOOKING {
\
    string bookingRef PK
\
    string userId FK
\
    string flightId
\
    string status
\
    datetime bookingTime
\
  }
\
  AUDIT_LOG {
\
    string id PK
\
    string userId FK
\
    string action
\
    string details
\
    datetime timestamp
\
  }
\
```
\
  5.2 DB Validations
\
    - Ensure mandatory fields (name, email) are valid before insert/update.
\
    - Log all profile/booking changes in AUDIT_LOG.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Profile updates reflected within 2 seconds.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 for user authentication.
\
    6.2.2 Authorization
\
      - Only authenticated users can access/modify profile/history.
\
      - GDPR compliance for data access/deletion.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: API requests/responses, profile changes.
\
      - INFO: Successful updates, history access.
\
      - WARN: Unauthorized attempts, partial failures.
\
      - ERROR: Validation failures, access errors.
\
    6.3.2 Audit Log
\
      - Log all profile and booking changes with user and timestamp.
\

\
7 Dependencies
\
  - Azure SQL Database
\
  - Authentication Service (OAuth2)
\

\
8 Assumptions
\
  - User authentication is handled before profile/history APIs are called.
\
  - Booking data is linked to userId and up-to-date.
\
  - GDPR compliance is enforced for all user data.