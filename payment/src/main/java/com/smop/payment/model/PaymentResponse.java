package com.smop.payment.model;

import lombok.Data;

@Data
public class PaymentResponse {
    private String paymentId;
    private String clientSecret;
    private String status;
    private long amount;
}
