EPIC Number: 3
User Story Number: 3
User Story Title: As a passenger, I want to manage my baggage information online, So that I can prepay for baggage and track my luggage.

User Story Description: This user story allows passengers to add baggage to their booking, pay for additional bags, and track checked luggage. The system should allow users to view baggage allowance, add or remove bags, make payments, and receive updates on baggage status during the journey.

Acceptance Criteria:
1. Users can view baggage allowance and fees for their flight.
2. Users can add or remove baggage from their booking and pay online.
3. Users can track the status/location of checked baggage (if supported by airline).

Validations:
1. Baggage additions must not exceed airline or regulatory limits.
2. Payments must be validated and processed securely.
3. Baggage tracking must display accurate and timely information.

Business Logic: The system will integrate with airline APIs to fetch baggage policies and fees, process payments, and (where supported) retrieve baggage tracking data from airport systems.

Technical Context: The feature will use REST APIs for airline integration, payment gateway for transactions, and possibly RFID/barcode tracking for baggage status. The frontend will be React, backend Node.js, and data stored in a secure SQL database.

Non-Functional Requirements: The baggage management feature must be available 24/7, support up to 500 concurrent users, and ensure all payment and tracking data is encrypted and logged for compliance.