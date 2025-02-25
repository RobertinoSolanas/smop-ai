package com.smop.payment.model;

import com.smop.providerintegration.model.PricingInfo;
import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String customerEmail;
    private List<PricingInfo> providerPrices;
}
