package com.smop.payment;

import com.smop.payment.model.PaymentRequest;
import com.smop.payment.model.PaymentResponse;
import com.smop.payment.service.PaymentService;
import com.smop.providerintegration.model.PricingInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private PaymentIntent paymentIntent;

    @Test
    void testProcessPayment() throws Exception {
        // Setup test data
        PricingInfo pricing1 = new PricingInfo();
        pricing1.setBasePrice(2.0);
        pricing1.setPerMinuteRate(0.2);
        pricing1.setPerKilometerRate(0.5);
        pricing1.setDuration(30);
        pricing1.setDistance(5);

        PaymentRequest request = new PaymentRequest(
                "test@example.com",
                List.of(pricing1)
        );

        // Mock Stripe response
        when(paymentIntent.getId()).thenReturn("pi_12345");
        when(paymentIntent.getClientSecret()).thenReturn("secret_12345");
        when(paymentIntent.getStatus()).thenReturn("succeeded");
        when(paymentIntent.create(any(PaymentIntentCreateParams.class)))
            .thenReturn(paymentIntent);

        // Execute test
        PaymentResponse response = paymentService.processPayment(request);

        // Verify results
        assertNotNull(response);
        assertEquals("pi_12345", response.getPaymentId());
        assertEquals("secret_12345", response.getClientSecret());
        assertEquals("succeeded", response.getStatus());
        assertEquals(850L, response.getAmount()); // 2.0 + (0.2 * 30) + (0.5 * 5) = 8.50 EUR
    }
}
