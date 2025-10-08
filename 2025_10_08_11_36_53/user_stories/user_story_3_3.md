EPIC Number: 3
User Story Number: 3
User Story Title: As a user, I want to view a history of my recent calculations, so that I can refer back to previous results without re-entering data.

User Story Description: The calculator should display a session-based history of calculations, showing the inputs, operation, and result for each calculation performed. Users should be able to clear the history if desired. This feature improves user experience and productivity, especially for multi-step calculations.

Acceptance Criteria:
1. Calculation history is displayed in a clear, chronological list.
2. Each history entry shows both inputs, the operation, and the result.
3. User can clear the history with a single action.
4. History is session-based and resets when the page is refreshed.

Validations:
1. Only valid calculations are added to history.
2. Clearing history removes all entries.
3. History does not persist across sessions.

Business Logic:
- After each calculation, append the input values, operation, and result to the history list.
- On 'Clear History', remove all entries from the history list.
- Pseudocode:
  onCalculate():
    history.append({num1, operation, num2, result})
  onClearHistory():
    history = []

Technical Context:
- Technology Stack: React
- UI Framework: Material UI
- Data Format: Array of objects in memory
- Security: History stored only in session memory

Non-Functional Requirements:
- Performance: History update should be instant
- Availability: Feature always accessible
- Security: No persistent storage of history
- Scalability: Up to 100 entries per session
- Monitoring: Log usage of history feature
