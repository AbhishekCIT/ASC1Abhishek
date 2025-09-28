EPIC Number: 3
User Story Number: 5
User Story Title: As a user, I want to view a history of my recent calculations, so that I can refer back to previous results.

User Story Description: The calculator should display a list of recent calculations (inputs, operation, and result) within the application interface. This helps users keep track of their work and refer back to previous calculations without re-entering data.

Acceptance Criteria:
1. A history section is visible below or beside the calculator.
2. Each calculation (inputs, operation, result) is added to the history after completion.
3. History displays at least the last 10 calculations.
4. User can clear the history if desired.

Validations:
1. No duplicate entries in history.
2. Clearing history removes all entries.
3. History updates in real time after each calculation.

Business Logic:
- After each calculation, append {input1, operation, input2, result} to history array.
- Limit history array to 10 items (remove oldest if exceeded).
- Provide clear history button to empty the array.

Technical Context:
- Technology stack: React (frontend), local state management.
- No backend required; history stored in client memory.

Non-Functional Requirements:
- History updates instantly (<0.5s).
- History persists during session (until browser/tab is closed).
- Accessible for screen readers.