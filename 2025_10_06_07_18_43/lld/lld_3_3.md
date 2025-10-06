# For User Story Number [3]
\
1. Objective
\
This requirement ensures travelers receive real-time notifications about their flight status, including delays, gate changes, and cancellations. Notifications are sent via email, SMS, and in-app alerts, and users can manage their notification preferences. The system guarantees timely, reliable, and accurate delivery of flight status updates.
\

\
2. API Model
\
  2.1 Common Components/Services
\
    - Notification Service
\
    - User Profile Service
\
    - Flight Status Monitoring Service
\
    - Airline API Client
\
    - Audit Logging Service
\

\
  2.2 API Details
\
| Operation | REST Method | Type | URL | Request | Response |
\
|-----------|-------------|------|-----|---------|----------|
\
| Get Notification Preferences | GET | Success/Failure | /api/users/{userId}/notifications | N/A | {"userId": "U456", "preferences": {"email": true, "sms": false, "inApp": true}} |
\
| Update Notification Preferences | PUT | Success/Failure | /api/users/{userId}/notifications | {"preferences": {"email": true, "sms": true, "inApp": true}} | {"userId": "U456", "preferences": {...}} |
\
| Send Notification | POST | Success/Failure | /api/notifications/send | {"userId": "U456", "channel": "email", "message": "Flight delayed"} | {"status": "DELIVERED"} |
\
| Monitor Flight Status | GET | Success/Failure | /api/flights/{flightId}/status | N/A | {"flightId": "F123", "status": "DELAYED", "gate": "A5"} |
\

\
  2.3 Exceptions
\
    - NotificationDeliveryException: Returned if notification fails to deliver.
\
    - InvalidPreferenceException: Returned if user sets invalid notification preferences.
\
    - FlightStatusNotFoundException: Returned if flight status cannot be retrieved.
\
    - UnauthorizedAccessException: Returned if user is not authorized to manage preferences.
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
  User <|-- NotificationController
\
  NotificationController --> NotificationService
\
  NotificationService --> UserProfileService
\
  NotificationService --> FlightStatusMonitoringService
\
  NotificationService --> AuditLoggingService
\
  NotificationService --> AzureNotificationHubClient
\
  FlightStatusMonitoringService --> AirlineAPIClient
\
```
\

\
  3.2 UML Sequence Diagram
\
```mermaid
\
sequenceDiagram
\
  participant AirlineAPIClient
\
  participant FlightStatusMonitoringService
\
  participant NotificationService
\
  participant NotificationController
\
  participant UserProfileService
\
  participant AzureNotificationHubClient
\
  participant AuditLoggingService
\
  participant User
\
  loop Monitor Flight Status
\
    AirlineAPIClient->>FlightStatusMonitoringService: Push flight status update
\
    FlightStatusMonitoringService->>NotificationService: statusChange(flightId, status)
\
    NotificationService->>UserProfileService: getNotificationPreferences(userId)
\
    UserProfileService-->>NotificationService: preferences
\
    NotificationService->>AzureNotificationHubClient: sendNotification(userId, channel, message)
\
    AzureNotificationHubClient-->>NotificationService: deliveryStatus
\
    NotificationService->>AuditLoggingService: logNotificationEvent(userId, status, channel)
\
    NotificationService-->>User: notification delivered
\
  end
\
```
\

\
  3.3 Components
\
| Component Name | Description | Existing/New |
\
|----------------|-------------|--------------|
\
| NotificationController | Handles API requests for notifications and preferences | New |
\
| NotificationService | Business logic for notification delivery | New |
\
| UserProfileService | Manages user notification preferences | Existing |
\
| FlightStatusMonitoringService | Monitors airline APIs for flight status | New |
\
| AirlineAPIClient | Integrates with airline status APIs | New |
\
| AzureNotificationHubClient | Sends notifications via email, SMS, in-app | Existing |
\
| AuditLoggingService | Logs notification events for audit | Existing |
\

\
  3.4 Service Layer Logic and Validations
\
| FieldName | Validation | Error Message | ClassUsed |
\
|-----------|-----------|---------------|-----------|
\
| userId | Must have active booking | "No active booking found" | NotificationService |
\
| channel | Must be valid (email, SMS, inApp) | "Invalid notification channel" | NotificationService |
\
| preferences | Must be boolean for each channel | "Invalid preference value" | UserProfileService |
\
| message | Must be clear and accurate | "Notification content error" | NotificationService |
\

\
4 Integrations
\
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
\
|----------------------|--------------|-----------------|
\
| Airline Status APIs | Flight status monitoring | API |
\
| Azure Notification Hubs | Notification delivery | API |
\
| SMS/Email Gateway APIs | Notification delivery | API |
\
| Audit Logging Service | Notification event logging | API |
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
  USER ||--o{ BOOKING : has
\
  BOOKING }|--|| FLIGHT : books
\
  USER ||--o{ NOTIFICATION_PREFERENCE : sets
\
  NOTIFICATION_EVENT {
\
    string eventId
\
    string userId
\
    string bookingId
\
    string channel
\
    string message
\
    datetime sentAt
\
    string deliveryStatus
\
  }
\
  NOTIFICATION_PREFERENCE {
\
    string userId
\
    boolean email
\
    boolean sms
\
    boolean inApp
\
  }
\
```
\

\
  5.2 DB Validations
\
    - Notification preference must reference a valid user.
\
    - Notification event must reference a valid booking and user.
\
    - Delivery status must be updated on notification send.
\

\
6 Non-Functional Requirements
\
  6.1 Performance
\
    - Notifications delivered within 1 minute of status change.
\
    - System scales to 10,000+ users simultaneously.
\
    - Caching for flight status at API layer.
\

\
  6.2 Security
\
    6.2.1 Authentication
\
      - OAuth2 authentication for managing preferences.
\
      - IAM integration for user management.
\
    6.2.2 Authorization
\
      - Only users with active bookings receive notifications.
\
      - Role-based access for notification management (traveler, admin).
\

\
  6.3 Logging
\
    6.3.1 Application Logging
\
      - DEBUG: Notification payloads, flight status updates.
\
      - INFO: Successful notification deliveries.
\
      - ERROR: Notification delivery failures.
\
      - WARN: Preference misconfigurations.
\
    6.3.2 Audit Log
\
      - Log notification events, delivery status, user/channel/timestamp for audit.
\

\
7 Dependencies
\
    - Airline status APIs for flight status updates.
\
    - Azure Notification Hubs for notification delivery.
\
    - SMS/email gateway APIs for notification delivery.
\
    - Audit logging service for event logging.
\

\
8 Assumptions
\
    - Airline APIs provide real-time status updates.
\
    - Notification hubs and gateways support required channels.
\
    - User authentication and authorization are managed via existing IAM system.