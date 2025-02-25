package com.smop.providerintegration.service;

import com.smop.providerintegration.model.ProviderVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProviderIntegrationService {

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(
        value = {RuntimeException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2)
    public List<ProviderVehicle> getAvailableVehicles(String providerUrl) {
        ProviderVehicle[] vehicles = restTemplate.getForObject(
            providerUrl + "/vehicles", 
            ProviderVehicle[].class);
        
        if (vehicles == null) {
            throw new RuntimeException("Failed to fetch vehicles from provider");
        }
        
        return Arrays.asList(vehicles);
    }

    @Retryable(
        value = {RuntimeException.class},
        maxAttempts = 3,
        backoff = @Backoff(delay = 1000, multiplier = 2))
    public boolean reserveVehicle(String providerUrl, String vehicleId) {
        Boolean result = restTemplate.postForObject(
            providerUrl + "/vehicles/" + vehicleId + "/reserve",
            null,
            Boolean.class);
        
        if (result == null || !result) {
            throw new RuntimeException("Failed to reserve vehicle");
        }
        
        return true;
    }
}
