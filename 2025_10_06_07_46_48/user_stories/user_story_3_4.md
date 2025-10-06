EPIC Number: 3
User Story Number: 4
User Story Title: As a user, I want to view a history of my previous calculations, So that I can refer back to results without re-entering data.

User Story Description: The calculator should maintain a session-based history of all calculations performed, displaying the last 10 results (inputs, operation, and output) in a scrollable area. Users should be able to clear the history if desired.

Acceptance Criteria:
1. History section displays last 10 calculations.
2. Each history item shows inputs, operation, and result.
3. User can clear history with a button.

Validations:
1. Only session-based history is maintained (no persistent storage).
2. History updates instantly after each calculation.
3. Clearing history removes all entries from display.

Business Logic:
- On calculation, append new entry to history array.
- Limit history to 10 items (remove oldest if exceeded).
- On 'Clear History', empty history array.

Technical Context:
- Technology stack: ReactJS frontend state management.
- No backend required for session history.
- Security: History is not stored beyond session.

Non-Functional Requirements:
- History updates must be instant (<0.2s).
- History UI must be accessible and readable.
- App must not retain history after browser refresh.