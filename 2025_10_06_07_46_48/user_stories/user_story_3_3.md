EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to clear or reset the calculator inputs and results, So that I can start a new calculation easily without refreshing the page.

User Story Description: The calculator should provide a 'Clear' or 'Reset' button that removes all input values and results from the screen, returning the calculator to its initial state. This should work instantly and not affect any other functionality.

Acceptance Criteria:
1. 'Clear' button is visible and accessible.
2. Pressing 'Clear' removes all inputs and results.
3. Calculator returns to initial state after clearing.

Validations:
1. All input fields are emptied.
2. Result display is cleared.
3. Any error messages are removed.

Business Logic:
- On 'Clear', set all input and result fields to blank.
- Remove any error messages.

Technical Context:
- Technology stack: ReactJS frontend.
- No backend required for clear/reset.
- Security: Ensure no sensitive data is retained.

Non-Functional Requirements:
- Clear/reset must occur instantly (<0.2s).
- Button must be accessible via keyboard and screen reader.