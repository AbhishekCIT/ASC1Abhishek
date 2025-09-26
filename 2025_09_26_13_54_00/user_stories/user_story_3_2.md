EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to view and manage my air transport bookings, So that I can update, cancel, or check details of my reservations easily.

User Story Description: Allow users to access their booking history, view details of upcoming flights, and make changes such as updating passenger information, changing seats, or cancelling bookings. The system should provide real-time updates and notifications for any changes.

Acceptance Criteria:
1. Users can view all current and past bookings.
2. Users can update passenger details and seat preferences.
3. Users can cancel bookings and receive refunds as per policy.
4. Notifications are sent for changes or cancellations.

Validations:
1. Only valid booking IDs can be accessed.
2. Changes are allowed only before the airline's cutoff time.
3. Refunds are processed according to cancellation policy.

Business Logic:
- Fetch bookings from database using user ID.
- Allow updates if flight is not within 24 hours of departure.
- Calculate refund amount based on fare rules.
- Update booking and notify user via email/SMS.

Technical Context:
- Technology stack: .NET Core backend, React frontend.
- Integration with airline booking APIs for real-time updates.
- Secure authentication (OAuth2).
- Data stored in Azure SQL Database.

Non-Functional Requirements:
- Booking management available 24/7.
- Secure access to user data.
- Real-time sync with airline systems.
- Scalable to support thousands of concurrent users.
- Monitoring and alerting for failed updates/cancellations.
