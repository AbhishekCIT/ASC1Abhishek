EPIC Number: 32
User Story Number: 1
User Story Title: As a business analyst, I want to define report scheduling parameters, So that users can customize when and how reports are generated automatically.

User Story Description: This user story covers the ability for business analysts or administrators to configure the scheduling parameters for automated reports. This includes setting frequency (daily, weekly, monthly), time of generation, report type, and recipients. The purpose is to provide flexibility and control over automated reporting to meet business needs.

Acceptance Criteria:
1. Users can select report frequency (daily, weekly, monthly).
2. Users can specify the time of day for report generation.
3. Users can choose report type and format (PDF, Excel, etc.).
4. Users can select recipients for the automated report.
5. Scheduling parameters are saved and can be edited or deleted.

Validations:
1. Frequency must be one of the allowed options (daily, weekly, monthly).
2. Time must be in valid 24-hour format.
3. At least one recipient must be selected.
4. Report type must be supported by the system.

Business Logic: When a user sets up a schedule, the system should validate all inputs, save the schedule, and trigger report generation at the specified time. If parameters are changed, the system should update the schedule accordingly. Pseudocode:
IF schedule_parameters_are_valid THEN
    SAVE schedule
    SET timer for report generation
ELSE
    SHOW validation errors

Technical Context: Technology stack: .NET Core backend, Angular frontend, Azure SQL Database. Scheduling logic will use Azure Functions or Logic Apps to trigger report generation. Reports will be generated via backend APIs and sent via email using SendGrid. Data formats: JSON for API, PDF/Excel for reports. Security: Only authorized users can configure schedules.

Non-Functional Requirements:
- Performance: Scheduling should not delay report generation by more than 2 minutes.
- Availability: Scheduling service must be available 99.9% of the time.
- Security: Only authenticated users can access scheduling features.
- Scalability: System should support up to 10,000 scheduled reports per day.
- Monitoring: All scheduling actions should be logged and monitored via Azure Monitor.