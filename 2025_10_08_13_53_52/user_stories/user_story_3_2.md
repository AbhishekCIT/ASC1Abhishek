EPIC Number: 3
User Story Number: 2
User Story Title: As a customer, I want to track the real-time status of my air cargo shipment, So that I can stay informed about its location and estimated delivery time.

User Story Description: This feature enables customers to track their air cargo shipments in real-time using a tracking number. The system should display current location, status updates (e.g., in transit, arrived at airport, customs clearance), and estimated delivery time. Notifications should be sent to customers for key status changes.

Acceptance Criteria:
1. Customers can enter a tracking number and view shipment status.
2. Real-time updates are displayed for shipment location and status.
3. Estimated delivery time is provided and updated as needed.
4. Notifications are sent for status changes (e.g., departure, arrival, delivery).

Validations:
1. Tracking number must be valid and associated with a booking.
2. Status updates must be accurate and timely.
3. Notifications must be sent to the correct customer contact details.

Business Logic:
- Validate tracking number and retrieve shipment details.
- Integrate with airline and logistics APIs for real-time status.
- Calculate estimated delivery time based on current location and route.
- Trigger notifications for status changes.

Technical Context:
- Technology stack: .NET Core backend, React frontend, Azure SQL Database.
- API integration with airline tracking systems (REST APIs, JSON format).
- Push notification service (Azure Notification Hubs).
- Secure access to tracking data (OAuth2).

Non-Functional Requirements:
- Real-time update latency <30 seconds.
- System availability 99.9%.
- Data encryption in transit and at rest.
- Scalability to support 5,000 concurrent tracking requests.
- Monitoring and alerting for tracking service failures.
