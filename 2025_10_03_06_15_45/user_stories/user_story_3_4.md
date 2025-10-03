EPIC Number: 3
User Story Number: 4
User Story Title: As a passenger, I want to check in online and select my seat, So that I can save time at the airport and ensure my preferred seating.

User Story Description: This feature allows passengers to complete check-in via the web or mobile app, select available seats, and receive a digital boarding pass. The system should update seat availability in real-time and provide confirmation upon successful check-in.

Acceptance Criteria:
1. Users can check in for their flight within the allowed window.
2. Users can view and select available seats.
3. Users receive a digital boarding pass upon completion.

Validations:
1. Ensure check-in is only allowed within the airline's specified time window.
2. Validate passenger identity and booking reference.
3. Prevent double-booking of seats.

Business Logic: The system verifies booking, checks eligibility for check-in, updates seat map in real-time, and issues a digital boarding pass. If seat selection is not available, assign a seat automatically.

Technical Context: Web/mobile app frontend, backend integration with airline check-in and seat map APIs, secure authentication, PDF/QR code generation for boarding pass.

Non-Functional Requirements: Check-in process must complete in <1 minute, 99.9% uptime, secure handling of personal and travel data, accessible on all major browsers and devices.