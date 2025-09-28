EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to enter and calculate decimal numbers, so that I can perform more precise calculations.

User Story Description: The calculator should accept decimal (floating-point) numbers as input and handle arithmetic operations involving decimals. The results should also be displayed with appropriate decimal precision.

Acceptance Criteria:
1. Input fields accept decimal numbers (e.g., 3.14).
2. All four operations work with decimal inputs.
3. Results are displayed with up to 4 decimal places.
4. Input validation prevents multiple decimal points in a single number.

Validations:
1. Only one decimal point allowed per input field.
2. Results are rounded to 4 decimal places.
3. Invalid decimal input triggers an error message.

Business Logic:
- Parse input as floating-point numbers.
- Perform arithmetic as per operation.
- Round result to 4 decimal places for display.

Technical Context:
- Technology stack: React (frontend)
- JavaScript number parsing and formatting.
- Input masking for decimal entry.

Non-Functional Requirements:
- Calculation and rendering must remain <1s even with decimals.
- No floating-point errors in common cases.
- Accessible input for users with disabilities.