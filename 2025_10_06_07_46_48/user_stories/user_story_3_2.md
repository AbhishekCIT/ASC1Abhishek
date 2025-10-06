EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want the calculator to validate my inputs, So that I am prevented from entering invalid data and avoid calculation errors.

User Story Description: The calculator should validate user inputs in real-time, allowing only numeric values and preventing invalid characters. It should also provide clear error messages when invalid input is detected, and prevent calculations until valid input is provided.

Acceptance Criteria:
1. Only numeric input is accepted in both fields.
2. Invalid input triggers a visible error message.
3. Calculation is disabled until inputs are valid.

Validations:
1. Input fields accept only numbers (integer or decimal).
2. No calculation is performed if either field is empty or contains invalid data.
3. Error message is displayed for invalid input.

Business Logic:
- On input change, validate content.
- If invalid, disable 'Calculate' button and show error.
- If valid, enable 'Calculate' button.

Technical Context:
- Technology stack: ReactJS frontend validation.
- No backend required for validation.
- Security: Prevent code injection via input fields.

Non-Functional Requirements:
- Validation must occur instantly (<0.2s).
- Error messages must be accessible and readable.
- App must support accessibility standards (WCAG 2.1 AA).