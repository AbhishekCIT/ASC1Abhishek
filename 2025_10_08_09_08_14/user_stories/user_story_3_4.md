EPIC Number: 3
User Story Number: 4
User Story Title: As a user, I want to perform calculations with negative numbers, So that I can handle both positive and negative values in my calculations.

User Story Description: This user story covers the ability for users to input and calculate with negative numbers, ensuring the calculator supports both positive and negative values for all operations.

Acceptance Criteria:
1. The user can input negative numbers in both fields.
2. All arithmetic operations work correctly with negative numbers.
3. Results are displayed correctly for operations involving negative numbers.

Validations:
1. Only valid negative numbers are accepted as input.
2. Input fields must allow the '-' sign only at the beginning.
3. Results must be mathematically accurate.

Business Logic:
- Accept and validate negative number input.
- Perform calculations using signed numbers.
- Display results with correct sign.

Technical Context:
- Technology stack: React (frontend), JavaScript number handling
- No backend required for calculation
- Security: Input validation for negative numbers

Non-Functional Requirements:
- Calculations must complete in < 1 second
- Application must prevent invalid negative input
- Error logging for calculation errors
