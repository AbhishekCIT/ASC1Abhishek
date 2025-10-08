# For User Story Number [1]
1. Objective
The objective is to provide a calculator that allows users to perform basic arithmetic operations (addition, subtraction, multiplication, division) on two numbers. The result should be displayed instantly and the interface should be intuitive and responsive for both desktop and mobile users. The system must handle invalid inputs and division by zero gracefully.

2. API Model
  2.1 Common Components/Services
  - Input validation utility for numbers
  - Arithmetic operation handler/service
  - Error handling component

  2.2 API Details
  | Operation | REST Method | Type    | URL                | Request (JSON)                                  | Response (JSON)                                  |
  |-----------|-------------|---------|--------------------|------------------------------------------------|--------------------------------------------------|
  | Calculate | POST        | Success | /api/calculate     | {"num1": 5, "num2": 2, "operation": "add"}    | {"result": 7}                                   |
  | Calculate | POST        | Failure | /api/calculate     | {"num1": 5, "num2": 0, "operation": "divide"} | {"error": "Cannot divide by zero"}              |
  | Calculate | POST        | Failure | /api/calculate     | {"num1": "a", "num2": 2, "operation": "add"}  | {"error": "Invalid input: must be a number"}    |

  2.3 Exceptions
  | Exception Type           | Description                                    |
  |-------------------------|------------------------------------------------|
  | InvalidInputException    | Thrown when input is not a valid number        |
  | DivisionByZeroException  | Thrown when division by zero is attempted      |
  | OperationNotSupportedException | Thrown when operation is not recognized |

3 Functional Design
  3.1 Class Diagram
  ```mermaid
  classDiagram
    CalculatorController --> CalculatorService
    CalculatorService --> ArithmeticUtils
    CalculatorService --> InputValidator
    CalculatorService --> Logger
    class CalculatorController {
      +calculate(request)
    }
    class CalculatorService {
      +calculate(num1, num2, operation)
    }
    class ArithmeticUtils {
      +add(a, b)
      +subtract(a, b)
      +multiply(a, b)
      +divide(a, b)
    }
    class InputValidator {
      +validateNumber(input)
      +validateOperation(op)
    }
    class Logger {
      +logError(message)
      +logUsage(operation)
    }
  ```

  3.2 UML Sequence Diagram
  ```mermaid
  sequenceDiagram
    participant UI
    participant CalculatorController
    participant CalculatorService
    participant InputValidator
    participant ArithmeticUtils
    participant Logger
    UI->>CalculatorController: POST /api/calculate
    CalculatorController->>InputValidator: validateNumber(num1, num2)
    InputValidator-->>CalculatorController: valid/invalid
    CalculatorController->>CalculatorService: calculate(num1, num2, operation)
    CalculatorService->>ArithmeticUtils: perform operation
    ArithmeticUtils-->>CalculatorService: result/error
    CalculatorService->>Logger: logUsage(operation)
    CalculatorService-->>CalculatorController: result/error
    CalculatorController-->>UI: result/error
    CalculatorService->>Logger: logError(message) (if error)
  ```

  3.3 Components
  | Component Name        | Description                                      | Existing/New |
  |----------------------|--------------------------------------------------|--------------|
  | CalculatorController | REST controller for calculation endpoint          | New          |
  | CalculatorService    | Service layer for business logic                  | New          |
  | ArithmeticUtils      | Utility for arithmetic operations                 | New          |
  | InputValidator       | Utility for input validation                      | New          |
  | Logger               | Logging component for usage and errors            | Existing     |

  3.4 Service Layer Logic and Validations
  | FieldName   | Validation                        | Error Message                  | ClassUsed         |
  |-------------|-----------------------------------|-------------------------------|-------------------|
  | num1        | Must be a valid number             | Invalid input: must be a number| InputValidator    |
  | num2        | Must be a valid number             | Invalid input: must be a number| InputValidator    |
  | operation   | Must be one of add/sub/mul/div     | Invalid operation             | InputValidator    |
  | num2        | If division, must not be zero      | Cannot divide by zero         | CalculatorService |

4 Integrations
  | SystemToBeIntegrated | IntegratedFor         | IntegrationType |
  |----------------------|----------------------|-----------------|
  | Logger               | Usage/error logging  | API/Library     |

5 DB Details
  5.1 ER Model
  ```mermaid
  erDiagram
    CALCULATION_LOG {
      string id PK
      string operation
      double num1
      double num2
      double result
      datetime timestamp
      string error_message
    }
  ```
  5.2 DB Validations
  - None required for basic operation (optional logging only)

6 Non-Functional Requirements
  6.1 Performance
  - Calculation must complete in <100ms.
  - No heavy backend processing; all logic is lightweight.

  6.2 Security
    6.2.1 Authentication
    - Not required for basic calculator, but endpoints should be protected if exposed publicly.
    6.2.2 Authorization
    - Not required for basic calculator.

  6.3 Logging
    6.3.1 Application Logging
    - Log all errors (ERROR level)
    - Log successful calculation usage (INFO level)
    6.3.2 Audit Log
    - Log operation, inputs, result, timestamp for analytics (if enabled)

7 Dependencies
  - Logger system (for analytics and error tracking)
  - Material UI (frontend)

8 Assumptions
  - No persistent storage required unless analytics is enabled.
  - Backend is optional; all logic can be performed client-side if needed.
  - Only basic arithmetic operations are in scope.
