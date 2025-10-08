EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to view a history of my previous calculations, so that I can refer back to past results without re-entering data.

User Story Description: This feature will display a list of recent calculations performed by the user, including the input values, selected operation, and result. The history should be easily accessible from the main calculator interface and allow users to clear the history if desired.

Acceptance Criteria:
1. Calculation history is displayed in a dedicated area of the interface.
2. Each history entry shows the full calculation (e.g., 5 + 3 = 8).
3. User can clear the entire history with a single action.
4. History persists during the session but is cleared on page refresh or reset.

Validations:
1. Only valid calculations are recorded in history.
2. Clearing history removes all entries from the display and memory.

Business Logic:
- After each calculation, append the operation and result to the history list.
- Provide a clear history button that empties the history array.
- Do not persist history beyond the current session (unless extended in future).

Technical Context:
- Technology stack: React (frontend), JavaScript/TypeScript.
- Data format: Array of calculation objects in memory.
- No backend required for session-only history.
- Security: Ensure history is not accessible after session ends.

Non-Functional Requirements:
- Performance: History updates must be instantaneous.
- Scalability: Support at least 50 recent calculations per session.
- Usability: History area must be scrollable if entries exceed visible area.