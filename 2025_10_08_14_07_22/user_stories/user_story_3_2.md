EPIC Number: 3
User Story Number: 2
User Story Title: As a traveler, I want to view my air transport itinerary, So that I can keep track of my travel schedule.

User Story Description: This feature allows users to view their booked air transport itinerary, including flight details, departure and arrival times, and seat information. Users should be able to access this information at any time from their account dashboard.

Acceptance Criteria:
1. Users can log in and access their account dashboard.
2. Users can view all upcoming and past air transport bookings.
3. Each itinerary displays flight details, times, and seat assignment.
4. Users can download or print their itinerary.

Validations:
1. Only authenticated users can view itineraries.
2. Itinerary data must match booking records.
3. Downloaded/printed itinerary must contain all relevant details.

Business Logic:
- Retrieve bookings from database linked to user account.
- Display itinerary details in a structured format.
- Provide download/print functionality.

Technical Context:
- Technology stack: .NET Core, ReactJS
- Secure user authentication (OAuth2)
- Data format: JSON
- PDF generation for download/print
- Security: HTTPS, user data encryption

Non-Functional Requirements:
- Dashboard loads within 2 seconds
- 99.9% availability
- Secure access to user data
- Scalable to support thousands of concurrent users
- Monitoring via Azure Application Insights