EPIC Number: 3
User Story Number: 4
User Story Title: As a traveler, I want to register and manage my account, So that I can securely access my bookings and personal information.

User Story Description: This feature enables users to register for an account, log in, and manage their profile information, including contact details, travel preferences, and password. Secure authentication and authorization must be enforced.

Acceptance Criteria:
1. User can register with email and password.
2. User receives verification email upon registration.
3. User can log in and log out securely.
4. User can update profile information and change password.
5. User can view past and upcoming bookings in their account.

Validations:
1. Email must be unique and valid.
2. Password must meet complexity requirements.
3. Profile updates must be validated before saving.

Business Logic:
- Store user credentials securely (hashed passwords).
- Send verification email upon registration.
- Authenticate users on login and manage sessions.
- Allow profile updates and password changes with validation.

Technical Context:
- Technology stack: React (frontend), Node.js/Express (backend), PostgreSQL (database), JWT for authentication.
- APIs: REST API for registration, login, and profile management.
- Data formats: JSON for API requests/responses.
- Security: HTTPS, password hashing, email verification, JWT tokens.

Non-Functional Requirements:
- Registration and login should complete within 3 seconds.
- System should support at least 20,000 registered users.
- All authentication events must be logged.
- 99.9% uptime for authentication services.
