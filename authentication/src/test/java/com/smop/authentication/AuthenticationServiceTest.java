package com.smop.authentication;

import com.smop.authentication.controller.AuthController;
import com.smop.authentication.dto.AuthRequest;
import com.smop.authentication.dto.AuthResponse;
import com.smop.authentication.dto.RegisterRequest;
import com.smop.authentication.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthController authController;

    @MockBean
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    void testRegister() {
        RegisterRequest request = new RegisterRequest(
                "test@example.com", "password", "CUSTOMER");
        
        when(authService.register(any(RegisterRequest.class)))
            .thenReturn(new AuthResponse("token"));

        ResponseEntity<AuthResponse> response = authController.register(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getToken());
    }

    @Test
    void testLogin() {
        AuthRequest request = new AuthRequest("test@example.com", "password");
        
        when(authService.authenticate(any(AuthRequest.class)))
            .thenReturn(new AuthResponse("token"));

        ResponseEntity<AuthResponse> response = authController.login(request);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody().getToken());
    }
}
