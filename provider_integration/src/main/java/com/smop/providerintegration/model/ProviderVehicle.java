package com.smop.providerintegration.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ProviderVehicle {
    private String providerId;
    private String vehicleId;
    private VehicleType type;
    private GeoLocation location;
    private double batteryLevel;
    private PricingInfo pricing;
    private AvailabilityStatus status;
    private Instant lastUpdated;
}

enum VehicleType {
    E_CAR,
    E_SCOOTER,
    E_CARGOBIKE
}

@Data
class GeoLocation {
    private double latitude;
    private double longitude;
}

@Data
class PricingInfo {
    private double basePrice;
    private double perMinuteRate;
    private double perKilometerRate;
}

enum AvailabilityStatus {
    AVAILABLE,
    IN_USE,
    MAINTENANCE,
    LOW_BATTERY
}
