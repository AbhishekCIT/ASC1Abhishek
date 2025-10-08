# For User Story Number [3]
1. Objective
The objective is to provide a session-based calculation history in the calculator UI, allowing users to view previous calculations (inputs, operation, result) and clear the history if desired. This enhances user experience and productivity, especially for multi-step calculations. The history must be session-based and not persist across page refreshes.

2. API Model
  2.1 Common Components/Services
  - In-memory history manager (frontend state)
  - History display component
  - Clear history handler/service

  2.2 API Details
  | Operation      | REST Method | Type    | URL            | Request (JSON) | Response (JSON) |
  |---------------|-------------|---------|----------------|---------------|-----------------|
  | Add to History| N/A         | Success | N/A (frontend) | N/A           | N/A             |
  | Clear History | N/A         | Success | N/A (frontend) | N/A           | N/A             |

  2.3 Exceptions
  | Exception Type | Description                          |
  |---------------|--------------------------------------|
  | None          | No backend/API, handled in frontend   |

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    CalculatorUI --> HistoryManager
    CalculatorUI --> HistoryDisplay
    HistoryManager --> HistoryEntry
    class CalculatorUI {
      +onCalculate()
      +onClearHistory()
    }
    class HistoryManager {
      +addEntry(entry)
      +clearHistory()
      +getHistory()
    }
    class HistoryDisplay {
      +render(history)
    }
    class HistoryEntry {
      double num1
      double num2
      string operation
      double result
    }
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant User
    participant CalculatorUI
    participant HistoryManager
    participant HistoryDisplay
    User->>CalculatorUI: Perform calculation
    CalculatorUI->>HistoryManager: addEntry({num1, operation, num2, result})
    HistoryManager-->>CalculatorUI: updated history
    CalculatorUI->>HistoryDisplay: render(history)
    User->>CalculatorUI: Click 'Clear History'
    CalculatorUI->>HistoryManager: clearHistory()
    HistoryManager-->>CalculatorUI: empty history
    CalculatorUI->>HistoryDisplay: render(history)
  ```

  3.3 Components
  | Component Name   | Description                                 | Existing/New |
  |------------------|---------------------------------------------|--------------|
  | CalculatorUI     | Main UI component for calculator            | Existing     |
  | HistoryManager   | Manages in-memory history state             | New          |
  | HistoryDisplay   | Renders calculation history list            | New          |
  | HistoryEntry     | Represents a single history record          | New          |

  3.4 Service Layer Logic and Validations
  | FieldName   | Validation                       | Error Message | ClassUsed      |
  |-------------|----------------------------------|---------------|---------------|
  | history     | Only valid calculations added    | N/A           | HistoryManager |
  | history     | Cleared on user action           | N/A           | HistoryManager |
  | history     | Not persisted across sessions    | N/A           | HistoryManager |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor        | IntegrationType |
  |----------------------|---------------------|-----------------|
  | Logger               | Log history usage    | API/Library     |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    // No DB entities required; session memory only
  ```
  5.2 DB Validations
  - None required

6 Non-Functional Requirements
  6.1 Performance
  - History update must be instant (UI state update only).

  6.2 Security
    6.2.1 Authentication
    - Not required for this feature.
    6.2.2 Authorization
    - Not required for this feature.

  6.3 Logging
    6.3.1 Application Logging
    - Log usage of history feature (INFO level)
    6.3.2 Audit Log
    - Not required

7 Dependencies
  - Logger system (for usage tracking)
  - Material UI (frontend)

8 Assumptions
  - No backend/API required for history feature.
  - All state is managed in the frontend and cleared on page refresh.
  - Maximum 100 entries per session.
