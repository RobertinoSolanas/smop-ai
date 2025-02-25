package com.smop.providerintegration;

import com.smop.providerintegration.model.ProviderVehicle;
import com.smop.providerintegration.service.ProviderIntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProviderIntegrationTest {

    @Autowired
    private ProviderIntegrationService providerService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testGetAvailableVehiclesSuccess() {
        ProviderVehicle mockVehicle = new ProviderVehicle();
        when(restTemplate.getForObject(anyString(), eq(ProviderVehicle[].class)))
            .thenReturn(new ProviderVehicle[]{mockVehicle});

        var vehicles = providerService.getAvailableVehicles("http://provider.com");
        assertFalse(vehicles.isEmpty());
    }

    @Test
    void testGetAvailableVehiclesFailure() {
        when(restTemplate.getForObject(anyString(), eq(ProviderVehicle[].class)))
            .thenThrow(new RuntimeException("Provider unavailable"));

        assertThrows(RuntimeException.class, () -> {
            providerService.getAvailableVehicles("http://provider.com");
        });
    }

    @Test
    void testReserveVehicleSuccess() {
        when(restTemplate.postForObject(anyString(), isNull(), eq(Boolean.class)))
            .thenReturn(true);

        boolean result = providerService.reserveVehicle("http://provider.com", "vehicle123");
        assertTrue(result);
    }

    @Test
    void testReserveVehicleFailure() {
        when(restTemplate.postForObject(anyString(), isNull(), eq(Boolean.class)))
            .thenThrow(new RuntimeException("Reservation failed"));

        assertThrows(RuntimeException.class, () -> {
            providerService.reserveVehicle("http://provider.com", "vehicle123");
        });
    }
}
