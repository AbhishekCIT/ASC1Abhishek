EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to perform calculations with decimal numbers, So that I can handle more precise and real-world calculations.

User Story Description: This user story ensures that the calculator application supports decimal (floating-point) numbers for all arithmetic operations, allowing users to input and calculate with non-integer values.

Acceptance Criteria:
1. Users can input decimal numbers for both operands.
2. The calculator performs arithmetic operations accurately with decimal numbers.
3. Results are displayed with appropriate decimal precision.

Validations:
1. Only valid decimal formats are accepted (e.g., 1.23, 0.5).
2. Multiple decimal points in a single number are not allowed.
3. Results are rounded or truncated as per business rules (e.g., up to 4 decimal places).

Business Logic:
- Accept and validate decimal input from the user.
- Perform arithmetic operations using floating-point arithmetic.
- Ensure results are displayed with correct precision and formatting.

Technical Context:
- Technology stack: JavaScript/TypeScript for frontend logic.
- Use built-in floating-point arithmetic functions.
- Input validation using regular expressions.
- Security: Prevent malformed input.

Non-Functional Requirements:
- Performance: Calculations with decimals should be as fast as with integers.
- Security: Input validation for decimals.
- Scalability: No additional impact for supporting decimals.
- Monitoring: Log errors related to invalid decimal input.
