EPIC Number: 3
User Story Number: 4
User Story Title: As a user, I want to view a history of my previous calculations, So that I can refer back to past results without re-entering data.

User Story Description: The calculator should maintain a session-based history of all calculations performed. Users should be able to view, clear, and select previous calculations to reuse inputs. This feature enhances usability and saves time for users performing multiple related calculations.

Acceptance Criteria:
1. History of calculations is displayed in a dedicated section.
2. Each history entry shows input values, operation, and result.
3. User can clear the history with a single action.
4. User can select a history entry to reuse its inputs.

Validations:
1. History only persists for the current session (not saved after closing app).
2. Clearing history removes all entries immediately.
3. Selecting a history entry populates input fields correctly.

Business Logic:
- On calculation, append entry {num1, num2, operation, result} to history array
- Display history array in UI
- Clear history resets array to empty
- Selecting entry sets input fields to entry values

Technical Context:
- Technology stack: ReactJS
- State management via React Context or Redux
- No backend required for session history
- Data format: Array of objects in memory
- Security: Do not persist sensitive data

Non-Functional Requirements:
- Performance: History updates within 100ms of calculation
- Scalability: Supports up to 100 history entries per session
- Security: History is session-based and not stored permanently
- Monitoring: Log history usage for analytics
