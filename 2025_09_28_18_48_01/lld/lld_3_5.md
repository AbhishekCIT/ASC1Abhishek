# For User Story Number [5]
1. Objective
This requirement enables passengers to enroll in a loyalty program, earn and redeem points for flights, and access exclusive member benefits. It provides real-time points tracking, redemption, and personalized offers through a secure online platform. The goal is to increase customer retention and engagement by rewarding frequent flyers.

2. API Model
  2.1 Common Components/Services
  - User Authentication Service (Spring Security)
  - Loyalty Program Service
  - Booking Service
  - Points Calculation Service
  - Offer Management Service
  - Notification Service

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Register Loyalty Member | POST | Success/Failure | /api/loyalty/register | {"userId":123,"personalDetails":{...}} | {"memberId":"LOY123","status":"REGISTERED"} |
  | Get Points Balance | GET | Success/Failure | /api/loyalty/points | {"memberId":"LOY123"} | {"points":12000,"history":[{"date":"2025-10-10","activity":"Flight","points":500}]} |
  | Redeem Points | POST | Success/Failure | /api/loyalty/redeem | {"memberId":"LOY123","points":1000,"rewardType":"UPGRADE"} | {"status":"REDEEMED","remainingPoints":11000} |
  | Get Exclusive Offers | GET | Success/Failure | /api/loyalty/offers | {"memberId":"LOY123"} | {"offers":[{"offerId":1,"description":"10% off next flight"}]} |

  2.3 Exceptions
  - NotEligibleForPointsException
  - InsufficientPointsException
  - ValidationException
  - DataSecurityException

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class LoyaltyController
    class LoyaltyService
    class PointsCalculationService
    class OfferService
    class NotificationService
    class BookingService
    class LoyaltyRepository
    class BookingRepository
    LoyaltyController --> LoyaltyService
    LoyaltyService --> PointsCalculationService
    LoyaltyService --> OfferService
    LoyaltyService --> NotificationService
    LoyaltyService --> LoyaltyRepository
    LoyaltyService --> BookingService
    BookingService --> BookingRepository
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant LoyaltyController
    participant LoyaltyService
    participant PointsCalculationService
    participant OfferService
    participant NotificationService
    participant BookingService
    participant LoyaltyRepository
    participant BookingRepository
    User->>LoyaltyController: Register Loyalty Member
    LoyaltyController->>LoyaltyService: registerMember()
    LoyaltyService->>LoyaltyRepository: saveMember()
    LoyaltyService-->>LoyaltyController: memberId
    LoyaltyController-->>User: memberId
    User->>LoyaltyController: Get Points Balance
    LoyaltyController->>LoyaltyService: getPoints()
    LoyaltyService->>PointsCalculationService: calculatePoints()
    PointsCalculationService->>LoyaltyRepository: fetchPoints()
    PointsCalculationService-->>LoyaltyService: pointsData
    LoyaltyService-->>LoyaltyController: pointsData
    LoyaltyController-->>User: pointsData
    User->>LoyaltyController: Redeem Points
    LoyaltyController->>LoyaltyService: redeemPoints()
    LoyaltyService->>PointsCalculationService: validateRedemption()
    PointsCalculationService-->>LoyaltyService: redemptionStatus
    LoyaltyService->>LoyaltyRepository: updatePoints()
    LoyaltyService-->>LoyaltyController: redemptionStatus
    LoyaltyController-->>User: redemptionStatus
    User->>LoyaltyController: Get Exclusive Offers
    LoyaltyController->>OfferService: getOffers()
    OfferService-->>LoyaltyController: offers
    LoyaltyController-->>User: offers
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | LoyaltyController | Handles loyalty program requests | New |
  | LoyaltyService | Business logic for loyalty program | New |
  | PointsCalculationService | Calculates and validates points | New |
  | OfferService | Manages exclusive offers | New |
  | NotificationService | Sends loyalty notifications | New |
  | BookingService | Booking data and validation | New |
  | LoyaltyRepository | Data access for loyalty members | New |
  | BookingRepository | Data access for bookings | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | bookingId | Eligible booking check | "Booking not eligible for points" | PointsCalculationService |
  | points | Sufficient balance check | "Insufficient points" | PointsCalculationService |
  | personalDetails | Data security check | "Invalid or insecure personal data" | LoyaltyService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Airline Loyalty API | Points accrual/redemption | API |
  | Booking System | Booking validation | API |
  | Notification Service | Loyalty notifications | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    LOYALTY_MEMBER ||--o{ POINTS_TRANSACTION : has
    LOYALTY_MEMBER {
      int id PK
      string member_id
      string name
      string email
      datetime join_date
      string status
    }
    POINTS_TRANSACTION {
      int id PK
      int loyalty_member_id FK
      datetime transaction_date
      string activity
      int points
      string type
    }
    OFFER {
      int id PK
      string description
      datetime valid_from
      datetime valid_to
      string eligibility
    }
  ```

  5.2 DB Validations
  - Only eligible bookings accrue points.
  - Points redemption cannot exceed available balance.
  - Personal data encrypted at rest.

6 Non-Functional Requirements
  6.1 Performance
  - Points updates processed within 5 seconds.

  6.2 Security
    6.2.1 Authentication
    - OAuth2/JWT-based authentication for all APIs.
    - HTTPS enforced for all endpoints.
    6.2.2 Authorization
    - Only member can access their loyalty data.
    - GDPR-compliant data processing.

  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Loyalty program requests
    - INFO: Points accrual/redemption events
    - ERROR: Failed transactions, exceptions
    - WARN: Suspicious redemption attempts
    6.3.2 Audit Log
    - Log all points and redemption events with timestamp, user, and status.

7 Dependencies
  - Airline loyalty API
  - Booking system
  - Notification service

8 Assumptions
  - Airline loyalty API is reliable and up-to-date.
  - Points and offers are synchronized in real time.
  - Personal data is securely stored and processed.
