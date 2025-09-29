EPIC Number: 3
User Story Number: 1
User Story Title: As a passenger, I want to search for available flights, so that I can find and book flights that suit my schedule and budget.

User Story Description: This feature allows users to search for flights based on criteria such as origin, destination, date, and number of passengers. The system should return a list of available flights matching the search criteria, including details such as departure/arrival times, airlines, and prices.

Acceptance Criteria:
1. Users can enter origin, destination, date, and number of passengers.
2. The system displays a list of available flights matching the criteria.
3. Each flight listing includes airline, flight number, departure/arrival times, and price.
4. Users can sort and filter the results by price, duration, or airline.

Validations:
1. Origin and destination must be valid airport codes.
2. Date must not be in the past.
3. Number of passengers must be a positive integer.

Business Logic: The system queries the flight database or external APIs for available flights based on user input. It applies filters and sorting as requested by the user. If no flights are found, an appropriate message is displayed.

Technical Context: The frontend will use React. Backend will be built with Node.js and Express. Flight data will be fetched from an external API (e.g., Amadeus, Sabre). Data will be exchanged in JSON format. Security: All API calls must be authenticated, and sensitive data must be encrypted in transit (HTTPS).

Non-Functional Requirements: The search should return results within 2 seconds. The system must be available 99.9% of the time. All user data must be protected as per GDPR. The system should be able to handle 10,000 concurrent users. Analytics should track search queries and conversion rates. Monitoring via Azure Monitor.