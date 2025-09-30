EPIC Number: 3
User Story Number: 3
User Story Title: As a frequent flyer, I want to manage my profile and view my travel history, So that I can easily access past bookings and streamline future reservations.

User Story Description: This feature allows users to create and maintain a personal profile, view their booking history, and store preferences (e.g., seat, meal, frequent flyer number) for faster future bookings.

Acceptance Criteria:
1. Users can create, update, and delete their profile information.
2. Users can view a chronological list of all past and upcoming bookings.
3. Users can save and edit travel preferences for use in future bookings.

Validations:
1. All required profile fields must be completed for saving.
2. Only authenticated users can access or modify their profile and history.
3. Preferences must be applied correctly in subsequent bookings.

Business Logic: The system should link user profiles to all bookings, ensure data privacy, and pre-fill booking forms with saved preferences. Deleting a profile should anonymize booking data but retain necessary audit trails.

Technical Context: User data stored securely in a relational database, GDPR-compliant data handling, frontend built with React, backend with Node.js, and secure authentication (OAuth2/JWT).

Non-Functional Requirements: Profile updates must reflect within 2 seconds, data encrypted at rest and in transit, system must comply with privacy regulations, and support 24/7 availability.