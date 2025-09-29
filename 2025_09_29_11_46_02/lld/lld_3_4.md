# For User Story Number [4]
1. Objective
This requirement enables passengers to manage their profiles and saved preferences, including personal details, travel documents, frequent flyer numbers, and payment methods. The system must allow users to create, view, and update their profiles securely. Profile information should be auto-filled during booking for logged-in users.

2. API Model
  2.1 Common Components/Services
  - AuthenticationService (OAuth2 for authentication)
  - ProfileService (for profile management)
  - PaymentMethodService (for managing payment methods)
  - EncryptionService (for securing sensitive data)

  2.2 API Details
  | Operation | REST Method | Type | URL | Request | Response |
  |-----------|-------------|------|-----|---------|----------|
  | Create Profile | POST | Success | /api/profile | {
    "email": "user@example.com",
    "name": "Jane Doe",
    "passportNumber": "P1234567",
    "frequentFlyer": "FF1234",
    "paymentMethods": [{"type": "card", "token": "tok_xyz"}]
  } | {
    "profileId": "PR123",
    "status": "CREATED"
  } |
  | Update Profile | PUT | Success | /api/profile/{profileId} | {
    "email": "user@example.com",
    "name": "Jane Doe Updated"
  } | {
    "profileId": "PR123",
    "status": "UPDATED"
  } |
  | Get Profile | GET | Success | /api/profile/{profileId} | - | {
    "profileId": "PR123",
    "email": "user@example.com",
    "name": "Jane Doe",
    "passportNumber": "P1234567",
    "frequentFlyer": "FF1234",
    "paymentMethods": [{"type": "card", "last4": "1234"}]
  } |
  | Add Payment Method | POST | Success | /api/profile/{profileId}/payment-methods | {
    "type": "card",
    "token": "tok_abc"
  } | {
    "status": "ADDED"
  } |
  | Remove Payment Method | DELETE | Success | /api/profile/{profileId}/payment-methods/{paymentMethodId} | - | {
    "status": "REMOVED"
  } |

  2.3 Exceptions
  - InvalidProfileDataException: Thrown if mandatory fields are missing/invalid.
  - PaymentMethodValidationException: Thrown if payment method is invalid.
  - AuthenticationException: Thrown if user is not authenticated.
  - AuthorizationException: Thrown if user tries to access another user's profile.

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    class ProfileController {
      +createProfile(request)
      +updateProfile(profileId, request)
      +getProfile(profileId)
    }
    class ProfileService {
      +createProfile(data)
      +updateProfile(profileId, data)
      +getProfile(profileId)
    }
    class PaymentMethodService {
      +addPaymentMethod(profileId, data)
      +removePaymentMethod(profileId, paymentMethodId)
    }
    class EncryptionService {
      +encrypt(data)
      +decrypt(data)
    }
    class Profile {
      -profileId
      -email
      -name
      -passportNumber
      -frequentFlyer
      -paymentMethods
    }
    ProfileController --> ProfileService
    ProfileService --> PaymentMethodService
    ProfileService --> EncryptionService
    ProfileService --> Profile
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant U as User
    participant C as ProfileController
    participant S as ProfileService
    participant P as PaymentMethodService
    participant E as EncryptionService
    participant D as DB
    U->>C: POST /api/profile
    C->>S: createProfile(data)
    S->>E: encrypt(sensitiveData)
    E-->>S: encryptedData
    S->>D: save profile
    D-->>S: profileId
    S-->>C: profileId, status
    C-->>U: profileId, status
  ```

  3.3 Components
  | Component Name | Description | Existing/New |
  |----------------|-------------|--------------|
  | ProfileController | Handles API requests for profile management | New |
  | ProfileService | Business logic for profile management | New |
  | PaymentMethodService | Manages payment methods for profiles | New |
  | EncryptionService | Encrypts/decrypts sensitive data | New |
  | Profile | Model for user profile data | New |

  3.4 Service Layer Logic and Validations
  | FieldName | Validation | Error Message | ClassUsed |
  |-----------|------------|--------------|-----------|
  | email | Must be valid email format | Invalid email address | ProfileService |
  | passportNumber | Must be valid format | Invalid passport number | ProfileService |
  | paymentMethods | Must be valid and securely stored | Invalid payment method | PaymentMethodService |
  | authentication | Must be authenticated | User not authenticated | AuthenticationService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor | IntegrationType |
  |----------------------|---------------|-----------------|
  | Authentication Service (OAuth2) | User authentication | API |
  | Azure SQL Database | Profile data storage | DB |
  | Encryption Service | Data encryption | API |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    PROFILE {
      string profileId
      string email
      string name
      string passportNumber
      string frequentFlyer
    }
    PAYMENT_METHOD {
      string paymentMethodId
      string profileId
      string type
      string token
    }
    PROFILE ||--o{ PAYMENT_METHOD : has
  ```
  5.2 DB Validations
  - Email must be unique.
  - Payment method tokens must be encrypted.

6 Non-Functional Requirements
  6.1 Performance
  - Profile updates reflected instantly.
  - Scalable for millions of profiles.
  6.2 Security
    6.2.1 Authentication
    - OAuth2 authentication required for all profile operations.
    6.2.2 Authorization
    - Only profile owner can access/update profile.
    - GDPR compliance for data privacy.
  6.3 Logging
    6.3.1 Application Logging
    - DEBUG: Profile update requests, validation details
    - INFO: Profile creation/updates
    - ERROR: Validation/authentication errors
    - WARN: Unauthorized access attempts
    6.3.2 Audit Log
    - Log user ID, profile changes, timestamp

7 Dependencies
  - Authentication service (OAuth2)
  - Azure SQL Database
  - Encryption service

8 Assumptions
  - All sensitive data is encrypted at rest and in transit.
  - Payment methods are validated by a third-party provider.
