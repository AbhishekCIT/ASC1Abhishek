EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to clear the calculator input and result, so that I can start a new calculation easily.

User Story Description: The calculator should provide a clear button that resets all input fields and the result display. This allows users to quickly start a new calculation without manually deleting previous values.

Acceptance Criteria:
1. A clear button is visible on the calculator interface.
2. Clicking the clear button resets both input fields and the result display.
3. The clear button works at any stage of input or result.

Validations:
1. After clearing, all input fields are empty.
2. The result display is reset to its default state.
3. No residual values remain after clearing.

Business Logic: 
- On clear button click: set input1 = '', input2 = '', result = ''

Technical Context: 
- Technology stack: React (frontend)
- No backend required for this feature.
- UI event handling for button click.

Non-Functional Requirements:
- Clear action completes instantly (<0.5s).
- Button is accessible via keyboard and screen readers.
- No data persists after clearing.