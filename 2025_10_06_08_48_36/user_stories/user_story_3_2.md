EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want the calculator to validate my inputs, So that I am prevented from entering invalid data and can trust the results.

User Story Description: The calculator should check user inputs for validity before performing any calculation. Invalid inputs (such as letters, special characters, or empty fields) should trigger a clear error message and prevent calculation. This ensures reliability and prevents unexpected behavior.

Acceptance Criteria:
1. Only numeric input is accepted in number fields.
2. Error message is displayed for invalid input.
3. Calculation is blocked until valid input is provided.

Validations:
1. Input fields must not be empty.
2. Input must be a valid number (integer or decimal).
3. No special characters or letters allowed in input fields.

Business Logic:
- On input change, validate using regex for numbers.
- If invalid, disable 'Calculate' button and show error.
- If valid, enable calculation.

Technical Context:
- Technology stack: ReactJS
- Use HTML5 input type="number" and custom validation logic
- Error messages handled via state
- Security: Prevent script injection via input

Non-Functional Requirements:
- Performance: Validation should occur in real-time (within 50ms of input)
- Accessibility: Error messages should be screen-reader friendly
- Security: All inputs sanitized
- Monitoring: Log invalid input attempts for analytics
