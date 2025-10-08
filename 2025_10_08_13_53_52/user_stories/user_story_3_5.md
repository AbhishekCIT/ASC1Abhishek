EPIC Number: 3
User Story Number: 5
User Story Title: As a business user, I want to generate analytics and reports on air transport shipments, So that I can monitor performance and make informed decisions.

User Story Description: This feature enables business users and administrators to generate detailed analytics and reports on air transport shipments, including metrics such as total shipments, delivery times, cancellations, revenue, and customer satisfaction. Reports should be exportable in PDF/Excel formats and support filtering by date, route, and customer.

Acceptance Criteria:
1. Users can select report type and filters (date range, route, customer).
2. System generates and displays analytics and reports with relevant metrics.
3. Reports can be exported in PDF and Excel formats.
4. Data is updated in real-time or near real-time.

Validations:
1. Filters must be applied correctly to report data.
2. Exported reports must match displayed analytics.
3. Data privacy rules must be enforced for sensitive information.

Business Logic:
- Aggregate shipment data based on selected filters.
- Calculate key metrics (e.g., average delivery time, cancellation rate).
- Format data for display and export.
- Enforce access controls for sensitive data.

Technical Context:
- Technology stack: .NET Core backend, React frontend, Azure SQL Database, Power BI integration.
- Data export functionality (PDF, Excel).
- Secure access to analytics (role-based access control).

Non-Functional Requirements:
- Report generation time <10 seconds for standard filters.
- System availability 99.9%.
- Data encryption at rest and in transit.
- Scalability to support 500 concurrent report generations.
- Monitoring and alerting for analytics service failures.
