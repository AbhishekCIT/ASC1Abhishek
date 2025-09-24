EPIC Number: 32
User Story Number: 4
User Story Title: As a business user, I want to configure my notification preferences for scheduled reports, so that I receive alerts in my preferred way.

User Story Description: This feature allows users to choose how they are notified about scheduled report events, such as successful deliveries, failures, or upcoming schedule changes. Users can select from email, SMS, or in-app notifications, and customize which events trigger notifications.

Acceptance Criteria:
1. Users can access a notification preferences page.
2. Users can select preferred notification channels (email, SMS, in-app).
3. Users can choose which events trigger notifications (success, failure, schedule changes).
4. System sends notifications according to user preferences.

Validations:
1. Only valid contact information is accepted for email/SMS.
2. Users cannot disable all notifications for critical failures.
3. Preferences are saved and applied immediately.

Business Logic:
- Store user preferences in the database.
- When a scheduled report event occurs, check user preferences and send notifications accordingly.
- Ensure at least one notification channel is enabled for critical events.

Technical Context:
- Angular frontend for preferences UI.
- .NET Core API for managing preferences.
- Integration with email/SMS providers (e.g., Twilio for SMS).
- Security: Only authenticated users can manage their preferences.

Non-Functional Requirements:
- Notification delivery within 1 minute of the event.
- System supports at least 10,000 users with unique preferences.
- All notification data encrypted in transit.
- Monitoring for failed notification deliveries.
