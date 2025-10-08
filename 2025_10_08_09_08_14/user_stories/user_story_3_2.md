EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to clear the calculator input, So that I can reset my calculation and start over easily.

User Story Description: This user story covers the ability for a user to clear all current inputs and results in the calculator, allowing them to start a new calculation without refreshing the page or manually deleting values.

Acceptance Criteria:
1. The user can click a 'Clear' button to reset all input fields and results.
2. After clearing, the calculator is ready for new input.
3. No previous values or results are retained after clearing.

Validations:
1. The 'Clear' button must reset all fields to their initial state.
2. No residual data should remain after clearing.
3. The calculator must not crash or freeze after clearing.

Business Logic:
- On clicking the 'Clear' button, all input fields and result displays are reset to default (empty or zero).
- The calculator state is refreshed for a new calculation.

Technical Context:
- Technology stack: React (frontend)
- No backend or API required for this functionality
- Security: Ensure no sensitive data is retained

Non-Functional Requirements:
- Clear operation must complete in < 0.5 seconds
- Application must be responsive to user actions
- No data persistence after clearing
- Error logging for any issues during clear operation
