#!/bin/bash

# Delete test users
curl -X DELETE http://localhost:8081/api/auth/users/user1@example.com
curl -X DELETE http://localhost:8081/api/auth/users/user2@example.com

# Clear test provider data
curl -X DELETE http://localhost:8083/api/providers/vehicles/provider1/vehicle1
