Prompt 0: Foundational Setup
Use Spring Java in the latest version

Draft a detailed, step-by-step blueprint for building this project. Then, once you have a solid plan, break it down into small, iterative chunks that build on each other. Look at these chunks and then go another round to break it into small steps. Review the results and make sure that the steps are small enough to be implemented safely with strong testing, but big enough to move the project forward. Iterate until you feel that the steps are right sized for this project.

From here you should have the foundation to provide a series of prompts for a code-generation LLM that will implement each step in a test-driven manner. Prioritize best practices, incremental progress, and early testing, ensuring no big jumps in complexity at any stage. Make sure that each prompt builds on the previous prompts, and ends with wiring things together. There should be no hanging or orphaned code that isn't integrated into a previous step.

Make sure and separate each prompt section. Use markdown. Each prompt should be tagged as text using code tags. The goal is to output prompts, but context, etc is important as well.

# Smart Mobility Platform (SMOP) - Developer Specification

## 1. Overview
SMOP (Smart Mobility Platform) is a cloud-based, microservices-driven platform designed to seamlessly integrate multiple electric vehicle providers. Users can book multi-modal trips (e-cars, e-scooters, e-cargo bikes) without realizing they are using different providers. Payments and routing are handled seamlessly to enhance the user experience.

## 2. Key User Roles
### 2.1. Mr. Huber (Owner)
- Access to a **business dashboard**
- Manage **provider partnerships**
- View **financial reports and platform analytics**

### 2.2. Cloud Experts
- Responsible for **cloud infrastructure setup**
- **Deployment and monitoring** of microservices
- Ensure **scalability and security** of the platform

### 2.3. Customers
- **Register/Login** via mobile or web
- **Plan and book trips** combining multiple electric vehicle providers
- **Make seamless payments** for multi-provider routes
- **Track rides** in real-time
- **Receive notifications** (booking confirmation, updates, etc.)
- **View ride history and manage profiles**

## 3. Platform Support
- **Mobile Applications:** iOS & Android
- **Web Application:** Responsive web portal

## 4. Core Features
### 4.1. Routing System
- Users can search for **multi-modal routes** (combining different electric vehicles)
- **Optimization preferences:** Fastest, Cheapest, or Eco-friendly
- **Automatic vehicle switching suggestions**
- **Real-time vehicle availability and battery level** consideration
- **Traffic-aware routing**

### 4.2. External Provider Integration
- **API-based vehicle provider integration**
- **Standardized data model** for vehicle availability, pricing, and location
- **Middleware layer** to handle bookings and payments

### 4.3. Payment System
- **Single seamless payment** per trip, SMOP handles provider payments
- **Pricing calculation based on provider rates**
- **No additional user fees**, only standard provider rates apply

## 5. Architecture & Technology Stack
- **Cloud Provider:** AWS (or similar scalable solution)
- **Backend:** Node.js / Python (FastAPI) with microservices
- **Frontend:** React (Web) + React Native (Mobile)
- **Database:** PostgreSQL / MongoDB (for structured/unstructured data)
- **Authentication:** OAuth 2.0, JWT-based authentication
- **Payment Gateway:** Stripe / Adyen / PayPal
- **Real-time Data Handling:** WebSockets / MQTT for live vehicle updates
- **API Gateway & Load Balancing:** Kubernetes with Nginx / AWS API Gateway
- **Logging & Monitoring:** Prometheus, Grafana, AWS CloudWatch

## 6. Data Handling & Security
- **Data Encryption:** TLS 1.2+ for all communications, AES-256 for sensitive data
- **Personal Data Protection:** GDPR-compliant user data management
- **Access Control:** Role-based access control (RBAC) for different user roles
- **Transaction Security:** PCI-DSS compliance for payments

## 7. Error Handling Strategies
- **Graceful Failures:** In case of provider API failures, suggest alternative routes
- **Retry Logic:** Implement exponential backoff for API calls
- **Error Logging:** Centralized logging with alerts for critical failures
- **User Notifications:** Inform users of booking failures or trip modifications

## 8. Testing Plan
### 8.1. Unit Testing
- Test individual **microservices** for functionality
- Validate **routing logic**, **payment handling**, and **authentication**

### 8.2. Integration Testing
- Ensure **smooth provider API integration**
- Validate **real-time vehicle updates and tracking**
- Test **multi-modal trip calculations**

### 8.3. Performance Testing
- Load testing for **high user traffic**
- Stress testing on **database and API calls**

### 8.4. Security Testing
- **Penetration testing** to identify vulnerabilities
- **Data protection validation** (GDPR, PCI-DSS compliance)

### 8.5. User Acceptance Testing (UAT)
- Beta testing with real users for usability feedback
- Validation of **end-to-end booking experience**

---
This specification provides a solid foundation for developers to begin implementing SMOP efficiently while ensuring scalability, security, and user satisfaction.

