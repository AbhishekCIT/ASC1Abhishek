EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to view and manage my air transport bookings, So that I can make changes or cancellations as needed.

User Story Description: This feature allows users to view their current and past bookings, modify flight details (where permitted), and cancel bookings. The system should display refund policies and process changes in real time.

Acceptance Criteria:
1. Users can view a list of their active and past bookings.
2. Users can modify booking details (e.g., change date, passenger info) if allowed by airline policy.
3. Users can cancel bookings and receive confirmation of cancellation and refund status.

Validations:
1. Only authenticated users can access their bookings.
2. Modifications are allowed only within airline policy windows.
3. Cancellations trigger refund calculations based on fare rules.

Business Logic: The system checks airline rules for changes/cancellations, calculates any applicable fees or refunds, and updates booking status. Notifications are sent to users for every change.

Technical Context: The backend integrates with airline APIs for booking management. User authentication is required (OAuth 2.0). All changes are logged. Refunds are processed through the payment gateway.

Non-Functional Requirements: Changes and cancellations must be processed within 5 seconds. The system must be available 24/7 and ensure secure access to booking data.