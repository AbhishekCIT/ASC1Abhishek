EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to check in online, So that I can save time at the airport and select my preferred seat.

User Story Description: This user story covers the ability for passengers to complete check-in online before arriving at the airport. The system should allow seat selection, issue boarding passes, and provide relevant travel information.

Acceptance Criteria:
1. Passengers can access online check-in within the allowed time window before departure.
2. Passengers can select or change their seat during check-in.
3. Boarding passes are generated and sent via email or available for download.

Validations:
1. Check-in is only available within the airline’s check-in window (e.g., 24-48 hours before departure).
2. Seat selection is limited to available seats.
3. Passenger details must match the booking record.

Business Logic: The system validates the check-in window, retrieves seat map and availability, updates the seat assignment, and generates a digital boarding pass. Updates are synchronized with the airline’s system in real-time.

Technical Context: The feature will use backend APIs to handle check-in logic, integrate with airline systems for seat maps and boarding pass generation, and deliver digital passes via email or in-app download. Data is stored securely and updated in real-time.

Non-Functional Requirements: The system must support high concurrency during peak check-in times, ensure data integrity, and provide a responsive user interface for mobile and desktop devices.