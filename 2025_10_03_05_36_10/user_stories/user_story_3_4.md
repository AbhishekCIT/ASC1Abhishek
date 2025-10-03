EPIC Number: 3
User Story Number: 4
User Story Title: As a frequent flyer, I want to earn and redeem loyalty points, So that I can benefit from my repeated bookings.

User Story Description: This feature enables users to enroll in a loyalty program, accumulate points with each booking, and redeem those points for discounts, upgrades, or free flights. Users can view their points balance and transaction history in their account.

Acceptance Criteria:
1. Users can enroll in the loyalty program during registration or from their profile.
2. Points are automatically credited for eligible bookings.
3. Users can view points balance and redemption options.
4. Users can redeem points for eligible rewards during booking.

Validations:
1. Points are only awarded for completed, non-cancelled bookings.
2. Redemption is only allowed if the user has sufficient points.
3. Points calculation and expiry rules are enforced.

Business Logic:
- Points accrue based on booking value and fare class.
- Redemption logic applies discounts or upgrades based on points.
- Points expiry and promotional bonuses managed as per business rules.

Technical Context:
- Technology stack: React (frontend), Node.js (backend), Azure SQL Database.
- APIs: Loyalty management, booking integration.
- Data formats: JSON.
- Security: User authentication, secure points transactions.

Non-Functional Requirements:
- Points update reflected within 1 minute of booking.
- 99.9% uptime for loyalty services.
- Secure handling of loyalty data.
- Scalable to support millions of users.
- Monitoring for points accrual and redemption errors.