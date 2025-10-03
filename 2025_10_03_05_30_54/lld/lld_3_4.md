# For User Story Number [4]
\
1. Objective
\
Enable the system to send timely notifications to travelers about their flight status, including reminders, gate changes, delays, or cancellations. Deliver notifications via email, SMS, or in-app alerts based on user preferences. Ensure notifications are reliable, real-time, and configurable by the user.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - NotificationService (core notification logic)
\
    - FlightStatusMonitor (monitors airline APIs/webhooks)
\
    - NotificationPreferenceService (manages user preferences)
\
    - NotificationHistoryService (stores notification logs)
\
    - ValidationUtil (contact info validation)
\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Set Preferences | POST | Success | /api/notifications/preferences | { "userId": "U123", "email": true, "sms": false, "app": true } | { "status": "PREFERENCES_UPDATED" } |
\
| Get Notification History | GET | Success | /api/notifications/history | N/A | { "history": [ { "type": "REMINDER", "message": "Flight DL100 departs in 2 hours", "timestamp": "2025-12-01T07:00:00Z" } ] } |
\
| Trigger Notification | POST | Success | /api/notifications/trigger | { "userId": "U123", "type": "DELAY", "message": "Flight DL100 delayed by 1 hour" } | { "status": "NOTIFICATION_SENT" } |
\
| Set Preferences | POST | Failure | /api/notifications/preferences | { ... } | { "error": "Invalid contact information." } |
\
  2.3 Exceptions
\
    - InvalidContactInfoException: For invalid or missing contact info
\
    - NotificationSendException: Failure to send notification
\
    - DuplicateNotificationException: Duplicate notification detected
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
  class NotificationController {
\
    +setPreferences()
\
    +getHistory()
\
    +triggerNotification()
\
  }
\
  class NotificationService {
\
    +sendNotification()
\
  }
\
  class FlightStatusMonitor {
\
    +monitorStatus()
\
  }
\
  class NotificationPreferenceService {
\
    +getPreferences()
\
    +setPreferences()
\
  }
\
  class NotificationHistoryService {
\
    +logNotification()
\
    +getHistory()
\
  }
\
  class ValidationUtil {
\
    +validateContactInfo()
\
  }
\
  NotificationController --> NotificationService
\
  NotificationService --> NotificationPreferenceService
\
  NotificationService --> NotificationHistoryService
\
  NotificationService --> ValidationUtil
\
  FlightStatusMonitor --> NotificationService
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
  participant C as NotificationController
\
  participant P as NotificationPreferenceService
\
  participant S as NotificationService
\
  participant H as NotificationHistoryService
\
  participant V as ValidationUtil
\
  participant F as FlightStatusMonitor
\
  U->>C: POST /api/notifications/preferences
\
  C->>V: validateContactInfo(request)
\
  V-->>C: validationResult
\
  C->>P: setPreferences(request)
\
  P-->>C: status
\
  C-->>U: Response
\
  F->>S: sendNotification(event)
\
  S->>P: getPreferences(userId)
\
  P-->>S: preferences
\
  S->>V: validateContactInfo(user)
\
  V-->>S: validationResult
\
  S->>H: logNotification(event)
\
  S-->>F: notificationStatus
\
  U->>C: GET /api/notifications/history
\
  C->>H: getHistory(userId)
\
  H-->>C: history
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
| NotificationController | REST endpoint for notifications | New |
\
| NotificationService | Core notification logic | New |
\
| FlightStatusMonitor | Monitors airline APIs/webhooks | New |
\
| NotificationPreferenceService | Manages user preferences | New |
\
| NotificationHistoryService | Stores notification logs | New |
\
| ValidationUtil | Validates contact info | New |
\
  3.4 Service Layer Logic as per the user story requirement and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|------------|--------------|-----------|
\
| contactInfo | Valid email/phone/app | Invalid contact information. | ValidationUtil |
\
| bookingStatus | Must be active | Notifications sent only for active bookings. | NotificationService |
\
| notification | Prevent duplicates | Duplicate notification detected. | NotificationService |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|---------------|-----------------|
\
| Airline APIs/Webhooks | Flight status monitoring | API |
\
| SendGrid/Twilio | Email/SMS delivery | API |
\
| Azure SQL Database | Notification logs | API/DB |
\
| Azure Notification Hubs | Push/in-app notifications | API |
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
  USER ||--o{ NOTIFICATION_PREFERENCE : has
\
  USER ||--o{ NOTIFICATION_HISTORY : receives
\
  NOTIFICATION_PREFERENCE {
\
    string userId PK
\
    bool email
\
    bool sms
\
    bool app
\
    datetime updatedTime
\
  }
\
  NOTIFICATION_HISTORY {
\
    string id PK
\
    string userId FK
\
    string type
\
    string message
\
    datetime timestamp
\
  }
\
```
\
  5.2 DB Validations
\
    - Ensure valid contact information before saving preferences.
\
    - Prevent duplicate notifications for the same event.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Notifications delivered within 1 minute of event.
\
    - Use async delivery and retry mechanisms.
\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 for user authentication.
\
    6.2.2 Authorization
\
      - Only authenticated users can manage preferences/history.
\
      - Opt-in consent required for notifications.
\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: Notification triggers, delivery attempts.
\
      - INFO: Successful deliveries, user opt-ins.
\
      - WARN: Delivery retries, partial failures.
\
      - ERROR: Validation failures, delivery errors.
\
    6.3.2 Audit Log
\
      - Log all notifications sent with user and timestamp.
\

\
7 Dependencies
\
  - Airline APIs/webhooks
\
  - SendGrid/Twilio
\
  - Azure Notification Hubs
\
  - Azure SQL Database
\

\
8 Assumptions
\
  - Airline APIs/webhooks provide real-time status updates.
\
  - Email/SMS/push gateways are reliable and performant.
\
  - User authentication is handled before notification APIs are called.