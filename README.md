# SMOP - Smart Mobility Platform

SMOP is a cloud-based microservice platform that integrates multiple electric vehicle providers (e-cars, e-scooters, e-cargobikes) to provide seamless urban mobility solutions.

## Project Structure

```
smop/
├── authentication/
├── routing/
├── provider_integration/
├── payment/
├── notifications/
├── common/
│   ├── config/
│   └── logging/
├── .github/
│   └── workflows/
└── README.md
```

## Getting Started

1. Clone the repository
2. Install dependencies using Maven
3. Run the application

```bash
mvn clean install
```

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration. The pipeline:
- Runs unit tests on every push
- Builds the project
- Checks code quality

## Microservices

- **Authentication**: Handles user authentication and authorization
- **Routing**: Manages route planning and vehicle selection
- **Provider Integration**: Interfaces with external EV providers
- **Payment**: Processes payments and manages transactions
- **Notifications**: Sends real-time updates to users
