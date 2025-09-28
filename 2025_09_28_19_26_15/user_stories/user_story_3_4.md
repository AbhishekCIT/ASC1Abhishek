EPIC Number: 3
User Story Number: 4
User Story Title: As a user, I want to use my keyboard to enter numbers and operations, so that I can interact with the calculator more efficiently.

User Story Description: The calculator should support keyboard input for numbers, decimal point, and operation keys (+, -, *, /, Enter for equals, and Esc for clear). This improves accessibility and speed for users who prefer keyboard navigation.

Acceptance Criteria:
1. Numeric keys (0-9) and decimal point input numbers in the fields.
2. Operation keys (+, -, *, /) select the corresponding operation.
3. Enter key triggers calculation.
4. Esc key clears all fields and result.
5. Focus indicators are visible for accessibility.

Validations:
1. Only valid keys are accepted (others are ignored).
2. Keyboard shortcuts work in all supported browsers.
3. No input is lost or misinterpreted during fast typing.

Business Logic:
- Map keyboard events to calculator actions.
- Prevent default browser actions that interfere with calculator input.

Technical Context:
- Technology stack: React (frontend)
- JavaScript event listeners for keyboard events.
- Accessibility ARIA roles and focus management.

Non-Functional Requirements:
- Keyboard response time <100ms.
- Full accessibility compliance (WCAG 2.1 AA).
- Keyboard support tested on Chrome, Firefox, Edge, Safari.