# For User Story Number [5]
1. Objective
This requirement enables passengers to receive real-time notifications about their booked flights, including delays, gate changes, or cancellations. Users can subscribe to notifications and select their preferred channel (email, SMS, app). The system must deliver notifications promptly and securely.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (for API authentication)
  - NotificationService (for managing subscriptions and sending notifications)
  - AirlineStatusAPIClient (for real-time flight status)
  - ChannelIntegrationService (for email/SMS/app integration)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Subscribe Notification | POST | Success | /api/notifications/subscribe | {
    "bookingReference": "BR12345",
    "channels": ["email", "sms"],
    "contactInfo": {"email": "user@example.com", "phone": "+1234567890"}
  } | {
    "status": "SUBSCRIBED"
  } |
  | Unsubscribe Notification | POST | Success | /api/notifications/unsubscribe | {
    "bookingReference": "BR12345"
  } | {
    "status": "UNSUBSCRIBED"
  } |
  | Notification Event | POST | Success | /api/notifications/event | {
    "bookingReference": "BR12345",
    "eventType": "DELAY",
    "details": {"newDepartureTime": "2025-10-01T12:30:00"}
  } | {
    "status": "NOTIFIED"
  } |

  2.3 Exceptions
  - InvalidContactInfoException: Thrown if contact info is invalid.
  - SubscriptionNotFoundException: Thrown if user is not subscribed.
  - NotificationDeliveryException: Thrown if notification sending fails.
  - ExternalAPIException: Thrown if airline status API fails.

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class NotificationController {
      +subscribe(request)
      +unsubscribe(request)
      +notifyEvent(request)
    }
    class NotificationService {
      +subscribe(bookingReference, channels, contactInfo)
      +unsubscribe(bookingReference)
      +sendNotification(event)
    }
    class AirlineStatusAPIClient {
      +monitorFlightStatus(bookingReference)
    }
    class ChannelIntegrationService {
      +sendEmail(email, message)
      +sendSMS(phone, message)
      +sendAppNotification(userId, message)
    }
    class NotificationSubscription {
      -bookingReference
      -channels
      -contactInfo
    }
    NotificationController --> NotificationService
    NotificationService --> AirlineStatusAPIClient
    NotificationService --> ChannelIntegrationService
    NotificationService --> NotificationSubscription
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as NotificationController
    participant S as NotificationService
    participant A as AirlineStatusAPIClient
    participant CH as ChannelIntegrationService
    participant N as NotificationSubscription
    U->>C: POST /api/notifications/subscribe
    C->>S: subscribe(bookingReference, channels, contactInfo)
    S->>N: save subscription
    S->>A: monitorFlightStatus(bookingReference)
    A-->>S: event (e.g., delay)
    S->>CH: sendEmail/sendSMS/sendAppNotification
    CH-->>U: notification delivered
    S-->>C: status
    C-->>U: status
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | NotificationController | Handles API requests for notifications | New |
  | NotificationService | Business logic for notifications | New |
  | AirlineStatusAPIClient | Integrates with airline status APIs | New |
  | ChannelIntegrationService | Sends email/SMS/app notifications | New |
  | NotificationSubscription | Model for notification subscriptions | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | contactInfo | Must be valid email/phone | Invalid contact information | NotificationService |
  | bookingReference | Must exist | Booking not found | NotificationService |
  | channels | Must be supported (email/SMS/app) | Unsupported channel | NotificationService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline Status API | Real-time flight status | API |
  | Twilio (SMS), SendGrid (Email) | Notification delivery | API |
  | Authentication Service | Securing API access | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    NOTIFICATION_SUBSCRIPTION {
      string bookingReference
      string channels
      string contactInfo
    }
  ```
  5.2 DB Validations
  - Booking reference must exist.
  - Contact info must be valid and unique per booking.

6 Non-Functional Requirements
  6.1 Performance
  - Notifications delivered within 1 minute of event.
  - Scalable to handle notifications for thousands of flights.
  6.2 Security
    6.2.1 Authentication
    - All API calls require JWT-based authentication.
    6.2.2 Authorization
    - Only subscribed users can receive notifications.
    - GDPR compliance for user contact data.
  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Subscription requests, notification events
    - INFO: Successful notification deliveries
    - ERROR: Notification failures, validation errors
    - WARN: Delayed notifications
    6.3.2 Audit Log
    - Log user ID, booking reference, notification event, timestamp

7 Dependencies
  - Airline status API provider
  - Twilio (SMS), SendGrid (Email)
  - Authentication service

8 Assumptions
  - Airline status APIs and notification services are available and performant.
  - Users provide valid contact information.
