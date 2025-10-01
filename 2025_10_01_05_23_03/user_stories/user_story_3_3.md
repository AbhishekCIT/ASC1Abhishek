EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to manage and track my checked baggage, So that I can be assured of my baggage's location and status during my journey.

User Story Description: This user story enables passengers to add baggage to their booking, pay for additional baggage, and track the status and location of their checked baggage throughout the journey. The system should notify users of baggage check-in, transfer, and arrival at the destination.

Acceptance Criteria:
1. Passengers can add and pay for baggage during booking or check-in.
2. Baggage tags are generated and linked to the booking.
3. Passengers can track baggage status via the app or website.
4. Notifications are sent for baggage check-in, transfer, and arrival.

Validations:
1. Baggage weight and dimensions must comply with airline policies.
2. Payment for additional baggage must be processed securely.
3. Baggage tracking information must be accurate and up-to-date.

Business Logic: The system should validate baggage details against airline rules, calculate fees for excess baggage, integrate with baggage handling systems for tracking, and trigger notifications based on baggage events.

Technical Context: Integration with airline baggage handling APIs, backend in Node.js, frontend in React, payment integration (Stripe), and secure storage of baggage and payment data.

Non-Functional Requirements: Baggage status updates must be reflected within 2 minutes, system must handle at least 5,000 concurrent tracking requests, ensure GDPR compliance for personal data, and provide 99.9% uptime.