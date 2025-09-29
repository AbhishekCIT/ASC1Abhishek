EPIC Number: 3
User Story Number: 2
User Story Title: As a user, I want to clear/reset the calculator, So that I can easily start a new calculation without refreshing the page

User Story Description: This feature allows users to reset all input fields and the result display with a single click, ensuring a smooth and efficient workflow for consecutive calculations.

Acceptance Criteria:
1. A clear/reset button is available on the calculator UI
2. Clicking the button clears both input fields and the result display
3. Any error messages are also cleared

Validations:
1. After pressing clear, all fields revert to their default state
2. No residual values or messages are visible after reset
3. The calculator is ready for new input immediately

Business Logic:
- On click of the clear/reset button, set all input fields and result display to empty or default values
- Remove any error messages

Technical Context:
- Technology stack: ReactJS
- No backend interaction required
- Security: Ensure no sensitive data is retained after reset

Non-Functional Requirements:
- Performance: Reset action should complete in less than 50ms
- Availability: Feature must be available at all times
- Security: Ensure all temporary data is cleared from memory
- Analytics: Log usage of reset feature
- Monitoring: Track error rates related to reset feature