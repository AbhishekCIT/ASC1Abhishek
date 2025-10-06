EPIC Number: 3
User Story Number: 4
User Story Title: As a frequent flyer, I want to enroll in a loyalty program, So that I can earn and redeem points for my air travel.

User Story Description: This user story covers the ability for passengers to enroll in a loyalty program, view their points balance, and redeem points for rewards such as flight upgrades or free tickets. The system should provide clear information on earning and redemption rules.

Acceptance Criteria:
1. Passengers can enroll in the loyalty program online.
2. Points are automatically credited after eligible flights.
3. Passengers can view and redeem points for rewards.
4. System displays loyalty program rules and benefits.

Validations:
1. Enrollment is restricted to eligible passengers.
2. Points are credited only for eligible flights.
3. Redemption is allowed only if sufficient points are available.

Business Logic:
- Enrollment process verifies passenger eligibility.
- Points calculation based on fare class, distance, and promotions.
- Redemption engine checks points balance and applies rewards.

Technical Context:
- Technology stack: ReactJS, Node.js, Azure SQL Database.
- Integration with airline loyalty systems.
- Data format: JSON.
- Security: HTTPS, user authentication, GDPR compliance for personal data.

Non-Functional Requirements:
- Points update latency < 24 hours after flight completion.
- 99.9% uptime for loyalty program service.
- Secure storage of personal and transactional data.
- Monitoring for points calculation and redemption errors.
