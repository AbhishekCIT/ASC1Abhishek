EPIC Number: 3
User Story Number: 4
User Story Title: As a passenger, I want to manage my profile and travel preferences, So that my bookings and notifications are personalized.

User Story Description: This feature enables passengers to create, update, and manage their profiles, including personal details, travel preferences, and notification settings. The system should use profile data to personalize booking and communication experiences.

Acceptance Criteria:
1. Users can create and update their profile information.
2. Users can set travel preferences (e.g., meal, seat, notification).
3. Profile data is used to personalize booking and notifications.

Validations:
1. Required fields (name, contact info) must be completed.
2. Preferences must be valid options.
3. Data must be saved securely and accurately.

Business Logic: The system stores user profile data in a secure database, applies preferences during booking and notification processes, and allows users to update information at any time.

Technical Context: Technology stack: ReactJS frontend, NodeJS backend, PostgreSQL database. Secure storage of personal data. Integration with notification services. Data exchanged in JSON format. Security via HTTPS, data encryption, and OAuth2 authentication.

Non-Functional Requirements: Profile update latency < 2 seconds. 99.9% uptime. GDPR compliance for data privacy. Scalability for large user base. Monitoring via Azure Application Insights.