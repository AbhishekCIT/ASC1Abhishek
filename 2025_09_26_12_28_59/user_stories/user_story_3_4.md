EPIC Number: 3
User Story Number: 4
User Story Title: As a frequent flyer, I want to manage my loyalty program points, So that I can redeem rewards and track my status.

User Story Description: This user story allows frequent flyers to view their loyalty program points, track their status tier, redeem points for rewards (such as upgrades or free flights), and view transaction history within the application.

Acceptance Criteria:
1. Users can view their current loyalty points and status tier.
2. Users can redeem points for eligible rewards through the app.
3. Users can view a detailed transaction history of points earned and spent.

Validations:
1. Only registered loyalty program members can access this feature.
2. Points redemption must not exceed available balance.
3. All transactions are logged and visible to the user.

Business Logic:
- Fetch loyalty points and status from airline loyalty system.
- Allow redemption based on available points and reward eligibility.
- Update points balance and transaction history after each action.

Technical Context:
- Technology stack: .NET Core backend, React frontend, SQL Server database.
- APIs: Integration with airline loyalty program APIs (REST/SOAP).
- Data format: JSON for API communication.
- Security: OAuth2 authentication, encrypted storage of user data.

Non-Functional Requirements:
- Points balance updates reflected within 1 minute of transaction.
- 99.99% data accuracy for loyalty points.
- Secure handling of personal and transaction data.
- System must support 2,000 concurrent loyalty program users.
- Monitoring of all loyalty-related transactions and redemptions.
