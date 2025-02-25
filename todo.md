Prompt 1: Foundational Setup
---------------------------------
Context:
You are tasked with initializing the SMOP project repository with the necessary structure and setting up the CI/CD pipeline. The project is microservices-driven, so a modular folder structure is required.

Requirements:
1. Initialize a Git repository with a clear README explaining the project.
2. Create separate folders for each microservice: authentication, routing, provider_integration, payment, notifications.
3. Set up a common configuration module and a logging library.
4. Configure a basic CI/CD pipeline (e.g., using GitHub Actions or similar) to run tests on each commit.
5. Write initial basic unit tests to verify the setup.

Please provide the code/templates for:
- The project directory structure.
- A sample README.md.
- A basic CI/CD configuration file.
- Starter code for the common configuration and logging library.
- An initial unit test example for one of the microservices.

Prompt 2: Authentication & User Management
--------------------------------------------
Context:
Building on the foundational setup, implement the authentication and user management microservice. This service should allow user registration, login, and role-based access control using JWT or OAuth 2.0.

Requirements:
1. Create endpoints for user registration and login.
2. Implement JWT-based authentication and include role-based access control for roles: Mr. Huber, Cloud Experts, Customers.
3. Write unit tests for the registration and login endpoints.
4. Integrate the authentication service with the common configuration and logging library from Prompt 1.
5. Ensure all code follows best practices for security and testability.

Provide the implementation code, including:
- API endpoint definitions.
- JWT token generation and verification logic.
- Role-based access control middleware.
- Unit tests for the endpoints.

Prompt 3: Routing System Development
---------------------------------------
Context:
Now implement the core routing system that enables multi-modal trip planning. The system should support various optimization criteria (fastest, cheapest, eco-friendly) and suggest vehicle switches automatically.

Requirements:
1. Develop the routing search engine logic.
2. Implement options for selecting the optimization criteria.
3. Build a module that suggests automatic vehicle switching based on real-time availability.
4. Write unit and integration tests for the routing logic.
5. Integrate this system with the existing project structure and configuration from previous prompts.

Please provide:
- The code for the routing engine and optimization logic.
- Code for the vehicle switching suggestion module.
- Tests covering different routing scenarios.

Prompt 4: External Provider Integration
------------------------------------------
Context:
Integrate external electric vehicle providers using a standardized data model and a middleware layer. This layer must handle API communication, manage data transformations, and gracefully handle failures.

Requirements:
1. Define a standardized data model for provider information (availability, pricing, location).
2. Create a middleware module that interacts with external provider APIs.
3. Implement error handling with retry logic (exponential backoff) for provider API calls.
4. Write tests to simulate provider API success and failure scenarios.
5. Integrate this middleware with the routing and booking modules from previous prompts.

Provide the implementation code including:
- Data model definitions.
- Middleware functions for API calls and error handling.
- Test cases for provider API interactions.

Prompt 5: Payment System Implementation
------------------------------------------
Context:
Develop the payment system to process a single seamless payment per trip. The system will integrate with a payment gateway, calculate pricing based on provider rates, and handle secure transactions.

Requirements:
1. Integrate with a payment gateway (e.g., Stripe) with a sandbox mode for testing.
2. Implement the logic to calculate the final trip price based on various provider rates.
3. Ensure the system adheres to PCI-DSS standards and secure transaction protocols.
4. Write unit tests to verify payment processing and pricing logic.
5. Integrate the payment module with the provider integration and routing system.

Provide:
- The payment processing code.
- Pricing calculation logic.
- Integration and unit tests.

Prompt 6: Real-Time Updates & Notifications
-----------------------------------------------
Context:
Implement the real-time data handling and notification system. This will provide live tracking for vehicles and notify users of important updates (booking confirmations, trip status changes).

Requirements:
1. Set up a WebSockets or MQTT-based service to handle real-time vehicle updates.
2. Develop notification endpoints for booking confirmations and ride updates.
3. Write integration tests to simulate real-time data flow and notifications.
4. Ensure that notifications are integrated with the user dashboard and mobile app components.

Provide:
- Code for setting up the real-time update service.
- Notification endpoint implementations.
- Test cases for real-time update and notification flows.

Prompt 7: Cloud Infrastructure & Deployment
-----------------------------------------------
Context:
With all microservices built and tested, configure the cloud infrastructure for deployment. This includes setting up Kubernetes, an API gateway, load balancing, and monitoring.

Requirements:
1. Create Kubernetes deployment and service YAML configurations for each microservice.
2. Set up an API Gateway (or Nginx configuration) to manage traffic across services.
3. Configure load balancing and auto-scaling policies.
4. Integrate centralized logging and monitoring (Prometheus, Grafana, CloudWatch).
5. Write deployment scripts and documentation for the cloud infrastructure.

Provide:
- Kubernetes YAML files for deployments and services.
- API gateway configuration.
- Sample monitoring and logging configuration.
- Deployment scripts (e.g., Bash or Terraform scripts).

Prompt 8: End-to-End Integration & Final Testing
---------------------------------------------------
Context:
Wire together all the microservices to form a cohesive SMOP platform. Conduct comprehensive end-to-end tests covering user journeys from registration and booking through to payment and real-time tracking.

Requirements:
1. Integrate all microservices (authentication, routing, provider integration, payment, notifications) into a single platform.
2. Write end-to-end tests simulating complete user workflows (e.g., user registration, multi-modal trip planning, payment processing, ride tracking).
3. Ensure that error handling, retries, and notifications work across service boundaries.
4. Finalize documentation and create a guide for running the full system in a test environment.
5. Perform a final code review and integration test run.

Provide:
- Code or scripts that integrate and wire all modules together.
- End-to-end test cases for complete workflows.
- Documentation outlining how to deploy and test the entire system.

