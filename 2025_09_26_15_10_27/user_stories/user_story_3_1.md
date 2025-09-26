EPIC Number: 3
User Story Number: 1
User Story Title: As a traveler, I want to book air transport tickets online, So that I can conveniently plan my trips and secure my seat in advance.

User Story Description: This user story covers the ability for travelers to search for available flights, select their preferred flight, and book tickets online through the application. The feature should provide real-time availability, pricing, and allow users to enter passenger details and make payments securely.

Acceptance Criteria:
1. Users can search for flights based on origin, destination, and date.
2. Users can view available flights with pricing and seat availability.
3. Users can select a flight, enter passenger information, and proceed to payment.
4. Users receive a booking confirmation with ticket details after successful payment.

Validations:
1. Origin and destination fields must not be empty and must be valid airport codes.
2. Date of travel must not be in the past.
3. Payment must be processed securely and confirmed before booking is finalized.

Business Logic: 
- Search flights by querying the flight inventory database based on user input.
- Validate seat availability in real-time before allowing booking.
- Calculate total price including taxes and fees.
- Generate unique booking reference number upon successful payment.

Technical Context: 
- Technology stack: ReactJS frontend, Node.js backend, Azure SQL Database.
- Integrate with third-party flight APIs for real-time data.
- Payment gateway integration (e.g., Stripe, PayPal).
- Data exchanged in JSON format over HTTPS.
- Secure user data with OAuth2 authentication and encryption at rest.

Non-Functional Requirements:
- System must handle at least 1000 concurrent users.
- Booking confirmation should be sent within 5 seconds of payment.
- All personal and payment data must be encrypted.
- System must be available 99.9% of the time.
- Monitor API response times and error rates in Azure Monitor.
