EPIC Number: 3
User Story Number: 3
User Story Title: As a customer, I want to receive notifications about important shipment events, So that I am always informed about the status of my cargo.

User Story Description: This feature enables customers to receive automated notifications via email, SMS, or app push notifications for key shipment events such as booking confirmation, departure, arrival, customs clearance, and delivery. Customers can manage their notification preferences from their profile settings.

Acceptance Criteria:
1. Customers can opt in/out of notifications for specific shipment events.
2. Notifications are sent automatically for selected events.
3. Customers can choose notification channels (email, SMS, push).
4. Notification history is available in the customer portal.

Validations:
1. Notification preferences must be saved and applied correctly.
2. Notifications must be sent to valid contact details.
3. Notification content must be accurate and relevant to the event.

Business Logic:
- Store and retrieve customer notification preferences.
- Trigger notifications based on shipment status changes.
- Select notification channel based on customer settings.
- Log all sent notifications for audit purposes.

Technical Context:
- Technology stack: .NET Core backend, React frontend, Azure SQL Database.
- Integration with email (SendGrid), SMS (Twilio), and push notification services (Azure Notification Hubs).
- Secure handling of customer contact information.

Non-Functional Requirements:
- Notification delivery time <60 seconds from event occurrence.
- System availability 99.9%.
- Data privacy compliance (GDPR).
- Scalability to support 100,000 notifications per day.
- Monitoring and alerting for notification failures.
