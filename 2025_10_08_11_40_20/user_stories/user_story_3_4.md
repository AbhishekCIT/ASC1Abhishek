EPIC Number: 3
User Story Number: 4
User Story Title: As a user, I want to receive clear error messages for invalid inputs or operations, so that I understand what went wrong and how to correct it.

User Story Description: This feature ensures that users are informed when they enter invalid data (e.g., letters instead of numbers) or attempt invalid operations (e.g., division by zero). Error messages should be concise, informative, and displayed in a prominent location on the calculator interface.

Acceptance Criteria:
1. Invalid numerical input triggers an error message.
2. Division by zero triggers a specific error message.
3. Error messages disappear when the user corrects the input.
4. No calculations are performed when errors are present.

Validations:
1. Error messages are displayed only for invalid actions.
2. No error messages are shown for valid operations.
3. Error state is cleared when input is corrected.

Business Logic:
- Validate input before performing calculations.
- If input is invalid, display an error and prevent calculation.
- If division by zero is attempted, display a specific error and prevent calculation.
- Remove error messages once input is valid.

Technical Context:
- Technology stack: React (frontend), JavaScript/TypeScript.
- Input validation logic in UI components.
- Security: Prevent code injection via input fields.

Non-Functional Requirements:
- Performance: Error detection and display must be instantaneous.
- Usability: Error messages must be clear, non-technical, and accessible.
- Accessibility: Error messages must be screen-reader friendly.