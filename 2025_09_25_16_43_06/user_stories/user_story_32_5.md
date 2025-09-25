EPIC Number: 32
User Story Number: 5
User Story Title: As a business user, I want to customize notification preferences for scheduled reports, so that I am informed in my preferred way about report deliveries and failures.

User Story Description: Users should be able to choose how (email, SMS, in-app) and when (immediate, daily summary) they receive notifications about scheduled report deliveries and failures.

Acceptance Criteria:
1. Users can select preferred notification channels (email, SMS, in-app).
2. Users can opt for immediate or summary notifications.
3. Users receive notifications as per their preferences for both successful and failed deliveries.
4. Users can update preferences at any time.

Validations:
1. Notification preferences are stored and retrieved correctly.
2. Users receive notifications only via selected channels.
3. Notification failures are logged and retried.

Business Logic:
- Notification settings are linked to user profiles.
- Notification service checks user preferences before sending alerts.
- Summary notifications aggregate events over a defined period.

Technical Context:
- Notification service implemented as Azure Function or background job.
- Integration with email (SMTP/SendGrid), SMS (Twilio), and in-app notification APIs.
- Preferences stored in SQL Server.

Non-Functional Requirements:
- Notifications must be delivered within 2 minutes of event.
- System supports at least 10,000 notifications per day.
- All notification data is encrypted in transit and at rest.
