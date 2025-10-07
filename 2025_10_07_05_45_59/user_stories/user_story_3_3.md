EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to clear or reset the calculator, So that I can quickly start a new calculation without refreshing the page.

User Story Description: This user story covers the implementation of a clear or reset button in the calculator application, allowing users to erase current inputs and results to begin a new calculation easily.

Acceptance Criteria:
1. The calculator provides a clear/reset button.
2. Pressing the clear/reset button erases all current inputs and results.
3. The calculator is ready for new inputs immediately after clearing.

Validations:
1. All fields (inputs and result) are reset to default state on clear.
2. No residual data remains after reset.
3. The clear/reset action does not trigger any calculation.

Business Logic:
- On clear/reset button click, set all input fields and result display to empty or default values.
- Ensure no previous state is retained.

Technical Context:
- Technology stack: React state management for resetting values.
- No backend interaction required.
- Security: No sensitive data involved.

Non-Functional Requirements:
- Performance: Reset action should be instantaneous (<50ms).
- Usability: Button should be clearly labeled and accessible.
- Monitoring: Log each reset action for analytics.
