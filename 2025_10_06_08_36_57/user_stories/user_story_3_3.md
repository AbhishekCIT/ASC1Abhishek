EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to perform calculations with decimal numbers, so that I can handle more precise and real-world arithmetic scenarios.

User Story Description: This user story enables users to input and calculate with decimal (floating-point) numbers, ensuring the calculator is useful for a broader range of mathematical tasks.

Acceptance Criteria:
1. User can enter decimal numbers in both input fields.
2. Calculator correctly processes arithmetic operations involving decimals.
3. Results are displayed with appropriate decimal precision (up to 4 decimal places).

Validations:
1. Only valid decimal numbers are accepted as input.
2. Results are rounded or truncated to a maximum of 4 decimal places.
3. Invalid decimal formats (e.g., multiple dots) are rejected.

Business Logic:
- Accept and parse decimal numbers as input.
- Perform arithmetic operations using floating-point arithmetic.
- Round or truncate results to 4 decimal places for display.
- Pseudocode:
  if validDecimal(input1) and validDecimal(input2):
      result = perform_operation(input1, input2, operation)
      display round(result, 4)
  else:
      display 'Invalid input'

Technical Context:
- Technology stack: React (frontend), JavaScript/TypeScript for floating-point arithmetic.
- No API required; all logic is client-side.
- Data format: Internal state, floating-point numbers.
- Security: Input validation to prevent malformed numbers.

Non-Functional Requirements:
- Performance: Calculations must complete within 200ms.
- Availability: 99.9% uptime.
- Security: Input validation to prevent code injection.
- Scalability: Support all concurrent users.
- Analytics: Track usage of decimal calculations.
- Monitoring: Log errors related to decimal input.