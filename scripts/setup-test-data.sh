#!/bin/bash

# Create test users
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user1@example.com","password":"password1","role":"CUSTOMER"}'

curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user2@example.com","password":"password2","role":"CUSTOMER"}'

# Add test provider data
curl -X POST http://localhost:8083/api/providers/vehicles \
  -H "Content-Type: application/json" \
  -d '[
    {
      "providerId": "provider1",
      "vehicleId": "vehicle1",
      "type": "E_SCOOTER",
      "location": {"latitude": 52.5200, "longitude": 13.4050},
      "batteryLevel": 0.85,
      "pricing": {
        "basePrice": 1.0,
        "perMinuteRate": 0.2,
        "perKilometerRate": 0.5
      },
      "status": "AVAILABLE"
    }
  ]'
