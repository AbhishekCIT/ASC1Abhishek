EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to perform calculations with decimal numbers, So that I can handle non-integer values in my calculations.

User Story Description: This user story covers the ability for users to input and calculate with decimal (floating-point) numbers, ensuring the calculator supports both integers and decimals for all operations.

Acceptance Criteria:
1. The user can input decimal numbers in both fields.
2. All arithmetic operations work correctly with decimal numbers.
3. Results are displayed with appropriate decimal precision.

Validations:
1. Only valid decimal numbers are accepted as input.
2. Results must not have floating-point inaccuracies (e.g., 0.1 + 0.2 = 0.3).
3. Input fields must prevent multiple decimal points.

Business Logic:
- Accept and validate decimal input.
- Perform calculations using floating-point arithmetic.
- Round results to a reasonable number of decimal places (e.g., 2 or 4).

Technical Context:
- Technology stack: React (frontend), JavaScript number handling
- No backend required for calculation
- Security: Input validation for decimals

Non-Functional Requirements:
- Calculations must complete in < 1 second
- Results must be accurate to at least 4 decimal places
- Application must prevent invalid decimal input
- Error logging for calculation errors
