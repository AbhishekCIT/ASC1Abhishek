# For User Story Number [3]
1. Objective
Enable users to input and perform calculations with decimal (floating-point) numbers, supporting both integers and decimals for all arithmetic operations in the calculator. Ensure results are displayed with appropriate precision and floating-point inaccuracies are prevented. Provide robust input validation for decimal values.

2. API Model
  2.1 Common Components/Services
    - Input Validation Service (existing)
    - Arithmetic Operation Service (existing)
    - Error Handling Service (existing)
  2.2 API Details
| Operation | REST Method | Type | URL | Request | Response |
|-----------|-------------|------|-----|---------|----------|
| Calculate | POST | Success | /api/v1/calculate | { "number1": 0.1, "number2": 0.2, "operation": "add" } | { "result": 0.3 } |
| Calculate | POST | Failure | /api/v1/calculate | { "number1": "abc", "number2": 0.2, "operation": "add" } | { "error": "Invalid decimal input." } |
  2.3 Exceptions
    - InvalidDecimalException: Thrown if either input is not a valid decimal number.
    - FloatingPointPrecisionException: Thrown if floating-point inaccuracies are detected.
    - InvalidOperationException: Thrown if operation is not supported.

3 Functional Design
  3.1 Class Diagram
```mermaid
classDiagram
    class CalculatorController {
        +calculate(request: CalculationRequest): CalculationResponse
    }
    class CalculationRequest {
        +number1: double
        +number2: double
        +operation: String
    }
    class CalculationResponse {
        +result: double
        +error: String
    }
    class ArithmeticService {
        +calculate(number1: double, number2: double, operation: String): double
    }
    class ValidationService {
        +validateDecimal(number): boolean
        +validateOperation(operation): boolean
    }
    class ErrorHandler {
        +handleException(Exception): CalculationResponse
    }
    CalculatorController --> ArithmeticService
    CalculatorController --> ValidationService
    CalculatorController --> ErrorHandler
    CalculatorController --> CalculationRequest
    CalculatorController --> CalculationResponse
```
  3.2 UML Sequence Diagram
```mermaid
sequenceDiagram
    participant User
    participant CalculatorController
    participant ValidationService
    participant ArithmeticService
    participant ErrorHandler
    User->>CalculatorController: POST /api/v1/calculate
    CalculatorController->>ValidationService: validateDecimal(number1)
    ValidationService-->>CalculatorController: valid/invalid
    CalculatorController->>ValidationService: validateDecimal(number2)
    ValidationService-->>CalculatorController: valid/invalid
    CalculatorController->>ValidationService: validateOperation(operation)
    ValidationService-->>CalculatorController: valid/invalid
    CalculatorController->>ArithmeticService: calculate(number1, number2, operation)
    ArithmeticService-->>CalculatorController: result/error
    CalculatorController->>User: result/error
    Note over ErrorHandler: Handles exceptions and returns error response
```
  3.3 Components
| Component Name | Description | Existing/New |
|----------------|-------------|--------------|
| CalculatorController | REST controller handling calculation requests | Existing |
| ArithmeticService | Performs arithmetic operations | Existing |
| ValidationService | Validates decimal input and operation | Existing |
| ErrorHandler | Handles and formats errors | Existing |
| CalculationRequest | DTO for request payload | Existing |
| CalculationResponse | DTO for response payload | Existing |
  3.4 Service Layer Logic and Validations
| FieldName | Validation | Error Message | ClassUsed |
|-----------|------------|--------------|-----------|
| number1 | Must be a valid decimal | "First input is not a valid decimal number." | ValidationService |
| number2 | Must be a valid decimal | "Second input is not a valid decimal number." | ValidationService |
| operation | Must be one of add, subtract, multiply, divide | "Invalid operation selected." | ValidationService |
| number1/number2 | Must not have multiple decimal points | "Multiple decimal points detected." | ValidationService |
| result | Must be accurate to 4 decimal places | "Floating-point inaccuracy detected." | ArithmeticService |

4 Integrations
| SystemToBeIntegrated | IntegratedFor | IntegrationType |
|----------------------|---------------|-----------------|
| None | N/A | N/A |

5 DB Details
  5.1 ER Model
```mermaid
erDiagram
    CALCULATION_LOG {
        id int PK
        number1 double
        number2 double
        operation varchar
        result double
        error varchar
        timestamp datetime
    }
```
  5.2 DB Validations
    - All fields must be non-null except 'error'.
    - 'operation' must be one of the supported values.
    - 'result' must be rounded to 4 decimal places.

6 Non-Functional Requirements
  6.1 Performance
    - Calculation response time < 1 second.
  6.2 Security
    6.2.1 Authentication
      - Basic authentication for API access if exposed.
    6.2.2 Authorization
      - Only authenticated users can access calculation API.
  6.3 Logging
    6.3.1 Application Logging
      - Log all calculation requests at INFO level.
      - Log errors at ERROR level.
      - Log validation failures at WARN level.
    6.3.2 Audit Log
      - Log operation usage for analytics with timestamp.

7 Dependencies
    - React frontend for UI
    - JavaScript number handling
    - Database for calculation logs (optional, for analytics)

8 Assumptions
    - Only basic arithmetic operations are supported.
    - Input validation is enforced on both frontend and backend.
    - Application is stateless except for optional logging.
    - Calculations must be accurate to at least 4 decimal places.
