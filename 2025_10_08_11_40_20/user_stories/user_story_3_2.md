EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to clear the calculator input and result, so that I can quickly start a new calculation without refreshing the page.

User Story Description: This feature allows users to reset the calculator to its initial state with a single click. All entered numbers, selected operations, and displayed results will be cleared. The clear/reset button should be easily accessible and visually distinct to avoid accidental clicks.

Acceptance Criteria:
1. A clear/reset button is present and visible on the calculator interface.
2. Clicking the clear/reset button removes all input and output values.
3. Calculator returns to its default state after clearing.
4. No residual data remains after reset.

Validations:
1. Clear/reset button works regardless of the current calculator state.
2. No partial data is left after reset.

Business Logic:
- On clear/reset button click, set all input fields and result display to empty or default values.
- Ensure any temporary variables or states are also reset.

Technical Context:
- Technology stack: React (frontend), JavaScript/TypeScript.
- No backend or API required for this feature.
- Security: Ensure no sensitive data is stored in memory after reset.

Non-Functional Requirements:
- Performance: Reset must be instantaneous (<50ms).
- Availability: Feature must be available at all times.
- Security: Ensure no data persists after reset.
- Usability: Button must be clearly labeled and accessible.