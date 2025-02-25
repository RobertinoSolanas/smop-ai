package com.smop.integration;

import com.smop.authentication.dto.AuthRequest;
import com.smop.authentication.dto.AuthResponse;
import com.smop.authentication.dto.RegisterRequest;
import com.smop.payment.model.PaymentRequest;
import com.smop.payment.model.PaymentResponse;
import com.smop.routing.model.Route;
import com.smop.routing.model.RouteOptimization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EndToEndTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCompleteUserJourney() {
        // 1. User Registration
        RegisterRequest registerRequest = new RegisterRequest(
                "test@example.com", "password", "CUSTOMER");
        
        ResponseEntity<AuthResponse> registerResponse = restTemplate.postForEntity(
                "/api/auth/register", 
                registerRequest, 
                AuthResponse.class);
        
        assertEquals(HttpStatus.OK, registerResponse.getStatusCode());
        assertNotNull(registerResponse.getBody().getToken());

        // 2. User Login
        AuthRequest authRequest = new AuthRequest("test@example.com", "password");
        ResponseEntity<AuthResponse> loginResponse = restTemplate.postForEntity(
                "/api/auth/login", 
                authRequest, 
                AuthResponse.class);
        
        String authToken = loginResponse.getBody().getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);

        // 3. Route Planning
        ResponseEntity<Route> routeResponse = restTemplate.exchange(
                "/api/routing/route?fromLat=52.5200&fromLon=13.4050&toLat=52.5166&toLon=13.3889&optimization=FASTEST",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                Route.class);
        
        assertNotNull(routeResponse.getBody());
        assertFalse(routeResponse.getBody().getPoints().isEmpty());

        // 4. Payment Processing
        PaymentRequest paymentRequest = new PaymentRequest(
                "test@example.com",
                List.of(routeResponse.getBody().getPricingInfo()));
        
        ResponseEntity<PaymentResponse> paymentResponse = restTemplate.exchange(
                "/api/payments",
                HttpMethod.POST,
                new HttpEntity<>(paymentRequest, headers),
                PaymentResponse.class);
        
        assertEquals(HttpStatus.OK, paymentResponse.getStatusCode());
        assertNotNull(paymentResponse.getBody().getPaymentId());

        // 5. Real-time Tracking
        // This would involve WebSocket testing which requires additional setup
    }
}
