package com.smop.providerintegration.controller;

import com.smop.providerintegration.model.ProviderVehicle;
import com.smop.providerintegration.service.ProviderIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

    @Autowired
    private ProviderIntegrationService providerService;

    @GetMapping("/vehicles")
    public List<ProviderVehicle> getAvailableVehicles(@RequestParam String providerUrl) {
        return providerService.getAvailableVehicles(providerUrl);
    }

    @PostMapping("/reserve")
    public boolean reserveVehicle(
            @RequestParam String providerUrl,
            @RequestParam String vehicleId) {
        return providerService.reserveVehicle(providerUrl, vehicleId);
    }
}
