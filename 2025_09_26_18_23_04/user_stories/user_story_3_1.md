EPIC Number: 3
User Story Number: 1
User Story Title: As a traveler, I want to search for available flights, So that I can choose the best option for my journey

User Story Description: This feature allows users to search for available flights based on their travel preferences such as origin, destination, date, number of passengers, and class of service. The system should display a list of matching flights with relevant details, enabling users to compare and select the most suitable option.

Acceptance Criteria:
1. Users can input origin, destination, travel dates, number of passengers, and class.
2. The system displays a list of available flights matching the criteria.
3. Flight details include airline, departure/arrival times, duration, layovers, and price.
4. Users can sort and filter results by price, duration, airline, and departure time.
5. The search results update in real-time based on user input.

Validations:
1. Origin and destination cannot be the same.
2. Travel date cannot be in the past.
3. Number of passengers must be at least 1 and not exceed aircraft capacity.
4. Only valid airport codes are accepted.

Business Logic: 
- Query the flight inventory database based on user input.
- Apply filters and sorting based on user preferences.
- Ensure real-time availability by syncing with airline APIs.
- Return only flights with available seats.

Technical Context:
- Technology stack: React.js (frontend), Node.js/Express (backend), PostgreSQL (database).
- Integrate with airline APIs using REST/JSON.
- Secure API endpoints with OAuth 2.0.
- Data formats: JSON for API communication.
- Source: User input; Target: Flight inventory and airline APIs.
- Interfaces: UI, REST API, airline partner APIs.

Non-Functional Requirements:
- Search results must be returned within 2 seconds.
- System must be available 99.9% of the time.
- Secure all data in transit and at rest (TLS 1.2+).
- Scalable to handle 10,000 concurrent users.
- Monitor search API for latency and errors; log all failed searches for analytics.