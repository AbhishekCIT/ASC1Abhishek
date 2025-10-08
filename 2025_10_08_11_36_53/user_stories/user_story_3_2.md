EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to clear or reset the calculator inputs and results, so that I can start a new calculation without refreshing the page.

User Story Description: The calculator should provide a 'Clear' or 'Reset' button that resets both input fields and the result display, allowing the user to perform a new calculation easily. This improves usability and reduces confusion when switching between calculations.

Acceptance Criteria:
1. 'Clear' button is visible and accessible.
2. Clicking 'Clear' resets both input fields and the result display.
3. No residual values remain after clearing.

Validations:
1. After clearing, both input fields should be empty.
2. Result display should be reset to default (blank or zero).
3. No previous calculation data should persist.

Business Logic:
- On 'Clear' button click, set both input fields and result display to their default state.
- Pseudocode:
  onClear():
    input1 = ''
    input2 = ''
    result = ''

Technical Context:
- Technology Stack: React
- UI Framework: Material UI
- No backend required
- Security: Ensure no sensitive data is retained

Non-Functional Requirements:
- Performance: Reset action should be instant
- Availability: Feature always accessible
- Security: No data leakage
- Scalability: Works for all users
- Monitoring: Log usage of 'Clear' feature
