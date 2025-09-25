EPIC Number: 32
User Story Number: 4
User Story Title: As a business user, I want to manage notification preferences for scheduled reports, so that I am informed in my preferred way about deliveries and failures.

User Story Description: This feature allows users to set preferences for how they receive notifications about scheduled report deliveries (e.g., email, SMS, in-app) and failures. Users can choose notification channels and frequency (immediate, daily digest, etc.).

Acceptance Criteria:
1. Users can access and update their notification preferences.
2. Users can select notification channels (email, SMS, in-app).
3. Users can choose notification frequency (immediate, daily digest).
4. Users receive notifications as per their preferences for both successful and failed deliveries.

Validations:
1. Only valid contact information can be saved for notifications.
2. Users must opt-in for SMS notifications.
3. Notification settings must persist across sessions.

Business Logic:
- The system checks user preferences before sending notifications.
- For failures, critical alerts override digest settings to ensure timely awareness.
- Notification logs are maintained for audit purposes.

Technical Context:
- Technology stack: .NET Core backend, Angular frontend, Azure Notification Hubs for SMS and in-app notifications, SMTP for email.
- API endpoints for updating and retrieving preferences.
- Security: User authentication and authorization for managing preferences.

Non-Functional Requirements:
- Performance: Notifications must be sent within 1 minute of event occurrence.
- Security: Notification data encrypted in transit and at rest.
- Scalability: Support for 100,000+ notification events per day.
- Monitoring: Real-time tracking of notification delivery and failures.