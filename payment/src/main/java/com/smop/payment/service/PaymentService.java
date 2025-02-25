package com.smop.payment.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.smop.payment.model.PaymentRequest;
import com.smop.payment.model.PaymentResponse;
import com.smop.providerintegration.model.PricingInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PaymentService {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public PaymentResponse processPayment(PaymentRequest request) throws StripeException {
        long amount = calculateTotalAmount(request.getProviderPrices());
        
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("eur")
                .setDescription("SMOP Trip Payment")
                .setReceiptEmail(request.getCustomerEmail())
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        return new PaymentResponse(
                paymentIntent.getId(),
                paymentIntent.getClientSecret(),
                paymentIntent.getStatus(),
                amount
        );
    }

    private long calculateTotalAmount(List<PricingInfo> providerPrices) {
        return providerPrices.stream()
                .mapToLong(pricing -> 
                    (long) ((pricing.getBasePrice() * 100) + 
                           (pricing.getPerMinuteRate() * pricing.getDuration()) +
                           (pricing.getPerKilometerRate() * pricing.getDistance() * 100))
                .sum();
    }
}
